package com.example.jucthreadpool;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.*;

//max如果小于等于corePoolSize，除核心线程外，其他线程不会被使用
//max如果大于corePoolSize且小于等于maximumPoolSize + sizeof(linkedblockingdeque),,则启动核心线程以外的线程
//max如果大于maximumPoolSize + sizeof(linkedblockingdeque)，拒绝策略生效
//拒绝策略1、 new ThreadPoolExecutor.AbortPolicy() // 队列满了，还有数据往队列存，不处理这个数据，抛出异常
//拒绝策略2、new ThreadPoolExecutor.CallerRunsPolicy() //返回调用线程池的线程继续执行
//拒绝策略3、new ThreadPoolExecutor.DiscardPolicy()  //尝试去和最早的竞争，竞争失败不抛异常
public class RealThreadPoolTest {
    public static void main(String[] args) {

        //如何定义最大线程数
        //CPU密集型： 几核就是几，可以保证效率最高
        //IO秘籍型：比如：15个大型任务 io十分占用资源！这时候要大于15，保证还有线程执行其他任务

        //获取CPU核数Runtime.getRuntime().availableProcessors()
        int cpuCount = Runtime.getRuntime().availableProcessors();
        System.out.println("CUP核数：" + cpuCount);

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()  //拒绝策略
        );
        try {
            int max = 10;
            for (int i = 0; i < max; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "跑起来！");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
