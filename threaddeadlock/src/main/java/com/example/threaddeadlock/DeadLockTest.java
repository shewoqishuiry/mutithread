package com.example.threaddeadlock;


//死锁：多个线程互相抱着对方加了锁的资源，然后形成僵持
public class DeadLockTest {
    public static void main(String[] args) {
        MakeUp g1 = new MakeUp(0,"灰姑凉");
        MakeUp g2 = new MakeUp(1,"白雪公主");

        g1.start();
        g2.start();
    }
}

class Mirror{
}


class Lipstick {
}

class MakeUp extends Thread {
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String girlName;//打扮者

    MakeUp(int choice,String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run(){
        //化妆
        try {
            makeup1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //死锁
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + "获得了口红！");
                Thread.sleep(1000);
                synchronized (mirror) {
                    System.out.println(this.girlName + "获得了镜子！");
                }
            }
        } else {
            synchronized (mirror){
                System.out.println(this.girlName + "获得了镜子！");
                Thread.sleep(2000);
                synchronized (lipstick) {
                    System.out.println(this.girlName + "获得了口红");
                }
            }
        }
    }


    //解决死锁
    private void makeup1() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + "获得了口红！");
                Thread.sleep(1000);
            }
            synchronized (mirror) {
                System.out.println(this.girlName + "获得了镜子！");
            }
        } else {
            synchronized (mirror){
                System.out.println(this.girlName + "获得了镜子！");
                Thread.sleep(2000);
            }
            synchronized (lipstick) {
                System.out.println(this.girlName + "获得了口红");
            }
        }

    }
}
