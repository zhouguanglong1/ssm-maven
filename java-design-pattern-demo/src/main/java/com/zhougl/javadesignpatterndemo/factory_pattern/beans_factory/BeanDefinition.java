package com.zhougl.javadesignpatterndemo.factory_pattern.beans_factory;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/16 16:32
 */
@Data
public class BeanDefinition {
    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false;

    public boolean isSingleton(){
        return scope.equals(Scope.SINGLETON);
    }

    public static enum Scope {
        SINGLETON,
        PROTOTYPE;
    }

    @Data
    public static class ConstructorArg {
        private boolean isRef;
        private Class type;
        private Object arg;
    }
}
