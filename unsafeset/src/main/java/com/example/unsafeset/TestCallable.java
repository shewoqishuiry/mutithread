package com.example.unsafeset;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) {
        //怎么启动callable
        //适配器模式，用FutureTask来适配
        new Thread().start();
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        try {
            Integer i = (Integer) futureTask.get(); //get方法会产生阻塞，把他放到最后，或者采用异步方式去获取
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("你好呀！");
        return 1024;
    }
}
