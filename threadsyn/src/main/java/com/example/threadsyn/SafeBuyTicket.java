package com.example.threadsyn;

public class SafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket1 buyTicket = new BuyTicket1();

        new Thread(buyTicket,"牛逼的你1").start();
        new Thread(buyTicket,"傻逼的我2").start();
        new Thread(buyTicket,"老黄牛党3").start();

        new Thread(buyTicket,"老黄牛党4").start();
        new Thread(buyTicket,"牛逼的你5").start();
        new Thread(buyTicket,"傻逼的我6").start();
    }

}


class BuyTicket1 implements Runnable {

    private int ticketNum = 10000;

    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() throws InterruptedException{
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
//        //模拟延时
//        Thread.sleep(100);

        //买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNum-- + "hash值" + Thread.currentThread().hashCode());
    }
}

