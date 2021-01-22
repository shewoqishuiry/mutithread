package com.example.threadstop;


//不推荐使用JDK提供的stop()、destroy()方法
//推荐线程自己停下来
//建议使用一个标志位进行终止变量，当flag=false，则终止线程运行
public class TestThreadStop implements Runnable{
    private boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            System.out.println("run........thread");
        }
    }

    public void stopThread(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestThreadStop testThreadStop = new TestThreadStop();
        new Thread(testThreadStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("跑起来了，现在是" + i);
             if (i == 900) {
                 testThreadStop.stopThread();
                 System.out.println("线程停止了");
                 break;
             }
        }
    }


}
