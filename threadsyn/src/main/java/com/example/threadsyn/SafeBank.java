package com.example.threadsyn;

public class SafeBank {
    public static void main(String[] args) {
        //账户
        Account1 account = new Account1(300,"结婚基金");

        Drawing1 you = new Drawing1(account,50,"你");
        Drawing1 girlFriend = new Drawing1(account,100,"你女朋友");

        you.start();
        girlFriend.start();
    }
}


class Account1{
    int money;
    String name;

    public Account1(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class Drawing1 extends Thread {
    Account1 account;

    int drawingMoney;
    int nowMoney;

    public Drawing1(Account1 account, int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 余额 - 你取的钱
            account.money = account.money - drawingMoney;
            //你手里的钱
            nowMoney = nowMoney + drawingMoney;

            System.out.println(this.getName() + "手里的钱：" + nowMoney);
            System.out.println(account.name + "余额为：" + account.money);
        }
    }
}