package com.weiliai.chapter07.myextends;

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: 动态绑定
 */
public class JavaDynamicBind {

    public static void main(String[] args){

        //将一个子类的对象的地址,交给了一个AA(父类的)引用
        //Java的动态绑定机制的小结
        //1.如果调用的是方法,则jvm会将该方法和对象的内存地址绑定
        //2.如果调用的是一个属性,则没有动态绑定机制,在哪里调用,就返回对应值
        AA obj = new BB();
        System.out.println(obj.sum()); //40
        System.out.println(obj.i);
        System.out.println(obj.getI());
        BB obj1 = new BB();
        System.err.println(obj1.sum1());

    }
}

class AA{

    public int i=10;

    public int sum(){
        System.out.println("AA SUM");
//        return getI()+10;
        return i+10;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

class BB extends AA{

    public int i = 20;

    public int sum(){
        System.out.println("BB SUM");
        return i+20;
    }


    @Override
    public int getI() {
        return i;
    }

    @Override
    public void setI(int i) {
        this.i = i;
    }

    public int sum1(){
        return i+10;
//        return super.i+10;
    }
}