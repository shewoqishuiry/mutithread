package com.example.mutithread2;

import org.springframework.util.StringUtils;

public class Race implements Runnable {

    private static String winner;

    @Override
    public void run() {
        for (int i = 0;i <= 500 ;i++) {
            if (Thread.currentThread().getName().equals("兔子") && i%200 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (gameOver(i)) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + "===》跑了" + i + "步");
        }
    }

    private boolean gameOver(int steps) {
        if (StringUtils.hasLength(winner)) {
            return true;
        }
        if (steps == 500) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}
