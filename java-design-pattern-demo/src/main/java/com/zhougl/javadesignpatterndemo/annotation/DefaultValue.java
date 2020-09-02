package com.zhougl.javadesignpatterndemo.annotation;

import java.lang.annotation.*;

/**
 * @author by zhougl
 * @classname DefaultValue
 * @desc TODO
 * @date 2019/5/16 17:05
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultValue {
    String value() default "";
}
