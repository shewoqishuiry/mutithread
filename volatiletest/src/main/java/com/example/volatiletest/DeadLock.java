package com.example.volatiletest;

import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) {

        String A = "a";
        String B = "b";

        new Thread(new MyThread(A,B)).start();
        new Thread(new MyThread(B,A)).start();
    }
}

class MyThread implements Runnable {

    private String A;
    private String B;

    public MyThread(String a, String b) {
        A = a;
        B = b;
    }

    @Override
    public void run() {
        synchronized (A) {
            System.out.println(Thread.currentThread().getName() + "拿到了锁" + A);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "拿到了锁" + B);
            }
        }
    }
}
