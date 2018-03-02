package cn.lhzs.web.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IBA-EDV on 2018/3/2.
 */
public class ConsumerDemo implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    KafkaConsumer<String, String> consumer;// = new KafkaConsumer<>(props);

    public void run() {
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("group.id", "test");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("session.timeout.ms", "30000");
            props.put("key.deserializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList("wordcount"));
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(10000);
                // Handle new records
                for (final ConsumerRecord<String, String> rc : records) {
                    System.out.println("msg=" + rc.value());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get())
                throw e;
        } finally {
            consumer.close();
        }
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ConsumerDemo sub1 = new ConsumerDemo();
        Thread tsub1 = new Thread(sub1);
        tsub1.start();
    }
}
