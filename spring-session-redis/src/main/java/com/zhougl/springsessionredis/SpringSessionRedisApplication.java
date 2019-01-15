package com.zhougl.springsessionredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class SpringSessionRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSessionRedisApplication.class, args);
	}

}

