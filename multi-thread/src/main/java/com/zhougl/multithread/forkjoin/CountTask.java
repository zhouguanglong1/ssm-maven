package com.zhougl.multithread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhougl
 * 2019/4/14
 **/
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean isDepart = end - start <= THRESHOLD;
        if(isDepart){
            for(int i = start;i<=end;i++){
                sum += i;
            }
        }else{
            int middle = (start+end)/2;
            CountTask task = new CountTask(start,middle);
            CountTask task1 = new CountTask(middle+1,end);
            task.fork();
            task1.fork();

            Integer join = task.join();
            Integer join1 = task1.join();
            sum = join + join1;
        }

        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = pool.submit(new CountTask(1, 6));
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
