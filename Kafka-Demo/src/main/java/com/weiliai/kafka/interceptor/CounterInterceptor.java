package com.weiliai.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author: Doug Li
 * @Date 2020/12/6
 * @Describe: 统计发送消息成功和失败消息数,并在producer关闭时打印这两个计数器
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {

    private int errorCounter = 0;

    private int successCounter = 0;

    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        //统计成功和失败次数
        if(null == exception){
            successCounter++;
        }else{
            errorCounter++;
        }
    }

    @Override
    public void close() {
        System.out.println("Successful sent:"+successCounter);
        System.out.println("Failed sent:"+errorCounter);
    }
}
