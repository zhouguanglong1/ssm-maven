package com.zhougl.javadesignpatterndemo.strategy_pattern.demo.concrete;

import com.zhougl.javadesignpatterndemo.strategy_pattern.demo.AbstractHandler;
import com.zhougl.javadesignpatterndemo.strategy_pattern.demo.HandlerType;
import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/2 20:04
 */
@Component
@HandlerType("1")
public class NormalHandler extends AbstractHandler {
    @Override
    public String handle(Object o) {
        return "处理普通事件";
    }
}
