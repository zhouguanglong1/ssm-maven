package com.zhougl.singleton;

/**
 * 懒汉式
 * 在getInstance()方法增加synchronized，线程安全，但是影响效率
 * @author zhougl
 * 2019/1/16
 **/
public class SingleInstanceLazySync {
    private static SingleInstanceLazySync instanceHungry;
    private SingleInstanceLazySync(){}
    public static synchronized SingleInstanceLazySync getInstance(){
        if(instanceHungry == null){
            instanceHungry = new SingleInstanceLazySync();
        }
        return instanceHungry;
    }
}
