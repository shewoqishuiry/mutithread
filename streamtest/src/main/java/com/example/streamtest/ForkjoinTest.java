package com.example.streamtest;


import java.util.concurrent.RecursiveTask;

//1、forkjoinPool
//2、计算任务 forkjoinPool.execute(ForkJoinTask task)
//3、ForkjoinTask
public class ForkjoinTest extends RecursiveTask <Long>{

    private Long start;
    private Long end;

    private Long temp = 10_000L;

    public ForkjoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        Long sum = 0L;
        if ((end-start) > temp) {
            for (long i = start; i < end; i++) {
                sum+=i;
            }
        } else { //本质是递归
            long middle = (start + end) /2;
            ForkjoinTest task1 = new ForkjoinTest(start,middle);
            task1.fork(); //拆分任务，把任务压入线程
            ForkjoinTest task2 = new ForkjoinTest(start,middle);
            task2.fork();//拆分任务，把任务压入线程

            sum = task1.join()+task2.join(); //合并结果
        }
        return sum;
    }
}
