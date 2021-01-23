package com.example.threadsyn;


public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"傻逼的我").start();
        new Thread(buyTicket,"牛逼的你").start();
        new Thread(buyTicket,"老黄牛党").start();
    }

}


class BuyTicket implements Runnable {

    private int ticketNum = 10;

    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    private void buy() {
        if (ticketNum <= 0 ) {
            flag = false;
            return;
        }
        //模拟延时
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //买票
        System.out.println(Thread.currentThread().getName() + "拿到"+ ticketNum--);
    }

}