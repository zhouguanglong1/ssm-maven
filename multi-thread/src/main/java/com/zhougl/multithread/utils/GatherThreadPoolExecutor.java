package com.zhougl.multithread.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhougl
 * 2019/4/19
 **/
public class GatherThreadPoolExecutor {
    private static Logger log = LoggerFactory.getLogger(GatherThreadPoolExecutor.class);
    private static volatile GatherThreadPoolExecutor instance = null;

    public ThreadPoolExecutor pool = null;
    private static final int keepAliveTime = 5;
    private static final int corePoolSize = 16;
    private static final int workQueue_num = 16;
    public static final int maximumPoolSize = 32;

    private GatherThreadPoolExecutor() {
        init();
    }

    //双重检查锁-单例
    public static GatherThreadPoolExecutor getInstance(){
        if(instance == null){
            synchronized (GatherThreadPoolExecutor.class){
                if(instance == null){
                    instance = new GatherThreadPoolExecutor();
                }
            }
        }
        return instance;
    }

    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----16 maximumPoolSize 最大线程池大小----32 keepAliveTime
     * 线程池中超过corePoolSize数目的空闲线程最大存活时间----5+单位 TimeUnit TimeUnit
     * keepAliveTime时间单位----TimeUnit.MINUTES workQueue 阻塞队列----new
     * ArrayBlockingQueue<Runnable>(16)====16容量的阻塞队列 threadFactory 新建线程工厂----new
     * GatherThreadFactory()====定制的线程工厂 rejectedExecutionHandler
     * 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 即当提交第48个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        if (this.pool == null)
            pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES,
                    new ArrayBlockingQueue<Runnable>(workQueue_num), new GatherThreadFactory(),
                    new GatherRejectedExecutionHandler());

    }

    public void destory() {
        if (pool != null) {
            pool.shutdown();
        }
    }

    public int getActiveCount() {
        return pool.getActiveCount();
    }

    public ExecutorService getCustomThreadPoolExecutor() {
        log.debug("当前活动线程数量:" + pool.getActiveCount());
        return this.pool;
    }

    private class GatherThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = GatherThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            log.debug(threadName);
            t.setName(threadName);
            return t;
        }
    }

    private class GatherRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.warn("---当前线程数量超过设置的阀值---");
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.error("*-执行线程异常-*", e);
            }
        }
    }

    // 测试构造的线程池
    public static void main(String[] args) {
        GatherThreadPoolExecutor exec = getInstance();

        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        for (int i = 1; i < 100; i++) {
            System.out.println("提交第" + i + "个任务!");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running=====");
                }
            });
        }

        // exec.destory();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
