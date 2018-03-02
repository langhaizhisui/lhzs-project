package cn.lhzs.common.third.kafka.utils.handler;



import cn.lhzs.common.third.kafka.utils.MessageBean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by ZHX
 */
@Component("messageHandlerSelector")
public class MessageHandlerSelector {

    @Autowired(required = false)
    private List<IConsumerHandler> messageHandlers = new ArrayList<>();

    @Autowired
    @Qualifier(value = "doNothingConsumerHandler")
    private IConsumerHandler doNothingConsumerHandler;

    public IConsumerHandler select(ConsumerRecord<String, MessageBean> record) {
        Optional<IConsumerHandler> handlerOptional = messageHandlers.stream().filter(handler -> handler.topic().equals(record.topic())).findFirst();
        return handlerOptional.isPresent() ? handlerOptional.get() : doNothingConsumerHandler;
    }
}
