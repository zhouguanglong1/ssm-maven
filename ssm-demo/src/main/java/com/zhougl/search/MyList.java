package com.zhougl.search;

/**
 * @author zhougl
 * 2019/1/18
 **/
public class MyList<T> {
    private static class Node<T>{
        private Node<T> next;
        private T data;

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public MyList() {
        head = tail = null;
    }

    public void addHeader(T data){
        this.head = new Node<T>(data);
        if(tail == null){
            tail = head;
        }
    }

    public void addTail(T data){
        this.tail = new Node<>(data);
        head.next = tail;
    }

    public void insert(T data){
        if (this.head == null) {
            addHeader(data);

        } else if (this.tail == this.head) {
            addTail(data);
        } else {
            Node<T> preNext = head.next;
            @SuppressWarnings({ "unchecked", "rawtypes" })
            Node<T> newNode = new Node(data);
            this.head.next = newNode;
            newNode.next = preNext;
        }
    }

    public void printLinkList() {    //打印链表
        Node<T> curr = this.head;
        if (isEmpty()) {
            try {
                throw new Exception("linklist is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while(curr != null){
            System.out.print(curr.data+" ");
            curr = curr.next;
        }
    }

    public void delete(T data){//删除某一节点
        Node<T> curr = head, prev = null;
        boolean suc = false;//是否删除成功标志
        while (curr != null) {
            if (curr.data.equals(data)) {
                //判断是什么节点
                if (curr == head) {   //如果删除的是头节点
                    System.out.println('\n'+"delete head node");
                    head = curr.next;
                    suc = true;
                    return;
                }
                if (curr == tail) { //如果删除的是尾节点
                    System.out.println('\n'+"delete tail node");
                    tail = prev;
                    prev.next = null;
                    suc = true;
                    return;
                } else {//如果删除的是中间节点（即非头节点或非尾节点）
                    System.out.println('\n'+"delete center node");
                    prev.next = curr.next;
                    suc = true;
                    return;
                }
            }

            prev = curr;
            curr = curr.next;
        }

        if(suc == false) {
            System.out.println('\n'+"没有这个数据");
        }

    }

    public boolean isEmpty(){//判断链表是否为空
        return this.head == null || this.tail == null;
    }

    public MyList(Node<T> header, Node<T> tail) {
        this.head = header;
        this.tail = tail;
    }

    public static void main(String[] args) {
        MyList<Integer> mylist = new MyList<Integer>();//构造一个空链表
        mylist.insert(5);
        mylist.insert(6);
        mylist.insert(7);
        mylist.insert(3);
        mylist.printLinkList();
        mylist.delete(1);
        mylist.printLinkList();
        mylist.delete(5);
        mylist.printLinkList();
        mylist.delete(6);
        mylist.printLinkList();
    }
}
