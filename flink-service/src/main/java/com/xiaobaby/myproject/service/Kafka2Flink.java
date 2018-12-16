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
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.Properties;

/**
 * @author Lu Yufeng
 * @date 2018/8/8 上午10:38
 */
public class Kafka2Flink {

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","test-consumer-group");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4,10000));
        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<KafkaElement> input = env.addSource(
                new FlinkKafkaConsumer010<>("test", new KafkaElementSchema(),props)).flatMap(new WordCountFlatMap()).filter(new WordCountFilter())
                .assignTimestampsAndWatermarks(new PeriodWatermarkExtractor()).keyBy("keyWord").map(new WordCountMapper());

        input.addSink(new FlinkKafkaProducer010<>("test1",new KafkaElementSchema(),props));

        env.execute("flink-example");

    }



    private static class WordCountFilter implements FilterFunction<KafkaElement>{

        @Override
        public boolean filter(KafkaElement element) throws Exception {
            return false;
        }
    }


    private static class WordCountFlatMap implements FlatMapFunction<KafkaElement,KafkaElement>{



        @Override
        public void flatMap(KafkaElement element, Collector<KafkaElement> collector) throws Exception {

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







//    private static class ConsumerWatermarkExtractor implements AssignerWithPunctuatedWatermarks<KafkaElement> {
//
//
//        private long currentTimestamp = Long.MIN_VALUE;
//
//
//        @Nullable
//        @Override
//        public Watermark checkAndGetNextWatermark(KafkaElement element, long previousElementTimestamp) {
//
//            return null;
//        }
//
//        @Override
//        public long extractTimestamp(KafkaElement element, long previousElementTimestamp) {
//
//            return 0;
//        }
//    }


    private static class PeriodWatermarkExtractor implements AssignerWithPeriodicWatermarks<KafkaElement> {


//        private static final long serialVersionUID = -742759155861320823L;

        private long currentTimestamp = Long.MIN_VALUE;

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            return new Watermark(currentTimestamp == Long.MIN_VALUE ? Long.MIN_VALUE : currentTimestamp -1 );
        }

        @Override
        public long extractTimestamp(KafkaElement element, long previousElementTimestamp) {
            this.currentTimestamp = element.getTimestamp();
            return element.getTimestamp();
        }
    }
}
