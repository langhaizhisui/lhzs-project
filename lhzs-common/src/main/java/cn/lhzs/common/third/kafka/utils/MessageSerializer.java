package cn.lhzs.common.third.kafka.utils;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class MessageSerializer implements Serializer<MessageBean> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, MessageBean messageBean) {
        return SerializeUtil.serialize(messageBean);
    }

    @Override
    public void close() {

    }

}
