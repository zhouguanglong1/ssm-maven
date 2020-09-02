package com.zhougl.test;

/**
 * @author by zhougl
 * @classname TestSingleton
 * @desc TODO
 * @date 2019/5/23 21:48
 */
public class TestSingleton {
    private static TestSingleton ourInstance = new TestSingleton();

    public static TestSingleton getInstance() {
        return ourInstance;
    }

    private TestSingleton() {
    }
}
