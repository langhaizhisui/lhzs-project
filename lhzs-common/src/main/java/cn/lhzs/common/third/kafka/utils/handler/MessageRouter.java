package cn.lhzs.common.third.kafka.utils.handler;


import cn.lhzs.common.third.kafka.utils.MessageBean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by ZHX
 */
@Component("messageRouter")
public class MessageRouter implements IMessageRouter {

    @Autowired
    private MessageHandlerSelector messageHandlerSelector;

    public void route(ConsumerRecords<String, MessageBean> consumerRecords) {
        Iterator<ConsumerRecord<String, MessageBean>> iterator = consumerRecords.iterator();
        while (iterator.hasNext()) {
            ConsumerRecord<String, MessageBean> next = iterator.next();
            messageHandlerSelector.select(next).handle(next.value().getContent());
        }
    }
}
