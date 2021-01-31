package com.example.volatiletest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


class TestSelfLock{
    public static void main(String[] args) {
        SelfLock selfLock = new SelfLock();
        new Thread(()-> {
            selfLock.lock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                selfLock.unlock();
            }

        }).start();


        new Thread(()-> {
            selfLock.lock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                selfLock.unlock();
            }

        }).start();
    }
}

//自旋锁
class SelfLock {
      AtomicReference<Thread> atomicReference = new AtomicReference<>();
      public void lock() {
          Thread thread = Thread.currentThread();
          System.out.println(Thread.currentThread().getName() + "==> myLock");
          //自旋锁
          while (!atomicReference.compareAndSet(null,thread)) {

          }
      }

      public void unlock() {
          Thread thread = Thread.currentThread();
          System.out.println(Thread.currentThread().getName() + "==> myUnlock");
          atomicReference.compareAndSet(thread,null);
      }
}
