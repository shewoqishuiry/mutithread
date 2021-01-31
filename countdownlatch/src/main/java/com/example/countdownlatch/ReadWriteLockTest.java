package com.example.countdownlatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁：一次只能被一个线程占有
 * 共享锁：多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可共存
 * 读-写 不可共存
 * 写-写 不可共存
 */

public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCach myCach = new MyCach();

        // TestMyCach myCach = new TestMyCach();

        //写入
     for (int i = 0; i < 100; i++) {
            final int temp = i;
            new Thread(()->{
                myCach.put(temp+"",temp+"");
            }).start();
        }

        //读取
        for (int i = 0; i < 100; i++) {
            final int temp = i;
            new Thread(()->{
                myCach.read(temp+"");
            }).start();
        }
    }
}


class MyCach{
    //读写锁，更加细粒度的操作
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private volatile Map<String,Object> map= new HashMap<>(16);

    //存，写入的时候，只希望同时只有一个线程写
    public void put(String key,Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void read(String key) {
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

class TestMyCach{
    private volatile Map<String,Object> map= new HashMap<>(16);

    //存，写入的时候，只希望同时只有一个线程写
    public void put(String key,Object value) {
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完成！");
    }

    public void read(String key) {
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完成！");
    }
}
