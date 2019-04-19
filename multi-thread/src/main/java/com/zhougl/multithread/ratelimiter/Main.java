package com.zhougl.multithread.ratelimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhougl
 * 2019/4/19
 **/
public class Main {
    public static void main(String[] args){
        int threadNums = 10;
        int rateLimiter = 5;
        long time = 1000;
        ExecutorService service = Executors.newFixedThreadPool(threadNums);
        CountDownLatch countDownLatch = new CountDownLatch(threadNums);
        Semaphore semaphore = new Semaphore(rateLimiter);

        long currentTimeMillis = System.currentTimeMillis();
        Server server = new Server(semaphore,currentTimeMillis+time,rateLimiter);
        List<Future<?>> result = new ArrayList<>();

        for (int i = 0; i < threadNums; i++) {
//            service.submit();
            Future<?> submit = service.submit(new Client(server));
            result.add(submit);
//            Thread thread = new Thread(new Client(countDownLatch,server));
//            thread.setName("线程-帅哥"+i+"号");
//            thread.start();
            countDownLatch.countDown();
        }

        try {
            countDownLatch.await();
            for (Future<?> future : result) {
                System.out.println("调用结果："+future.get());
            }
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
