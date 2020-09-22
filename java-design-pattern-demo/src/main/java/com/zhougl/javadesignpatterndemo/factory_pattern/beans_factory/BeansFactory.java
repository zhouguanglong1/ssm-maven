package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 16:53
 */
public class BeansFactory {
    /**
     * 单例缓存map
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /**
     * bean定义缓存map
     */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionsMap = new ConcurrentHashMap<>();

    /**
     * 将解析完的bean定义数组加到缓存中
     *
     * @param beanDefinitions bean数组
     */
    public void addBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            beanDefinitionsMap.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }

        for (BeanDefinition beanDefinition : beanDefinitions) {
            // 如果不是懒加载，并且是单例模式，需要提前创建
            if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()) {
                createBean(beanDefinition);
            }
        }
    }

    public Object getBean(String beanId) {
        // 先获取定义
        BeanDefinition beanDefinition = beanDefinitionsMap.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("no such bean");
        }

        return createBean(beanDefinition);
    }

    /**
     * 创建bean
     *
     * @param beanDefinition bean定义
     * @return bean
     */
    private Object createBean(BeanDefinition beanDefinition) {
        // 单例直接从缓存集合中返回
        if (beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.getId())) {
            return singletonObjects.get(beanDefinition.getId());
        }

        Object bean = null;
        try {
            Class<?> beanClass = Class.forName(beanDefinition.getClassName());
            List<BeanDefinition.ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
            // 无参构造
            if (CollectionUtils.isEmpty(constructorArgs)) {
                bean = beanClass.newInstance();
            } else {
                // 使用反射 设置构造函数中的参数
                int size = constructorArgs.size();
                Class[] argClasses = new Class[size];
                Object[] argObjects = new Class[size];
                for (int i = 0; i < size; i++) {
                    BeanDefinition.ConstructorArg constructorArg = constructorArgs.get(i);
                    if (!constructorArg.isRef()) {
                        argClasses[i] = constructorArg.getType();
                        argObjects[i] = constructorArg.getArg();
                    } else {
                        BeanDefinition definition = beanDefinitionsMap.get(constructorArg.getArg());
                        if (definition == null) {
                            throw new NoSuchBeanDefinitionException("no such bean");
                        }
                        argClasses[i] = Class.forName(definition.getClassName());
                        argObjects[i] = createBean(definition);
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }

        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new BeanCreationException("bean create failed", e);
        }

        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.putIfAbsent(beanDefinition.getId(), bean);
        }
        return bean;
    }
}
