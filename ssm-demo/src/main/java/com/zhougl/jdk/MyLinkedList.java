package com.zhougl.jdk;

/**
 * @author zhougl
 * @since 2019/9/8 16:07
 */
public class MyLinkedList<T> {

    private Node<T> first;

    private Node<T> last;

    private int size;

    public MyLinkedList() {
    }

    public boolean add(T element){
        addLinkLast(element);
        return true;
    }

    private void addLinkLast(T element) {
        Node<T> prev = last;
        Node<T> node = new Node<>(element,prev,null);
        last = node;
        if(prev == null){
            first = node;
        }else {
            prev.next = node;
        }
        size ++;
    }

    private void addLinkBefore(T element,Node<T> after){
        Node<T> before = after.prev;
        Node<T> node = new Node<>(element,before,after);
        if(before == null){
            first = node;
        }else{
            before.next = node;
        }
        after.prev = node;
        size ++;
    }

    public boolean add(int index,T element){
        checkLength(index);
        if(index == size){
            addLinkLast(element);
        }else{
            addLinkBefore(element,node(index));
        }
        return true;
    }

    public T get(int index){
        checkPositionLength(index);
        Node<T> node = node(index);
        return node.element;
    }

    public T set(int index,T element){
        checkLength(index);
        Node<T> node = node(index);

        // 此处理解有错误
//        Node<T> prev = node.prev;
//        Node<T> next = node.next;
//
//        Node<T> newNode = new Node<>(element,null,null);
//        if(prev == null){
//            first = newNode;
//        }else{
//            prev.next = newNode;
//            newNode.prev = prev;
//        }
//
//        if(next == null){
//            last = newNode;
//        }else{
//            next.prev = newNode;
//            newNode.next = next;
//        }
//
//        T oldValue = node.element;
//        node.next = null;
//        node.prev = null;
//        node.element = null;
        T oldValue = node.element;

        node.element = element;

        return oldValue;
    }

    private Node<T> node(int index) {
        if(index < (size >> 1)){
            Node<T> x = first;
            for (int i = 0;i<=index;i++){
                x = x.next;
            }
            return x;
        }else{
            Node<T> x = last;
            for (int i = size-1;i> index;i--){
                x = x.prev;
            }
            return x;
        }
    }

    public int size(){
        return size;
    }

    public void clear(){
        for (Node<T> x = first;x!=null;){
            Node<T> next = x.next;
            x.element = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public T remove(int index){
        checkPositionLength(index);
        Node<T> node = node(index);

        T element = node.element;

        unlinkNode(node);

        return element;
    }

    private void unlinkNode(Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if(prev == null){
            first = next;
        }else{
            prev.next = next;
            node.prev = null;
        }

        if(next == null){
            last = prev;
        }else{
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        size --;
    }

    private void checkPositionLength(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException(index + " out of list length :"+ size);
        }
    }

    private void checkLength(int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException(index + " out of list length :"+ size);
        }
    }


    private static class Node<T>{
        T element;
        Node<T> prev;
        Node<T> next;

        Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
