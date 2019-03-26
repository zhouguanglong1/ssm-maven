package com.zhougl.heartbeatdemo.client;

import com.zhougl.heartbeatdemo.protocol.CustomProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhougl
 * 2019/2/13
 **/
@Configuration
public class HeartBeatConfig{

    @Value("${channel.id}")
    private long id;

    @Bean(value = "heartBeat")
    public CustomProtocol heartBeat(){
        return new CustomProtocol(id,"ping");
    }

}
