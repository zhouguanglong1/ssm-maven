package com.zhougl.test.aop;

import java.lang.annotation.*;

/**
 * @author zhougl
 * 2018/8/29
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
