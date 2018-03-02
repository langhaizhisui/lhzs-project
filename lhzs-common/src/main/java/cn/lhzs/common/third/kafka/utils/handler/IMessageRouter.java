package cn.lhzs.common.third.kafka.utils.handler;

import cn.lhzs.common.third.kafka.utils.MessageBean;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * Created by ZHX
 */
public interface IMessageRouter {


    void route(ConsumerRecords<String, MessageBean> consumerRecords);
}
