package com.zhougl.javadesignpatterndemo.strategy_pattern.demo;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/2 19:58
 */
@Component
public class HandlerContext {
    private Map<String, Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getInstance(String type) {
        Class aClass = handlerMap.get(type);
        if (aClass == null) {
            throw new RuntimeException();
        }

        return (AbstractHandler) SpringContextHolder.getBean(aClass);
    }
}
