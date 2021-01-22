package com.example.mutithread2;

public class TestThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("我在看代码！");
        }
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        testThread1.start();

        for (int i = 0; i < 500; i++) {
            System.out.println("我在看并发编程的书");
        }
    }
}
