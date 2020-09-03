package com.zhougl.javadesignpatterndemo.strategy_pattern.demo.concrete;

import com.zhougl.javadesignpatterndemo.strategy_pattern.demo.AbstractHandler;
import com.zhougl.javadesignpatterndemo.strategy_pattern.demo.HandlerType;
import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/2 20:06
 */
@Component
@HandlerType("2")
public class SpecialHandler extends AbstractHandler {
    @Override
    public String handle(Object o) {
        return "处理特殊事件";
    }
}
