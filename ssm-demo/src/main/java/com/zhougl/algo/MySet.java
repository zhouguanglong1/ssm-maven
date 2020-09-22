package com.zhougl.algo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 实现一个不包含重复元素的集合
 * 思路一：使用数组，for循环判断
 * 思路二：手写一个hashmap来存Set的值，空间换时间
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/7/14 0:54
 */
public class MySet implements Set {

    private Object[] objects;

    public MySet(){
        objects = new Object[10];
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {

        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
