package com.weiliai.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Author: Doug Li
 * @Date 2020/12/5
 * @Describe: 生产者API
 */
public class CustomProducer {

    private static final Properties props;

    static {
        //记录配置信息
        props = new Properties();

        //kafka集群,broker-list
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks","all");

        //重试次数
        props.put("retries",1);
        //批次大小
        props.put("batch.size",16384);
        //等待时间
        props.put("linger.ms",1);

        //RecordAccumulator缓冲区大小
        props.put("buffer.memory",33554432);

        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    }


    /**
     * 异步发送无回调
     */
    @Test
    public void testNoCallBack(){
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        for(int i = 0; i<100; i++){
            producer.send(new ProducerRecord<>("first",Integer.toString(i),Integer.toString(i)));
        }
        producer.close();
    }

    /**
     * 异步发送带回调
     */
    @Test
    public void testCallBack(){
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        for(int i = 0; i<100; i++){
            producer.send(new ProducerRecord<>("first", Integer.toString(i), Integer.toString(i)), (metadata, exception) -> {
                if(null == exception){
                    System.out.println("success->"+metadata.offset());
                }else{
                    exception.printStackTrace();
                }
            });
        }
        producer.close();
    }

    /**
     * 同步发送
     */
    @Test
    public void testSyncNoCallBack() throws ExecutionException, InterruptedException {
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        for(int i = 0; i<100; i++){
            producer.send(new ProducerRecord<>("first",Integer.toString(i),Integer.toString(i))).get();
        }
        producer.close();
    }

    @Test
    public void testInterceptors(){
        List<String> interceptors = Arrays.asList("com.weiliai.kafka.interceptor.CounterInterceptor","com.weiliai.kafka.interceptor.TimeInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        for(int i = 0; i<10; i++){
            producer.send(new ProducerRecord<>("first","message"+i));
        }
        //关闭producer才会调用拦截器的close方法
        producer.close();
    }

}
