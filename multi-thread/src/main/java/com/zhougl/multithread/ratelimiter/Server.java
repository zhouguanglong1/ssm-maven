package com.zhougl.multithread.ratelimiter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhougl
 * 2019/4/19
 **/
public class Server {
    private Semaphore s;
    private long time;
    private int rateLimiter;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Server(Semaphore s, long time, int rateLimiter) {
        this.s = s;
        this.time = time;
        this.rateLimiter = rateLimiter;
    }

    public String remote(){
        long l = System.currentTimeMillis();
        if(l <= time && atomicInteger.getAndIncrement() < rateLimiter){
            try {
                s.acquire();
                System.out.println("remote方法被调用，调用线程为："+Thread.currentThread().getName());
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            return "false";
        }
        return "success";
    }
}
