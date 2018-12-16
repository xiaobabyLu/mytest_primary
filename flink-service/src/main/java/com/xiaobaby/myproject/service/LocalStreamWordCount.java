package com.xiaobaby.myproject.service;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @author Lu Yufeng
 * @date 2018/8/13 下午4:10
 */

public class LocalStreamWordCount {


    /****
     * 在终端输入命令进行验证：nc -lk 9999
     * ****/
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        /*******局域网数据源********/
//        DataStream<Tuple2<String,Integer>> ds = env.socketTextStream("localhost",9999).flatMap(new SplitWord())
//                .keyBy(0).timeWindow(Time.seconds(5)).sum(1);


        /*******kafka数据源********/
        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","test-consumer-group");

        DataStream<Tuple2<String,Integer>> ds = env.addSource(new FlinkKafkaConsumer010<>("test",new SimpleStringSchema(),props))
                .flatMap(new SplitWord()).keyBy(0).timeWindow(Time.seconds(5)).sum(1);



        ds.print();


        DataStream<String> out =  ds.flatMap(new MergeData()).filter(new TestFilter());


        /********把结果直接输出到文件*******/
//        ds.writeAsText("/Users/didi/Desktop/LocalStreamWordCount.txt");

        /********把结果输出到kafka消息队列*******/
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers","localhost:9092");
//        props.setProperty("group.id","test-consumer-group");
//
        out.addSink(new FlinkKafkaProducer010<>("test1",new SimpleStringSchema(),props));

        env.execute("flink windows word count");
    }


    public static class SplitWord implements FlatMapFunction<String,Tuple2<String,Integer>>{

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String [] words = s.split(",");
            for(String word : words){
                collector.collect(new Tuple2<String, Integer>(word,1));
            }

        }
    }
    /*****数据清洗******/
    public static class MergeData implements FlatMapFunction<Tuple2<String,Integer>,String>{

        private transient ValueState<Integer> currentTotalCount;


        @Override
        public void flatMap(Tuple2<String, Integer> input, Collector<String> collector) throws Exception {


            collector.collect(new String(String.valueOf(input.f0)+" : "+String.valueOf(input.f1)));


//            collector.collect(new String(String.valueOf(input.f0)+" : "+String.valueOf(input.f1)));
        }
    }



    /*****数据筛选器******/
    public static class TestFilter implements FilterFunction<String>{

        @Override
        public boolean filter(String s) throws Exception {

            /********筛选只包含test 内容数据**********/
            if(s.contains("test")){
                return true;
            }
            return false;
        }
    }



    public static class  RichMapData extends RichFlatMapFunction<String, String> {

        @Override
        public void flatMap(String s, Collector<String> collector) throws Exception {

        }


    }



    public static class  MapData extends RichMapFunction<String, String> {

        @Override
        public String map(String s) throws Exception {
            return null;
        }
    }
}
