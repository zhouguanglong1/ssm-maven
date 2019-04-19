package com.zhougl.multithread.CyclicBarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 银行流水处理，多线程计算，合并计算结果
 * 线程池配置注意事项：
 * CPU密集型 N+1个线程的线程池 N是指CPU的核心数
 * IO密集型 2N个线程的线程池
 * @author zhougl
 * 2019/4/14
 **/
public class BankWaterService implements Runnable {

    // 创建四个屏障，处理完后执行当前类的run方法
    private CyclicBarrier c = new CyclicBarrier(4,this);

    // 启动4个线程
    private ExecutorService executors = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();


    private void count(){
        for (int i = 0; i < 4; i++) {
            executors.submit(() -> {
               sheetBankWaterCount.put(Thread.currentThread().getName(),1);
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executors.shutdown();
    }


    @Override
    public void run() {
        int result = 0;
        // 汇总每个子任务的结果
        for (Map.Entry<String, Integer> entry : sheetBankWaterCount.entrySet()) {
            result += entry.getValue();
        }
        sheetBankWaterCount.put("result",result);
        System.out.println(result);
    }

    public static void main(String[] args){
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
