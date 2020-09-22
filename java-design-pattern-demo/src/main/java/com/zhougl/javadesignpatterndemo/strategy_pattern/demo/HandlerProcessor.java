package com.zhougl.javadesignpatterndemo.strategy_pattern.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/2 14:38
 */
public class HandlerProcessor implements BeanFactoryPostProcessor {

    private static final String HANDLE_PACKAGE = "com.zhougl.javadesignpatterndemo.strategy_pattern.demo";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Class> handlerMap = new HashMap<>();

        HandlerContext context = new HandlerContext(handlerMap);

        configurableListableBeanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }
}
