package com.zhougl.jdk;

/**
 * @author zhougl
 * @version v1.0.0
 * @date 2019/9/10 16:19
 */
public class MyArrayBlockingQueue<T> {
    private int count = 0;

    private Object[] items;

    /**
     * 队列满的时候的阻塞锁
     */
    private Object full = new Object();

    /**
     * 队列空的时候的阻塞锁
     */
    private Object empty = new Object();

    /**
     * 写入时的索引
     */
    private int putIndex;

    /**
     * 获取时的索引
     */
    private int getIndex;

    public MyArrayBlockingQueue(int size) {
        this.items = new Object[size];
    }

    /**
     * 往队列尾插入数据
     * @param element
     */
    public void put(T element){
        synchronized (full){
            while (count == items.length){
                try {
                    full.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        synchronized (empty){
            items[putIndex] = element;
            count++;
            putIndex++;
            if(putIndex == items.length){
                putIndex = 0;
            }
            // 写入线程唤醒获取线程
            empty.notify();
        }
    }

    /**
     * 从队列头获取数据
     * @return
     */
    public T get(){
        synchronized (empty){
            while (count == 0){
                try {
                    empty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (full){
            Object item = items[getIndex];
            items[getIndex] = null;
            count --;
            getIndex ++;
            if(getIndex == items.length){
                getIndex = 0;
            }
            // 获取线程唤醒写入线程
            full.notify();
            return (T)item;
        }
    }
}
