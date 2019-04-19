package com.zhougl.distributedtools.config;

import com.zhougl.distributedtools.constant.RedisToolsConstant;
import com.zhougl.distributedtools.limit.RedisLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author zhougl
 * 2019/4/6
 **/
@Configuration
public class RedisLimitConfig {
    private Logger logger = LoggerFactory.getLogger(RedisLimitConfig.class);

    @Value("${redis.limit}")
    private int limit;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisLimit build1() {
        RedisLimit redisLimit = new RedisLimit.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE)
                .limit(limit)
                .build();
        return redisLimit;
    }
}
