package com.zhougl.distributedtools.config;

import com.zhougl.distributedtools.constant.RedisToolsConstant;
import com.zhougl.distributedtools.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author zhougl
 * 2019/4/2
 **/
@Configuration
public class RedisLockConfig {
    private Logger logger = LoggerFactory.getLogger(RedisLockConfig.class);


    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisLock build() {
        RedisLock redisLock = new RedisLock.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE)
                .lockPrefix("lock_")
                .sleepTime(100)
                .build();

        return redisLock;
    }
}
