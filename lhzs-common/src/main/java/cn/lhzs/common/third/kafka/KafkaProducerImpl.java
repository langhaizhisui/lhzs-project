package cn.lhzs.common.third.kafka;

/**
 * Created by ZHX
 */


import cn.lhzs.common.third.kafka.utils.MessageBean;
import cn.lhzs.common.util.DateUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * 消息发送接口
 */
public class KafkaProducerImpl implements IKafkaProducer {

    private static final Logger logger = Logger.getLogger(KafkaProducerImpl.class);

    private Producer<String, MessageBean> producer = null;
    private Properties properties;
    private String topicName;//发送主题

    /**
     * @description:初始化消息发送
     * @author Sawyer
     */
    public void init() {
        producer = new KafkaProducer<>(properties);
    }

    /**
     * @param vo     发送内容对象 必须序列化
     * @param msgKey 指定分区key
     * @return boolean
     * @description:发送消息到指定分区
     * @author Sawyer
     */
    @Override
    public boolean produceByKey(Object vo, String msgKey) throws InterruptedException, ExecutionException, TimeoutException {
        genericSender(topicName, vo, msgKey);
        return true;
    }

    /**
     * TOPIC指定主题
     * @param vo        发送内容对象 必须序列化
     * @param msgKey    指定分区key
     * @return boolean
     * @description:发送消息到指定主题、指定分区
     * @author Sawyer
     */
    @Override
    public boolean produce(String topic, Object vo, String msgKey) throws InterruptedException, ExecutionException, TimeoutException {
        genericSender(topic, vo, msgKey);
        return true;
    }

    /**
     * TOPIC指定主题
     * @param vo        发送内容对象 必须序列化
     * @return boolean
     * @description:发送消息到指定主题
     * @author Sawyer
     */
    @Override
    public boolean produce(String topic, Object vo) throws InterruptedException, ExecutionException, TimeoutException {
        genericSender(topic, vo, topic);
        return true;
    }

    /**
     * @param vo 发送内容对象 必须序列化
     * @return boolean
     * @description:发送消息到默认主题
     * @author Sawyer
     */
    @Override
    public boolean produce(Object vo) throws InterruptedException, ExecutionException, TimeoutException {
        genericSender(topicName, vo, topicName);
        return true;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * @description:销毁发送实例
     * @author Sawyer
     */
    public void destory() {
        if (producer != null) {
            producer.close();
        }
    }

    private void genericSender(String topic, Object vo, String msgKey) throws InterruptedException, ExecutionException, TimeoutException {
        ProducerRecord<String, MessageBean> km;
        if (!(vo instanceof MessageBean)) {
            final String nowTm = DateUtil.getDateTime();
            MessageBean msgBean = new MessageBean();
            msgBean.setContent(vo);
            msgBean.setDatetimes(nowTm);
            km = new ProducerRecord<>(topic, msgBean);
        } else {
            km = new ProducerRecord<>(topic, (MessageBean) vo);
        }

        RecordMetadata recordMetadata = producer.send(km, (metadata, exception) -> {
            if (exception != null) {
                logger.error("Kafka 发送失败: time:" + new Date().toString() + " message:" + toJSONString(vo));
            }
        }).get(500, TimeUnit.MILLISECONDS);
        if (recordMetadata != null ) {
            logger.error("Kafka 发送成功: time:" + new Date().toString());
        }

    }


}

