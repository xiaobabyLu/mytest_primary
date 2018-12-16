package com.xiaobaby.myproject.utils;

import com.xiaobaby.myproject.entity.KafkaElement;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * @author Lu Yufeng
 * @date 2018/8/8 上午11:16
 */
public class KafkaElementSchema implements DeserializationSchema<KafkaElement>,SerializationSchema<KafkaElement> {

//    private static final long serialVersionUID = 6154188370181669758L;


    @Override
    public KafkaElement deserialize(byte[] message) throws IOException {
        return KafkaElement.fromString(new String(message));
    }

    @Override
    public boolean isEndOfStream(KafkaElement element) {
        return false;
    }

    @Override
    public byte[] serialize(KafkaElement element) {
        return element.toString().getBytes();
    }

    @Override
    public TypeInformation<KafkaElement> getProducedType() {
        return TypeInformation.of(KafkaElement.class);
    }
}
