package com.zhougl.jdk;

/**
 * @author zhougl
 * @since 2019/9/8 15:57
 */
public class MyArrayListDemo {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>(5);
        list.add("hello");
        list.add("world");
        list.add("world");
        list.add("world");
        list.add("world");

        // 此处触发扩容
        list.add("world");
        list.set(5,"world1");

        list.add(6,"滴普");

        list.remove(6);
    }
}
