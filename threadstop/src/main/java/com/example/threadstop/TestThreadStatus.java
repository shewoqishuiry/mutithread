package com.example.threadstop;

public class TestThreadStatus {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("子线程结束");
        });

        //NEW状态
        Thread.State state = thread.getState();
        System.out.println(state);
        //RUNNABLE状态
        thread.start();
        state = thread.getState();
        System.out.println(state);

        //一直获取状态
        while (state != Thread.State.TERMINATED) {
            Thread.sleep(10);
            state = thread.getState();
            System.out.println(state);
        }

    }
}
