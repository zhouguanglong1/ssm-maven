package com.zhougl.javadesignpatterndemo.strategy_pattern.demo.main;

import com.zhougl.javadesignpatterndemo.strategy_pattern.demo.AbstractHandler;
import com.zhougl.javadesignpatterndemo.strategy_pattern.demo.HandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/2 20:17
 */
@Component
public class UserEntrance {
    @Autowired
    private HandlerContext handlerContext;

    public String handle(String type){
        AbstractHandler instance = handlerContext.getInstance(type);
        return instance.handle(type);
    }
}
