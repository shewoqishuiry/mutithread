package com.example.streamtest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 *
 */
public class Test {
    public static void main(String[] args) {
        test1(); //7099ms
        test2(); //6292ms
        test3(); //184ms
    }

    //普通程序员
    public static void test1() {
        Long sum = 0L;
        Long start = System.currentTimeMillis();
        for (long i = 0; i < 1000000000; i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum+=："+ sum + ",时间：" +(end-start));
    }

    //会forkjoin的程序员
    public static void test2() {
        Long sum = 0L;
        Long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkjoinTest forkjoinTest = new ForkjoinTest(0L,10_0000_0000L);
        //forkJoinPool.execute(forkjoinTest); //excute方法
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkjoinTest);

        Long aLong = 0L;
        try {
            aLong = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("forkjoin结果："+ aLong + "，时间：" +(end-start));
    }

    //会并行流的程序员
    public static void test3() {
        Long start = System.currentTimeMillis();
        long sum = LongStream.range(0,10_0000_0000).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("stream并行流结果："+ sum + "，时间：" +(end-start));
    }
}


