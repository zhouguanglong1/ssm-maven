package com.zhougl.test.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/9/21 11:42
 */
public class LRUCache {

    private int capacity;
    private Node head;
    private Node tail;
    private Map<Integer,Node> map;


    private class Node{
        private int key;
        private int value;
        private Node pre = null;
        private Node next = null;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.pre = head;
    }



    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        // 删除node节点
        if(node.pre != null){
            node.pre.next = node.next;
        }
        if(node.next != null){
            node.next.pre = node.pre;
        }
        // 把node节点移动到尾部
        moveToTail(node);
        return node.value;
    }

    private void moveToTail(Node node) {
        node.pre = tail.pre;
        tail.pre = node;
        node.pre.next = node.next;
        node.next = tail;

    }

    public void put(int key,int value){
        if(get(key)!= -1){
            map.get(key).value= value;
            return;
        }

        Node node = new Node(key,value);
        map.put(key,node);
        moveToTail(node);
        if(map.size() > capacity){
            // 删除首节点
            if(head.next != null){
                map.remove(head.next.key);
                head.next = head.next.next;
                head.next.pre = head;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
    }
}
