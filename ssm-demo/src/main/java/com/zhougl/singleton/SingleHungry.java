package com.zhougl.singleton;

/**
 * 饿汉式
 * 优点：占用资源少的时候，不依赖其他数据的时候，完美，并且不存在线程安全问题
 * 缺点：1.初始化太早，资源浪费
 *      2.初始化时机很难把握，很难保证依赖的其他数据能准备好
 * @author zhougl
 * 2019/1/16
 **/
public class SingleHungry {
    private static final SingleHungry INSTANCE = new SingleHungry();
    private SingleHungry(){}
    public static SingleHungry getInstance(){
        return INSTANCE;
    }
}
