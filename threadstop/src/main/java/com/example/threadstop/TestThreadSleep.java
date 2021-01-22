package com.example.threadstop;

//sleep()指当前线程阻塞的毫秒数
//sleep存在异常InterruptedException
//sleep时间达到后线程进入就绪状态
//sleep可以模拟网络延时,倒计时等
//每一个对象都有一个锁,sleep不会释放锁
public class TestThreadSleep{

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("第"+ i + "秒");
            Thread.sleep(1000);
        }
    }

}
