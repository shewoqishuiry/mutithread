package com.example.threadsyn;

public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100,"结婚基金");

        Drawing you = new Drawing(account,50,"你");
        Drawing girlFriend = new Drawing(account,100,"你女朋友");

        you.start();
        girlFriend.start();
    }
}


class Account{
    int money;
    String name;

    public Account(int money, String name) {
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


class Drawing extends Thread {
    Account account;

    int drawingMoney;
    int nowMoney;

    public Drawing(Account account, int drawingMoney,String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run(){
        if (account.money-drawingMoney < 0) {
            System.out.println(Thread.currentThread().getName()+ "钱不够，取不了");
            return;
        }

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //卡内余额 = 余额 - 你取的钱
        account.money = account.money - drawingMoney;
        //你手里的钱
        nowMoney = nowMoney + drawingMoney;

        System.out.println(account.name +"余额为："+account.money);

        System.out.println(this.getName() + "手里的钱："+ nowMoney);
    }
}
