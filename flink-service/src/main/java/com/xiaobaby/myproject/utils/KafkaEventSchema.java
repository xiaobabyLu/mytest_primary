package com.xiaobaby.myproject.utils;

import com.xiaobaby.myproject.entity.KafkaEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * @author Lu Yufeng
 * @date 2018/8/8 上午11:16
 */
public class KafkaEventSchema implements DeserializationSchema<KafkaEvent>,SerializationSchema<KafkaEvent> {

//    private static final long serialVersionUID = 6154188370181669758L;

    @Override
    public KafkaEvent deserialize(byte[] bytes) throws IOException {
        return KafkaEvent.fromString(new String(bytes));
    }

    @Override
    public boolean isEndOfStream(KafkaEvent t) {
        return false;
    }

    @Override
    public byte[] serialize(KafkaEvent t) {
        return t.toString().getBytes();
    }


    @Override
    public TypeInformation<KafkaEvent> getProducedType() {
        return TypeInformation.of(KafkaEvent.class);
    }
}

