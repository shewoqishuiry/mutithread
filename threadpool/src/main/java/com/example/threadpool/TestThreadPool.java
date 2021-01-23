package com.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {

    public static void main(String[] args) {

        //1、创建服务，创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //2、执行
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());

        //3、关闭连接
        executorService.shutdown();

    }
}


class MyThread  implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
