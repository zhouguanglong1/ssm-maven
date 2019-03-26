package com.zhougl.singleton;

/**
 * JVM指令重排的问题
 * 使用volatile可以禁止指令重排，让这句instanceHungry = new SingleInstanceLazySyncDoubleCheck();的执行变成原子操作
 * @author zhougl
 * 2019/1/16
 **/
public class SingleInstanceLazySyncDoubleCheck {
    private static volatile SingleInstanceLazySyncDoubleCheck instanceHungry;
    private SingleInstanceLazySyncDoubleCheck(){}
    public static SingleInstanceLazySyncDoubleCheck getInstance(){
        if(instanceHungry == null){
            synchronized (SingleInstanceLazySyncDoubleCheck.class){
                if(instanceHungry == null){
                    instanceHungry = new SingleInstanceLazySyncDoubleCheck();
                }
            }
        }
        return instanceHungry;
    }
}
