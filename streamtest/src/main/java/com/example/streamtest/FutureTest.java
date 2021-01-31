package com.example.streamtest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *  异步调用： CompletableFuture
 *  异步执行
 *  成功回调
 *  失败回调
 *
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        //没有返回值的异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(Thread.currentThread().getName() + "runAsysnc=>void");
//        });
//
//        System.out.println("11111");
//        completableFuture.get(); //阻塞等待子线程执行完成，获取结果

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
            return 1024;
        });

        Integer integer = supplyAsync.whenComplete((t, u) -> {
            System.out.println("t=>" + t);//执行成功返回结果
            System.out.println("u=>" + u); //执行失败的错误信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;//执行失败返回错误信息
        }).get();
        System.out.println(integer);
    }
}
