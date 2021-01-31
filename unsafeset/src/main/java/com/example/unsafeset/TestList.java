package com.example.unsafeset;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


//java.util.ConcurrentModificationException
//并发修改异常
public class TestList {
    public static void main(String[] args) {

        //并发下ArrayList不安全的
        /**
         * 解决方案：
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list1= Collections.synchronizedList(new ArrayList<>()); 工具类转化为线程安全的集合
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         */
       // List<String> list = new ArrayList<>();
       // List<String> list = new Vector<>();
       // List<String> list1= Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }).start();
        }
    }
}
