package com.zhougl.singleton;

/**
 * 饿汉式写法，线程不安全
 * @author zhougl
 * 2019/1/16
 **/
public class SingleInstanceLazy {
    private static SingleInstanceLazy instanceHungry;
    private SingleInstanceLazy(){}
    public static SingleInstanceLazy getInstance(){
        if(instanceHungry == null){
            instanceHungry = new SingleInstanceLazy();
        }
        return instanceHungry;
    }
}
