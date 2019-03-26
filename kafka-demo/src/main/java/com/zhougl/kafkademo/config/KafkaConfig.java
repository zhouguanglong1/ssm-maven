package com.zhougl.kafkademo.config;

import org.apache.kafka.clients.producer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author zhougl
 * 2019/2/14
 **/
@Configuration
public class KafkaConfig {
    @Bean
    public Producer<String,String> build(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        Producer<String,String> producer = new KafkaProducer<>(properties);
        // test 设置自定义异步回调
        producer.send(new ProducerRecord<String, String>("zhougl","hahaha"),new SendCallback());
        return producer;
    }

    private class SendCallback implements Callback{

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if(null != recordMetadata){
                System.out.println(recordMetadata.hasOffset()+" "+recordMetadata.topic());
            } else{
                System.out.println(e);
            }
        }
    }
}
