package com.example.countdownlatch;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        //设置默认线程数量
        //用于线程限流，一次性只能进来3条线程
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 21; i++) {
            new Thread(()->{
                //得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位！");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }
            }).start();
        }
    }
}
