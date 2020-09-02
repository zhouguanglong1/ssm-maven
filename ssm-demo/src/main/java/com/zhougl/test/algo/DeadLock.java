package com.zhougl.test.algo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by zhougl
 * @date 2020/5/21 0:05
 */
public class DeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            synchronized (lock1){
                System.out.println("获得了lock1，等到获取lock2");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){

                    System.out.println("线程1执行了");
                }
            }
        });

        executorService.submit(()->{
            synchronized (lock2){
                System.out.println("获取lock2，准备获取lock1");
                synchronized (lock1){
                    System.out.println("线程2执行了");
                }
            }
        });
    }
}
