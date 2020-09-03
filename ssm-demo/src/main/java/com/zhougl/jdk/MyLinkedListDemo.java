package com.zhougl.jdk;

/**
 * @author zhougl
 * @since 2019/9/8 19:41
 */
public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("hello");
        linkedList.add("world");

        linkedList.add(1,"滴普");

        linkedList.set(1,"科技");

        linkedList.remove(2);
        String s = linkedList.get(1);
        System.out.println(s);
        int size = linkedList.size();
        System.out.println(size);

        linkedList.clear();
    }
}
