package com.zhougl.springsessionredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhougl
 * 2019/1/6
 **/
@Configuration
public class SessionRedisConfig {

    /*@Bean
    public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
        RedisHttpSessionConfiguration redisHttpSessionConfiguration = new RedisHttpSessionConfiguration();
        redisHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(600);
        return redisHttpSessionConfiguration;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(100);
        return config;
    }

    @Bean
    public JedisConnectionFactory factory(){

        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("127.0.0.1");
        factory.setPort(6379);
        factory.setPassword("");
        factory.setTimeout(3000);
        factory.setUsePool(true);
        factory.setPoolConfig(jedisPoolConfig());
        return factory;
    }*/

    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy(){
        CookieHttpSessionStrategy strategy=new CookieHttpSessionStrategy();
        DefaultCookieSerializer cookieSerializer=new DefaultCookieSerializer();
        cookieSerializer.setCookieName("MYSESSIONID");//cookies名称
        cookieSerializer.setCookieMaxAge(1800);//过期时间(秒)
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }
}
