package com.xiaobaby.myproject.service;

import com.xiaobaby.myproject.utils.MessageSplitter;
import com.xiaobaby.myproject.utils.MessageWaterEmitter;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;


import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.streaming.api.TimeCharacteristic;

import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Lu Yufeng
 * @date 2018/8/7 下午6:46
 */
public class StreamingTest {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private static String servers;
//
//    @Value("${spring.kafka.consumer.group-id}")
//    private static String groupId;
//
//    @Value("${spring.kafka.consumer.topic}")
//    private static String topic;

    public static final Logger LOGGER = LoggerFactory.getLogger(StreamingTest.class);

    private static String outPath = "/Users/Desktop/result";

    public static void main(String[] args) throws Exception {

//        final ParameterTool parameterTool = ParameterTool.fromArgs(args);

//
//        if (parameterTool.getNumberOfParameters() < 5) {
//            System.out.println("Missing parameters!\n" +
//                    "Usage: Kafka --input-topic <topic> --output-topic <topic> " +
//                    "--bootstrap.servers <kafka brokers> " +
//                    "--zookeeper.connect <zk quorum> --group.id <some id>");
//            return;
//        }
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000);// create a checkpoint every 5 seconds
        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(3,10000));
//        env.getConfig().setGlobalJobParameters(parameterTool);
        // make parameters available in the web interface
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        /*** 组装kafka配置 ***/
        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("zookeeper.connect","localhost:2181");
        props.setProperty("group.id","test-consumer-group");



        FlinkKafkaConsumer010<String> consumer = new FlinkKafkaConsumer010<String>("test",new SimpleStringSchema(),props);
        consumer.assignTimestampsAndWatermarks(new MessageWaterEmitter());

        DataStream<Tuple2<String,Long>> keyedStream = env.addSource(consumer).flatMap(new MessageSplitter()).keyBy(0)
                .timeWindow(Time.seconds(10)).apply(new WindowFunction<Tuple2<String, Long>, Tuple2<String, Long>, Tuple, TimeWindow>() {
                    @Override
                    public void apply(Tuple tuple, TimeWindow timeWindow, Iterable<Tuple2<String, Long>> input, Collector<Tuple2<String, Long>> output) throws Exception {
                        long sum = 0L;
                        int count = 0;
                        for(Tuple2<String,Long> record:input){
                            sum += record.f1;
                            count++;
                        }
                        Tuple2<String,Long> result = input.iterator().next();
                        result.f1 = sum/count;
                        output.collect(result);

                        System.out.println(result.toString());
                    }
                });
        Thread.sleep(2000);

        keyedStream.print();
        System.out.println("       ==============     ");

        keyedStream.writeAsText(outPath);

        env.execute("Kafka Example");
    }

}
