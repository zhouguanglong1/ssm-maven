package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 17:35
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeansFactory beansFactory;

    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);
    }

    @Override
    public Object getBean(String beanId) {
        return beansFactory.getBean(beanId);
    }

    private void loadBeanDefinitions(String configLocation) {
        InputStream stream = null;
        try {
            stream = this.getClass().getResourceAsStream("/" + configLocation);
            if (stream == null) {
                throw new RuntimeException();
            }

            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(stream);
            beansFactory.addBeanDefinitions(beanDefinitions);

        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) { // TODO: log error } }
                }
            }
        }
    }
}