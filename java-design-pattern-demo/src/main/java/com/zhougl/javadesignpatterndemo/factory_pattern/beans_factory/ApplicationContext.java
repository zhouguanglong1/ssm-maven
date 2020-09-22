package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 17:34
 */
public interface ApplicationContext {
    Object getBean(String BeanId);
}
