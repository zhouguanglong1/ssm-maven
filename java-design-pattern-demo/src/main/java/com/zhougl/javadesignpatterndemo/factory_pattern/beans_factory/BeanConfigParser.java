package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

import java.beans.beancontext.BeanContext;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 16:31
 */
public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String path);
}
