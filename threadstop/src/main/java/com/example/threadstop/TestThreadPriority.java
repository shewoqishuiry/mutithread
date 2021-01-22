package com.example.threadstop;

public class TestThreadPriority {

    public static void main(String[] args) {
        System.out.println("主线程跑起来了");

        testThread th = new testThread();

        Thread thread1 = new Thread(th,"线程1");
        Thread thread2 = new Thread(th,"线程2");
        Thread thread3 = new Thread(th,"线程3");
        Thread thread4 = new Thread(th,"线程4");
        Thread thread5 = new Thread(th,"线程5");
        Thread thread6 = new Thread(th,"线程6");

        //thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.start();

        thread2.setPriority(2);
        thread2.start();

        thread3.setPriority(9);
        thread3.start();

        thread4.setPriority(6);
        thread4.start();

        thread5.setPriority(8);
        thread5.start();

        thread6.setPriority(Thread.MAX_PRIORITY);
        thread6.start();


    }
}


class testThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---->" + Thread.currentThread().getPriority());
    }
}
