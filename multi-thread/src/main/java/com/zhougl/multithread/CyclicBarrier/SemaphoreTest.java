package com.zhougl.multithread.CyclicBarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量是用来控制同时访问特定资源的线程数量，可用于流量控制
 * @author zhougl
 * 2019/4/14
 **/
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    // 只允许10个并发
    private static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args){
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(50);
                    System.out.println("save data...");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
