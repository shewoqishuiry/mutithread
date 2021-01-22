package com.example.mutithread2;

public class TestThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("我在看代码！");
        }
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        new Thread(testThread).start();

        for (int i = 0; i < 500; i++) {
            System.out.println("我在看并发编程的书籍！");
        }
    }
}
