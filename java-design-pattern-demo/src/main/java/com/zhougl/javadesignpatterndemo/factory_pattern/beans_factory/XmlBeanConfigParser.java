package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 16:43
 */
public class XmlBeanConfigParser implements BeanConfigParser {
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        String content = "";
        return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String content) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        System.out.println(content);
        return beanDefinitions;
    }
}
