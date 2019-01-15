package com.zhougl.handler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

/**
 * @author zhougl
 * 2018/12/13
 **/
public class TestJedis {
    public static void main(String[] args){
//        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
//            jedis.set("fuck", "hahah");
//            System.out.println(jedis.get("fuck"));
//            jedis.del("fuck");
//            Map<String,String> countMap = new HashMap<>();
//            countMap.put("哈哈哈","1");
//            jedis.hmset("test",countMap);
//            jedis.hset("test","哈哈哈","2");
//            System.out.println(jedis.hget("test", "哈哈哈"));
//            System.out.println(jedis.hvals("test"));

//        }
        Jedis jedis = RedisUtils.getJedis();
//        jedis.hset("hahaha","shit","1");
//        String hget = jedis.hget("hahaha", "fuck");
//        jedis.hincrBy("hahaha","shit",1);
        jedis.hincrBy("hahaha1","shit",1);
//        jedis.hincrBy("hahaha","shit2",1);
        String hget = jedis.hget("hahaha1", "shit");
//        String hget2 = jedis.hget("hahaha", "shit2");
        System.out.println(hget);
//        System.out.println(hget2);
        if(hget == null){
            System.out.println("fsdfds");
        }
//        System.out.println(jedis.hget("test", "哈哈哈"));
//        Map<String, String> test = jedis.hgetAll("test");
//        test.put("嘻嘻嘻","2");
//        jedis.hmset("test",test);
//        System.out.println(jedis.hgetAll("test"));
//        jedis.hmset("test",)
//        System.out.println(jedis.exists("dsjfksdf"));
//        RedisUtils.returnResource(jedis);
//        Set<String> allItem = jedis.keys("item_*");
//        allItem.forEach(System.out::println);
        /*jedis.zadd("range_rank",Double.parseDouble("2"),"1234");
        jedis.zadd("range_rank",Double.parseDouble("3"),"12345");
        Set<String> range_rank = jedis.zrevrange("item_73296648401004280074440105", 0, 1);
        // 输出元素和对应的分值
        Set<Tuple> tupleSet = jedis.zrevrangeWithScores("item_73296648401004280074440105", 0, 1);
        for (Tuple tuple : tupleSet) {
            String element = tuple.getElement();
            double score = tuple.getScore();
            System.out.println(element + "---" + score);
        }
        range_rank.forEach(System.out::println);

        List<String> list = new ArrayList<>();
        list.addAll(range_rank);
        System.out.println(list);*/
        RedisUtils.returnResource(jedis);
    }
}
