package cn.lhzs.common.third.kafka;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by ZHX
 */
public interface IKafkaProducer {

    boolean produce(String TOPIC, Object vo, String msgKey) throws InterruptedException, ExecutionException, TimeoutException;

    boolean produce(String TOPIC, Object vo) throws InterruptedException, ExecutionException, TimeoutException;

    boolean produceByKey(Object vo, String msgKey) throws InterruptedException, ExecutionException, TimeoutException;

    boolean produce(Object vo) throws InterruptedException, ExecutionException, TimeoutException;

    void destory();

}
