package com.weiliai.chapter07.myextends;

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: Java匿名内部类
 */
public class NoNameDemo01 {

    public static void main(String[] args) {
        A2 a2 = new A2(){
            @Override
            public void cry() {
                System.out.println("cry ...");
            }
        };

        a2.cry();
    }
}

abstract class A2{
    public abstract void cry();
}
