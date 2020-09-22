package com.zhougl.test.algo;

/**
 * 合并两个有序链表
 * 方法一：递归
 * 方法二：哨兵节点
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/9/9 11:29
 */
public class MergeSortedLinkedList {
    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        Node node4 = new Node(1);
        Node node5 = new Node(3);
        Node node6 = new Node(4);
        node4.next = node5;
        node5.next = node6;
        Node mergeSortedLinkedList = getMergeSortedLinkedList(node1, node4);
        System.out.println(mergeSortedLinkedList);


        System.out.println(1 >> 16);
    }

    public static Node getMergeSortedLinkedListByRecur(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        } else if (node1.val < node2.val) {
            node1.next = getMergeSortedLinkedListByRecur(node1.next, node2);
            return node1;
        } else {
            node2.next = getMergeSortedLinkedListByRecur(node1, node2.next);
            return node2;
        }
    }

    public static Node getMergeSortedLinkedList(Node node1, Node node2) {

        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        Node nodeHead1 = node1;
        Node nodeHead2 = node2;
        Node mergedNode = new Node();
        Node prev = mergedNode;

        while (nodeHead1 != null && nodeHead2 != null) {
            if (nodeHead1.val < nodeHead2.val) {
                prev.next = nodeHead1;
                nodeHead1 = nodeHead1.next;
            } else {
                prev.next = nodeHead2;
                nodeHead2 = nodeHead2.next;
            }
            prev = prev.next;
        }

        if (nodeHead1 != null) {
            prev.next = nodeHead1;
        }

        if (nodeHead2 != null) {
            prev.next = nodeHead2;
        }

        return mergedNode.next;
    }

    public static class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
