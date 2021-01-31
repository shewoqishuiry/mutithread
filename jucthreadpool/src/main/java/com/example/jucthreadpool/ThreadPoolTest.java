package com.example.jucthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Executors工具类：3大方法
public class ThreadPoolTest {
    public static void main(String[] args) {
       // ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService threadPool =Executors.newFixedThreadPool(5);//创建一个固定大小的线程池
      // ExecutorService threadPool = Executors.newCachedThreadPool(); //创建一个可伸缩的，遇强则强，遇弱则弱的线程池


        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "跑起来！");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
