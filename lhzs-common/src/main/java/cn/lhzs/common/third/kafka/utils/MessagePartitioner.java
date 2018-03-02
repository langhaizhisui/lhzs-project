package cn.lhzs.common.third.kafka.utils;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @ClassName:MessagePartitioner.java
 * @Description:主题分区 未来访问量上来之后可扩展分区
 * @author: ZHX
 */

public class MessagePartitioner implements Partitioner {
	
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		return 0;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> configs) {

	}
}
