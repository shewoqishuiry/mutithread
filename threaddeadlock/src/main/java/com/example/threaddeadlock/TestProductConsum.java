package com.example.threaddeadlock;

//生产者和消费者模型
public class TestProductConsum {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        Productor productor = new Productor(synContainer);
        Consumer consumer = new Consumer(synContainer);
        productor.start();
        consumer.start();

        System.out.println("主线程走完了！");
    }

}

class Chicken{
    public int id;//编号

    public Chicken(int id) {
        this.id = id;
    }
}

class Productor extends Thread{
    SynContainer synContainer;
    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }
    @Override
    public void run(){
        try {
            int i = 0;
            while (i < 1000) {
                if(i == 999) {
                    System.out.println("走");
                }
                synContainer.push(new Chicken(i++));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            synContainer.push(new Chicken(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产完成！");
    }
}


class Consumer extends Thread {
    SynContainer synContainer;
    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }
    @Override
    public void run(){
        try {
            int i = 0;
            while (i < 1000) {
                if (i==999) {
                    System.out.println(i);
                }
                synContainer.pop();
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("吃完了");
    }
}



class SynContainer{
    int count = 0;

    Chicken[] chickens = new Chicken[10];

    public synchronized void push(Chicken chicken) throws InterruptedException {
        if (count == chickens.length) {
            this.wait();
        }

        chickens[count++]=chicken;
        System.out.println("生产了第"+chicken.id+"只鸡！");
        this.notifyAll();
    }

    public synchronized Chicken pop() throws InterruptedException {
        if (count == 0) {
            this.wait();
        }

        Chicken chicken = chickens[--count];
        System.out.println("吃掉了" + chicken.id + "只鸡！");
        this.notifyAll();
        return chicken;
    }
}
