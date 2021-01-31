package com.example.functionalinterface;

import java.util.Random;
import java.util.function.Supplier;

//Supplier 供给型接口：没有参数，只有返回值

public class SupplierTest {
    public static void main(String[] args) {
//        Supplier supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return new Random().nextInt();
//            }
//        };

        Supplier<Integer> supplier = ()->{
            return new Random().nextInt();
        };

        System.out.println(supplier.get());
    }
}
