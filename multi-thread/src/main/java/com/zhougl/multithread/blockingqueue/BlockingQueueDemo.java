package com.zhougl.multithread.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zhougl
 * 2018/12/9
 **/
public class BlockingQueueDemo {
    private static LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Producer("生产者1"));
        service.submit(new Producer("生产者2"));
        service.submit(new Producer("生产者3"));
        service.submit(new Consumer("生产者1"));
        service.submit(new Consumer("生产者2"));
        service.submit(new Consumer("生产者3"));
        service.shutdown();
    }

    static class Producer implements Runnable{

        String name;

        private Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("生产数据"+ i);
                try {
                    blockingDeque.put("数据" + i);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        private String name;

        private Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("消费数据" + blockingDeque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
