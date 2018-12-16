package com.xiaobaby.myproject.kafka.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Lu Yufeng
 * @date 2018/8/7 下午6:49
 */

@Component
public class KafkaDataConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(KafkaDataConsumer.class);


    @KafkaListener(topics = {"test"})
    private String consumer(String message){

        LOGGER.info("test topic message :{} ",message);
        System.out.println(message);
        return message;
    }
}
