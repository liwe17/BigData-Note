package com.weiliai.chapter07.myextends;

/**
 * @Author: Doug Li
 * @Date 2021/2/6
 * @Describe: Java构造器回顾
 */
public class JavaBaseConstructor {

    public static void main(String[] args) {
//        new A();
        new A("test");
//        new B();
//        new B("test");
    }
}

class A {
    public A() {
        System.out.println("A()");
    }

    public A(String name) {
        System.out.println("A(String name) " + name);
    }
}

class B extends A{

    public B(){
        //这里会隐式调用super();就是无参的父类构造器A()
        //super();
        System.out.println("B()");
    }

    public B(String name){
        //super(); 隐式调用
        super(name);
        System.out.println("B(String name) "+name);
    }
}
