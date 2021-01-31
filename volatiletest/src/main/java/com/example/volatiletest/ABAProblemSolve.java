package com.example.volatiletest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABAProblemSolve {

    //正常操作，这里一般比较的都是一个个的对象,第二个参数相当于版本号，标记一下
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    //CAS  compareAndSet: 比较并交换
    public static void main(String[] args) {
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "1：" +stamp);

            Lock lock = new ReentrantLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "2:" + atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "3:" + atomicStampedReference.getStamp());
        },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "11：" +stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1));
            System.out.println(Thread.currentThread().getName() + "22:" + atomicStampedReference.getStamp());
        },"b").start();
    }
}
