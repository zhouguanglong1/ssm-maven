package com.zhougl.distributedtools.annotation;

import java.lang.annotation.*;

/**
 * @author zhougl
 * 2019/3/17
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLimit {
}
