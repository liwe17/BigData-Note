package com.weiliai.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: Doug Li
 * @Date 2020/12/5
 * @Describe: 消费者API
 */
public class CustomConsumer {

    private static final Properties props;

    static {
        //记录配置信息
        props = new Properties();

        //kafka集群,broker-list
        props.put("bootstrap.servers","localhost:9092");

        props.put("group.id","test");

        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    }

    /**
     * 测试自动提交
     */
    @Test
    public void testAutoOffset(){
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms","1000");
        final KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first"));
        for(;;){
            consumer.poll(100).forEach(e->System.out.printf("offset = %d,key = %s,value = %s%n",e.offset(),e.key(),e.value()));
        }
    }

    /**
     * 测试同步提交
     */
    @Test
    public void testCommitSync(){
        props.put("enable.auto.commit","false");
        final KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first"));
        for(;;){
            consumer.poll(100).forEach(e->System.out.printf("offset = %d,key = %s,value = %s%n",e.offset(),e.key(),e.value()));
            //同步提交,当前线程会阻塞直到offset提交成功
            consumer.commitSync();
        }
    }

    /**
     * 测试异步提交
     */
    @Test
    public void testCommitAsync(){
        props.put("enable.auto.commit","false");
        final KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first"));
        for(;;){
            consumer.poll(100).forEach(e->System.out.printf("offset = %d,key = %s,value = %s%n",e.offset(),e.key(),e.value()));
            //异步提交
            consumer.commitAsync((offsets, exception) -> {
                if(null==exception){
                    System.out.println("Commit failed for "+offsets);
                }
            });
        }
    }

}
