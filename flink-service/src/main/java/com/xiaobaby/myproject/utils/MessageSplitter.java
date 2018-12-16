package com.xiaobaby.myproject.utils;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author Lu Yufeng
 * @date 2018/8/7 下午9:38
 */
public class MessageSplitter implements FlatMapFunction<String,Tuple2<String,Long>> {
    @Override
    public void flatMap(String value, Collector<Tuple2<String, Long>> collector) throws Exception {

        if(value!=null && value.contains(",")){
            String [] parts = value.split(",");

            collector.collect(new Tuple2<String, Long>(parts[1],Long.parseLong(parts[2])));
        }

    }
}
