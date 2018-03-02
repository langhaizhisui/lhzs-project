package cn.lhzs.common.third.kafka.utils.handler;

/**
 * Created by ZHX
 */
public interface IProducerHandler {

    void handle(Object obj);

    String topic();
}
