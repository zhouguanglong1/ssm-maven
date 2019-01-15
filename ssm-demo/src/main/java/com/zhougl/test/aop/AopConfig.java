package com.zhougl.test.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhougl
 * 2018/8/29
 **/
@Configuration
@ComponentScan("com.zhougl.test.aop")
@EnableAspectJAutoProxy
public class AopConfig {

}
