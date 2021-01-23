package com.example.threaddeadlock;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock1 testLock1 = new TestLock1();
        new Thread(testLock1,"小明").start();
        new Thread(testLock1,"小白").start();
        new Thread(testLock1,"小红书").start();
    }

}

class TestLock1 implements Runnable{

    int tickectNum = 1000;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (tickectNum > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "得到了" + tickectNum--);
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
