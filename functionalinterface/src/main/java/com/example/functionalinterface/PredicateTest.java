package com.example.functionalinterface;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是布尔型
 */

public class PredicateTest {
    public static void main(String[] args) {
        //判断字符串是否为空
//        Predicate predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };

        Predicate<String> predicate = (String str) ->{return str.isEmpty();};
        System.out.println(predicate.test("1"));
    }
}
