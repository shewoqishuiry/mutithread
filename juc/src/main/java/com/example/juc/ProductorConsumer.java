package com.example.juc;

public class ProductorConsumer {
    public static void main(String[] args) {
        Data data  = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{  for (int i = 0; i < 10; i++) {
            try {
                data.decreace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"B").start();

        new Thread(()->{  for (int i = 0; i < 10; i++) {
            try {
                data.increace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"C").start();

        new Thread(()->{  for (int i = 0; i < 10; i++) {
            try {
                data.decreace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"D").start();
    }
}

//生产者消费者模型口诀：等待、业务、通知
class Data {
    private int number = 0;

    public synchronized void increace() throws InterruptedException {
        while (number !=0 ) {
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+ "+ 1" + "number =" + number);
        //通知其他线程,我+1完成了
        this.notifyAll();
    }

    public synchronized void decreace() throws InterruptedException {
        while (number == 0) {
            //等待
            this.wait();
        }
        number--;
        //通知其他线程,我-1完成了
        System.out.println(Thread.currentThread().getName()+ "- 1" + "number =" + number);
        this.notifyAll();
    }
}
