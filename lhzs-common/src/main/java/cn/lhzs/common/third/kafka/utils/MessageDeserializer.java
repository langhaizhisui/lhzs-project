package cn.lhzs.common.third.kafka.utils;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.log4j.Logger;

import java.util.Map;

public class MessageDeserializer implements Deserializer<MessageBean> {

    private static final Logger logger = Logger.getLogger(MessageDeserializer.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public MessageBean deserialize(String s, byte[] bytes) {
        try {
            return (MessageBean) SerializeUtil.deserialize(bytes);
        } catch (Exception e) {
            logger.error(e,e) ;
        }
        return null;
    }

    @Override
    public void close() {

    }

}
