package com.zhougl.singleton;

/**
 * @author zhougl
 * 内部类实现单例方式
 * 从内部看是饿汉式
 * 在SingletonHolder初始化时会由ClassLoader来保证同步
 * 从外部看，INSTANCE确实是在getInstance()被调用时才加载，这也算是懒汉式
 * 2019/1/16
 **/
public class SingleInstance {
    private static class SingletonHolder{
        private static final SingleInstance INSTANCE = new SingleInstance();
    }
    private SingleInstance(){}
    public static SingleInstance getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
