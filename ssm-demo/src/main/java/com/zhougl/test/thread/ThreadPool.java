package com.zhougl.test.thread;

/**
 * @author zhougl
 * 2019/2/8
 **/
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    void removeWorker(int num);
    int getJobSize();
}
