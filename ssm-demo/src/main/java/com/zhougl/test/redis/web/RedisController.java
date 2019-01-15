package com.zhougl.test.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhougl
 * 2018/9/14
 **/
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/redisData")
    public Object redisData(){
        redisTemplate.delete("set");
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","zhougl");
        map.put("age",23);
        map.put("address","guangzhou");
        Map<String,Object> map2 = new HashMap<>();
        map2.put("id",2);
        map2.put("name","linm");
        map2.put("age",22);
        map2.put("address","shantou");
        Map<String,Object> map3 = new HashMap<>();
        map3.put("id",3);
        map3.put("name","xiaohu");
        map3.put("age",1);
        map3.put("address","shenzhen");
        zSetOperations.add("set",map, Double.parseDouble(map.get("id").toString()));
        zSetOperations.add("set",map2, Double.parseDouble(map2.get("id").toString()));
        zSetOperations.add("set",map3, Double.parseDouble(map3.get("id").toString()));

        Set set = zSetOperations.rangeByScore("set", 1, 2);
        Set set2 = zSetOperations.reverseRangeByScore("set", 1, 3);
        Set set1 = zSetOperations.range("set", 0, 10);
        Set set3 = zSetOperations.reverseRange("set", 0, 10);
        System.out.println(set3);
        return set3;
    }
}
