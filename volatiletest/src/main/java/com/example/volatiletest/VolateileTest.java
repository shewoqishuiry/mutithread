package com.example.volatiletest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolateileTest {

    private static volatile int a = 0;
//    public static void main(String[] args) throws InterruptedException {
//
//       Thread thread1 =  new Thread(()->{
//            while (a == 0) {
//               // System.out.println(1);用这个会跳出死循环，原因是这个方法回去同步主内存中的数据
//            }
//        });
//       thread1.start();
//
//        TimeUnit.SECONDS.sleep(1);
//
//        a = 1; //修改了a的值后，程序立马停止了，volatile起作用了
//        System.out.println("a="+ a);
//    }

    private static AtomicInteger num = new AtomicInteger(0);

    private static void add(){
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { //两个线程：main线程和gc线程
            Thread.yield();
        }

        System.out.println(num);
    }
}
