package com.xiaobaby.myproject.kafka.config;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

/**
 * @author Lu Yufeng
 * @date 2018/8/7 下午6:54
 */

@Component
public class KafkaDataProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;



    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void sendTest(){
        kafkaTemplate.send("test","this is a test"+ sdf.format(d));
    }

}




