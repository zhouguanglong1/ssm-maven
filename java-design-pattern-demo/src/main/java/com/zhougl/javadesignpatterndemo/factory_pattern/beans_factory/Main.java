package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 17:34
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Object rateLimiter = applicationContext.getBean("rateLimiter");
        // 使用该bean
    }
}
