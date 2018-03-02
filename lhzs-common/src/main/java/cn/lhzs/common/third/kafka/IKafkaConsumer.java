package cn.lhzs.common.third.kafka;



import cn.lhzs.common.third.kafka.utils.MessageBean;
import org.apache.kafka.clients.consumer.Consumer;

import java.util.Properties;

/**
* @ClassName:IKafkaConsumer.java
* @Description:消息消费监听器接口
* @author:  ZHX
 */
public interface IKafkaConsumer {

	void init();

	Consumer<String, MessageBean> consumeList(String... topic);

	void destory();

	void setProperties(Properties properties);
}