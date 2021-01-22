package com.example.threadstop;

public class TestThreadJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("我要插队，我要先跑完，我就是这么吊"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadJoin testThreadJoin = new TestThreadJoin();
        Thread thread = new Thread(testThreadJoin);
        thread.start();

        for (int i = 0; i < 200; i++) {
            if (i == 50) {
                thread.join();//插队执行子线程，子线程一旦执行了这个方法，要等这个线程执行完了，再执行主线程
            }
            System.out.println("主线程跑起来，第" + i + "步");
        }
    }
}
