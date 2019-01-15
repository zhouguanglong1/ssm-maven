package com.zhougl.ssmdemo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("test","zhougl");
		Object test = redisTemplate.opsForValue().get("test");
		System.out.println(test);
	}

	/**
	 * redis八大应用场景
	 * 1，缓存
	 * 2，sorted set实现排行榜
	 * 3，计数器，商品浏览量，视频播放量，使用redis的incr命令实现
	 * 4，分布式会话，基于redis的session服务，把session交给redis管理
	 * 5，分布式锁，并发访问，全局id，减库存，秒杀场景，并发量不大可以用数据库的乐观锁悲观锁实现，并发量大使用redis，redis的setnx功能实现
	 * 6，社交网络中的点赞，关注，共同好友，可以使用redis提供的哈希，集合等数据结构
	 * 7，最新列表，LPUSH可以在列表头部插入内容id为关键字，LTRIM可以限制列表数量，这样列表永远是N个id，保持最新的列表
	 * 8，消息系统，发布/订阅及阻塞队列功能，实现简单的消息队列
	 * redisTemplate.opsForValue();//操作字符串
	 * redisTemplate.opsForHash();//操作hash
	 * redisTemplate.opsForList();//操作list
	 * redisTemplate.opsForSet();//操作set
	 * redisTemplate.opsForZSet();//操作有序set
	 */
	@Test
	public void sortedSetTest(){
		redisTemplate.opsForZSet().add("student","zhougl",88);
		redisTemplate.opsForZSet().add("student","linm",83);
		redisTemplate.opsForZSet().add("student","lisi",86);
		redisTemplate.opsForZSet().add("student","傻逼",98);
		redisTemplate.opsForZSet().add("student","逗比",78);

		Long aLong = redisTemplate.opsForZSet().reverseRank("student", "傻逼");
		System.out.println("傻逼的排名是" + aLong + "位");

		Set student = redisTemplate.opsForZSet().rangeByScore("student", 0, 100);
		System.out.println("分数从0到100的升序排名为："+student);
		Set student1 = redisTemplate.opsForZSet().reverseRangeByScore("student", 0, 100);
		System.out.println("分数从0到100的降序排名为："+student1);
		Set<ZSetOperations.TypedTuple> student2 = redisTemplate.opsForZSet().rangeByScoreWithScores("student", 0, 100);
		System.out.println("分数从0到100的升序排名，并输出分数：");
		for (ZSetOperations.TypedTuple typedTuple : student2) {
			Double score = typedTuple.getScore();
			Object value = typedTuple.getValue();
			System.out.println(score);
			System.out.println(value);
		}
//		System.out.println(student2);
		System.out.println("分数从0到100的升序倒数前三名，并输出分数：");
		Set<ZSetOperations.TypedTuple> student3 = redisTemplate.opsForZSet().rangeWithScores("student", 0, 2);
		for (ZSetOperations.TypedTuple typedTuple : student3) {
			System.out.println(typedTuple.getScore());
			System.out.println(typedTuple.getValue());
		}

	}

	/**
	 * incr操作可以用来记录点赞数，关注数
	 */
	@Test
	public void testZIncrBy(){
		Double aDouble = redisTemplate.opsForZSet().incrementScore("vote", "Alice", -1);
		Double bDouble = redisTemplate.opsForZSet().incrementScore("vote", "Micheal", 1);
		Double cDouble = redisTemplate.opsForZSet().incrementScore("vote", "Kobe", 2);
		Double dDouble = redisTemplate.opsForZSet().incrementScore("vote", "Paul", 3);

		Double score = redisTemplate.opsForZSet().score("vote", "Alice");
		System.out.println(score);

		redisTemplate.opsForZSet().incrementScore("vote", "Alice", -1);

		Double score1 = redisTemplate.opsForZSet().score("vote", "Alice");
		System.out.println(score1);

		Long vote = redisTemplate.opsForZSet().zCard("vote");
		System.out.println(vote);
	}


	@Test
	public void testSet(){
		redisTemplate.opsForSet().add("set1","value1","value2","value3");
		Set set1 = redisTemplate.opsForSet().members("set1");
		System.out.println(set1);
	}

	@Test
	public void selectAllKey(){
		Set keys = redisTemplate.keys("*");
		System.out.println(keys);
	}

	@Test
	public void kk(){
//		redisTemplate.delete("set1");
		Set keys = redisTemplate.keys("*");
		redisTemplate.delete(keys);
	}

}
