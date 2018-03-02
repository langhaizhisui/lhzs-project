package cn.lhzs.common.third.kafka.utils.handler;

/**
 * Created by ZHX
 */
public interface IConsumerHandler {

    void handle(Object obj);

    String topic();
}
