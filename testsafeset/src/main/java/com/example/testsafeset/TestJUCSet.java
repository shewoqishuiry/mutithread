package com.example.testsafeset;

import java.util.concurrent.CopyOnWriteArrayList;


//线程安全的集合
public class TestJUCSet {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(()-> list.add(Thread.currentThread().getName())).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("集合大小"+list.size());
    }
}
