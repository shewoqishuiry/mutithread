package com.example.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        //总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{System.out.println(Thread.currentThread().getName() + "go Out !");}).start();
            countDownLatch.countDown();
        }

        try {
            countDownLatch.await();//等待计数器归零，然后在向下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Close Door");

    }
}
