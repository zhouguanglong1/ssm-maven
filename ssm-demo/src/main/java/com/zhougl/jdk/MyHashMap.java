package com.zhougl.jdk;

/**
 * @author zhougl
 * @version v1.0.0
 * @date 2019/9/22 19:35
 */
public class MyHashMap {

    // 默认初始容量
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 16

    static final int MAX_INITIAL_CAPACITY = 1 << 30;

    // 扩容加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // 存放key-value对节点的数组
    Node[] table;

    // MyHashMap集合长度（元素的总数量）
    int size;

    int threshold;

    final float loadFactor;

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initCapacity) {
        this(initCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initCapacity, float loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("illegal init capacity number :" + initCapacity);
        }
        if (initCapacity > MAX_INITIAL_CAPACITY) {
            initCapacity = MAX_INITIAL_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("illegal init loadFactor number :" + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initCapacity);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_INITIAL_CAPACITY) ? MAX_INITIAL_CAPACITY : n + 1;
    }

    static final int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    /**
     * 新增key-value对
     *
     * @param key
     * @param value
     * @return
     */
    public Object put(Object key, Object value) {

        return null;
    }

    /**
     * 根据key找到对应的value
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        return null;
    }

    /**
     * 根据key 删除元素
     *
     * @param key
     * @return
     */
    public Object remove(Object key) {
        return null;
    }

    /**
     * 获取链表长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    // hash槽上的数据节点
    private static class Node {

        private Object key;

        private Object value;

        private int hash;

        private Node next;

        public Node(Object key, Object value, int hash, Node next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
