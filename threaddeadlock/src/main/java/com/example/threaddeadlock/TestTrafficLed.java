package com.example.threaddeadlock;


//信号灯法
public class TestTrafficLed {
    public static void main(String[] args) {
        TV tv = new TV();
        Player player = new Player(tv);
        Watcher watcher = new Watcher(tv);

        player.start();
        watcher.start();
    }
}

//生产者--》演员
class Player extends Thread {
    TV tv;
    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2 == 0) {
                this.tv.play("唱歌");
            } else {
                this.tv.play("跳舞");
            }
        }
    }
}

//消费者--》观众
class Watcher extends Thread {
    TV tv;
    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.watch();
        }
    }
}

//产品--》观众
class TV {
    //演员表演，观众等待
    //观众观看，演员等待
    String voice = "唱歌"; //表演的节目
    boolean flag = true;

    //表演
    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("演员表演了" + voice);
        //通知观众观看
        this.notifyAll();
        this.voice = voice;
        this.flag = !flag;
    }


    //观看
    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了：" + voice);

        //通知演员表演
        this.notifyAll();
        this.flag = !flag;
    }
}
