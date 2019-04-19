package com.zhougl.distributedtools.controller;

import com.zhougl.distributedtools.limit.RedisLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * @author zhougl
 * 2019/3/17
 **/
@Controller
@RequestMapping("limit")
public class LimitController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedisLimit redisLimit;

    private static Logger logger = LoggerFactory.getLogger(LimitController.class);

    private static final int time = 1000;

    @RequestMapping("v1")
    public void test(){

        Long result;
        RedisAtomicLong atomicLong = new RedisAtomicLong("redis-limit",redisTemplate.getConnectionFactory());
        long andIncrement = atomicLong.getAndIncrement();
        if(andIncrement == 0 && time > 0){
            atomicLong.expire(time, TimeUnit.SECONDS);
        }

        result = andIncrement + 1;
        if(result > time){
            logger.info("访问量超过{}",time);
        }
        logger.info("访问量达到{}",result);


    }

    @RequestMapping("v2")
    public void test1(){
        if(redisLimit.limit()){
            System.out.println("放行");
        }else{
            System.out.println("被限制访问");
        }
    }
}
