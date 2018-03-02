package cn.lhzs.common.third.kafka;


import cn.lhzs.common.third.kafka.utils.MessageBean;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
* @ClassName:KafkaConsumerImpl.java
* @Description:消息消费监听器实现
* @author:  ZHX
 */

public class KafkaConsumerImpl implements IKafkaConsumer {
	
	private Properties properties;

	private Consumer<String, MessageBean> consumer = null;

	private String topicName;

	/**
    * @description:初始化消息接收监听
    * @author Sawyer  2016-12-3
     */
    public void init(){
		consumer = new KafkaConsumer<>(properties);
    }
    
	/**
	* @description:接收指定主题消息列表
	* @param topics 指定主题
	* @author Sawyer  2016-12-3
	 */
    public Consumer<String, MessageBean> consumeList(String... topics) {
		consumer.subscribe(Arrays.asList(topics));
		return consumer;
    }

    /**
    * @description:销毁接收实例
    * @author Sawyer  2016-12-3
     */
    public void destory(){
    	if(consumer!=null){
    		consumer.close();
    	}
    }

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}