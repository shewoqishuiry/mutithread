package com.example.functionalinterface;

import java.util.function.Function;


/**
 * Function 函数式接口，有一个输入参数，有一个输出
 * 只要是函数式接口，就可以用呢lambda表达式简化
 */
public class FunctionTest {
    public static void main(String[] args) {
//        Function function = new Function<String,String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };

        Function function = (str)->{return "哈哈哈"+str;};

        System.out.println(((Function<String, String>) function).apply("12655"));
    }
}
