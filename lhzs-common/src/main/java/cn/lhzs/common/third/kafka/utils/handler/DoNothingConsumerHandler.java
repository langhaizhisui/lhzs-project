package cn.lhzs.common.third.kafka.utils.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by ZHX
 */
@Component("doNothingConsumerHandler")
public class DoNothingConsumerHandler implements IConsumerHandler {

    private static final Logger logger = Logger.getLogger(DoNothingConsumerHandler.class);

    @Override
    public void handle(Object obj) {
        logger.error("Invalid message topic, please contact with administrator");
    }

    @Override
    public String topic() {
        return "";
    }
}
