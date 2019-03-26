package com.zhougl.kafkademo.controller;

import com.zhougl.kafkademo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhougl
 * 2019/2/14
 **/
@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private Producer producer;

    @GetMapping("publish")
    public void publish(@RequestParam String message){
        this.producer.sendMessage(message);
    }
}
