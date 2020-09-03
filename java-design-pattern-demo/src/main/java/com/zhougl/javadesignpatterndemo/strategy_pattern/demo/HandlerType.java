package com.zhougl.javadesignpatterndemo.strategy_pattern.demo;

import java.lang.annotation.*;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/2 14:32
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    String value();
}
