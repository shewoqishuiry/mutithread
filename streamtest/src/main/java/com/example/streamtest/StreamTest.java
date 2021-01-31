package com.example.streamtest;


import java.util.Arrays;
import java.util.List;

/**
 * 筛选
 * 1、ID必须是偶数
 * 2、年龄必须大于23
 * 3、用户名转为大写字母
 * 4、用户名字母倒序排列
 * 5、只输出一个用户
 */
public class StreamTest {
    public static void main(String[] args) {
        User user1 = new User(1,"a",21);
        User user2 = new User(2,"b",23);
        User user3 = new User(3,"c",25);
        User user4 = new User(4,"d",27);
        User user5 = new User(5,"e",26);

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        users.stream().filter((user) -> {return user.getId()%2==0;})
                .filter(user -> {return user.getAge() > 23;})
                .map(user ->{return user.getName().toUpperCase();})
                .sorted((u1,u2) -> {return  u2.compareTo(u1);})
               //.limit(1)
                .forEach(System.out::println);

    }
}


class User {
    int id;
    String name;
    int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
