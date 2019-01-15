package com.zhougl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author zhougl
 * 2018/8/24
 **/
@SpringBootApplication
@EnableCaching
public class BootApplication {

    public static void main(String[] args){
        SpringApplication.run(BootApplication.class,args);
    }

}
