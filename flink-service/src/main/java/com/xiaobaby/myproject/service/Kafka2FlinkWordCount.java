package com.xiaobaby.myproject.service;

import com.xiaobaby.myproject.entity.KafkaElement;
import com.xiaobaby.myproject.utils.KafkaElementSchema;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.Properties;

/**
 * @author Lu Yufeng
 * @date 2018/8/8 上午10:38
 */
public class Kafka2FlinkWordCount {

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","test-3-group");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4,10000));
        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        System.out.println("--------------start---------------");

        DataStream<Tuple2<String,Integer>> input = env.addSource(
                new FlinkKafkaConsumer010<>("test", new SimpleStringSchema(),props)).flatMap(new WordCountFlatMap()).filter(new WordCountFilter())
                .keyBy(0).timeWindow(Time.seconds(5)).sum(1);

        input.writeAsText("/Users/Desktop/test.txt");

        System.out.println("---------------end--------------");
        Thread.sleep(1000);
//        input.addSink(new FlinkKafkaProducer010<Tuple2<String, Integer>>("test1", ,props));

        env.execute("flink-example");

    }



    private static class WordCountFilter implements FilterFunction<Tuple2<String,Integer>>{


        @Override
        public boolean filter(Tuple2<String,Integer> s) throws Exception {
            if("test".equals(s.f1)){
                return true;
            }
            return false;
        }
    }


    private static class WordCountFlatMap implements FlatMapFunction<String,Tuple2<String,Integer>>{


        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String [] words = s.split(",");

            for(String word:words){
                if(word.length()>0){
                    collector.collect(new Tuple2<String, Integer>(word,1));
                }
            }

        }
    }


    private static class WordCountMapper extends RichMapFunction<KafkaElement,KafkaElement>{

        private transient ValueState<Integer> currentTotalCount;


        @Override
        public KafkaElement map(KafkaElement element) throws Exception {
            Integer totalCount = currentTotalCount.value();

            if(totalCount==null){
                totalCount=0;
            }
            totalCount += element.getFrequency();

            currentTotalCount.update(totalCount);

            return new KafkaElement(element.getKeyWord(),totalCount,element.getTimestamp());
        }


        @Override
        public void open(Configuration parameters) throws Exception {

            currentTotalCount = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("currentTotalCount",Integer.class));
        }
    }



//    private static class PeriodWatermarkExtractor implements AssignerWithPeriodicWatermarks<Tuple2<String,Integer>> {
//
//
//        private long currentTimestamp = Long.MIN_VALUE;
//
//        @Nullable
//        @Override
//        public Watermark getCurrentWatermark() {
//            return new Watermark(currentTimestamp == Long.MIN_VALUE ? Long.MIN_VALUE : currentTimestamp -1 );
//        }
//
//        @Override
//        public long extractTimestamp(KafkaElement element, long previousElementTimestamp) {
//            this.currentTimestamp = element.getTimestamp();
//            return element.getTimestamp();
//        }
//    }
}
