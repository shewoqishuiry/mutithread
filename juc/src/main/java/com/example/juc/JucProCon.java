package com.example.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JucProCon {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.increace();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.decreace();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.increace();
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.decreace();
            }
        },"D").start();
    }
}


class Data2 {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increace(){
        lock.lock();
        try {
            while (number != 0) {
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "+ 1" + "number =" + number);
            //通知其他线程,我+1完成了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decreace() {
        lock.lock();
        try {
            while (number == 0) {
                //等待
                condition.await();
            }
            number--;
            //通知其他线程,我-1完成了
            System.out.println(Thread.currentThread().getName() + "- 1" + "number =" + number);
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}