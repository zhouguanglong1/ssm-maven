package com.zhougl.kafkademo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


/**
 * @author zhougl
 * 2019/2/14
 **/
@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "zhougl")
    public void consume(ConsumerRecord<String, String> record) {
        logger.info("#### -> Consumed topic -> {}",record.topic());
        logger.info("#### -> Consumed message -> {}",record.value());
    }
}
