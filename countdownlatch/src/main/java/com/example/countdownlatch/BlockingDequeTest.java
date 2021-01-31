package com.example.countdownlatch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class BlockingDequeTest {
    //BlockingDeque

    public static void main(String[] args) {
        //test1();
        //test2();
//        test3();
        //test4();
        test5();
    }


    public static void test1(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // //java.lang.IllegalStateException: Queue full抛出异常
       // System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //java.util.NoSuchElementException 没有元素异常
        //System.out.println(blockingQueue.remove());
    }

    public static void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //返回false
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //返回null
        System.out.println(blockingQueue.poll());
    }


    public static void test3() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //返回队首元素
        System.out.println(blockingQueue.element());
    }

    public static void test4() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //返回队首元素
        System.out.println(blockingQueue.peek());

    }

    public static void test5() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        try {
           blockingQueue.put("a");
           blockingQueue.put("b");
           blockingQueue.put("c");


           new Thread(()->{
               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               blockingQueue.remove();
               System.out.println("移除了一个元素");
           }).start();

            System.out.println("准备放入d");
           blockingQueue.put("d");//队列没有位置，一直等，直到有元素出队
            System.out.println("放入d成功");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
