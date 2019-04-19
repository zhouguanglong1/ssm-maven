package com.zhougl.distributedtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(value = "com.zhougl.distributedtools")
public class DistributedToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedToolsApplication.class, args);
	}

}
