package com.zhougl.algo;

import lombok.Data;

/**
 * 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 * 5 -> 4 -> 3 -> 2 -> 1 -> NULL
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/8/29 1:07
 */
public class ReverseLinkedList {

    public static Node reverse(Node head){
        Node cur = head;
        Node prev = null;
        Node next;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node4 = new Node(4);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node1 = new Node(1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

//        while (node1 != null){
//            System.out.println(node1.getValue() + "->");
//            node1 = node1.next;
//        }
        System.out.println(node1);

        Node reverse = reverse(node1);

        while (reverse != null){
            System.out.println(reverse.getValue() + "->");
            reverse = reverse.next;
        }
    }


    @Data
    private static class Node{
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }
    }
}
