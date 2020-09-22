package com.zhougl.jdk;

import java.util.Arrays;

/**
 * @author zhougl
 * @since 2019/9/7 16:57
 */
public class MyArrayList<T> {

    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private static final int DEFAULT_CAPACITY = 10;

    private static final int MAX_CAPACITY = Integer.MAX_VALUE -8;

    private Object[] elementData;
    
    private int size;

    public MyArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }
    
    public MyArrayList(int initCapacity){
        if(initCapacity > 0){
            this.elementData = new Object[initCapacity];
        }else if(initCapacity == 0){
            this.elementData = EMPTY_ELEMENT_DATA;
        }else{
            throw new IllegalArgumentException("array size error!");
        }
    }

    public boolean add(T element){
        checkRangeSize(size + 1);
        elementData[size ++] = element;
        return true;
    }

    public boolean add(int index,T element){
        checkLengthForAdd(index);
        checkRangeSize(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = element;
        size ++;
        return true;
    }

    public T get(int index){
        checkLength(index);
        return elementData(index);
    }

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elementData[index];
    }

    private void checkLength(int index) {
        if(index >= size){
            throw new IndexOutOfBoundsException("index out of bounds!");
        }
    }

    public T set(int index,T element){
        checkLength(index);
        T oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public T remove(int index){
        checkLength(index);
        T oldValue = elementData(index);
        // 移动数组
        int moveNum = size - index -1;
        if(moveNum > 0){
            System.arraycopy(elementData,index + 1,elementData,index,moveNum);
        }
        // 最后一位置空
        elementData[--size] = null;
        return oldValue;
    }

    private void checkLengthForAdd(int index) {
        if(index > size || index < 0){
            throw new IllegalArgumentException("size error!");
        }
    }

    private void checkRangeSize(int minCapacity) {
        int capacity = calculateCapacity(elementData,minCapacity);
        if(capacity > elementData.length){
            // 超过数组长度，需要扩容
            grow(capacity);
        }
    }

    private void grow(int capacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity > MAX_CAPACITY){
            newCapacity = MAX_CAPACITY;
        }else if(newCapacity < capacity){
            newCapacity = capacity;
        }
        elementData = Arrays.copyOf(elementData,newCapacity);
    }

    private int calculateCapacity(Object[] elementData,int minCapacity){
        // 证明是默认无参方式构造的数组，此时使用默认的capacity
        if(elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA){
            return Math.max(minCapacity,DEFAULT_CAPACITY);
        }
        return minCapacity;
    }
}
