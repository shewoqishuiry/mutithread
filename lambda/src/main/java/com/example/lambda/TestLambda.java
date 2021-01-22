package com.example.lambda;


//lambda表达式的推导过程
public class TestLambda {
    //2、静态内部类
    static class MyLambda1 implements ILambda{
        @Override
        public void lambda() {
            System.out.println("新建一个lambda1");
        }
    }

    public static void main(String[] args) {
        //1、外部类
        ILambda myLambda = new MyLambda();
        myLambda.lambda();
        //2、静态内部类
        myLambda = new MyLambda1();
        myLambda.lambda();
        //3、局部内部类
        class Mylambda2 implements ILambda{

            @Override
            public void lambda() {
                System.out.println("新建一个lambda2");
            }
        }
        myLambda = new Mylambda2();
        myLambda.lambda();
        //4、匿名内部类
        myLambda = new ILambda() {
            @Override
            public void lambda() {
                System.out.println("新建一个lambda3");
            }
        };
        myLambda.lambda();
        //5、lambda表达式
        myLambda = ()-> {System.out.println("新建一个lambda4");};
        myLambda.lambda();

    }

}

//1、外部类
class MyLambda implements ILambda{

    @Override
    public void lambda() {
        System.out.println("新建一个 lambda");
    }
}

//定义一个函数式接口，只能包涵一个方法
interface ILambda {
    void lambda();
}
