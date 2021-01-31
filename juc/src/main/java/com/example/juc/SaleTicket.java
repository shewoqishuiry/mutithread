package com.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) ticket.saleTicket();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) ticket.saleTicket();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) ticket.saleTicket();
        }).start();
    }
}

class Ticket {
    private int ticketNum = 50;

    Lock lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();
        try {
            if (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (ticketNum--) + "张票！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
