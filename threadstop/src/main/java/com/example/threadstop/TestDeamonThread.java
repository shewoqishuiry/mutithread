package com.example.threadstop;

//虚拟机不会等守护线程结束，主线程结束了就行，程序员不需要去结束守护线程
public class TestDeamonThread {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

       Thread godThread = new Thread(god,"上帝是守护线程！");
       godThread.setDaemon(true); //设置为守护线程，如果不设置，即使主线程结束了，那这个线程仍将一直跑
       godThread.start();

        new Thread(you,"我的人生不过3万多天！").start();
    }
}


class God implements Runnable{
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝一直守护着你！");
        }
    }
}

class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("活了"+ i + "天了");
        }
        System.out.println("I dead ! Goodbye World !");
    }
}
