package com.weiliai.chapter05.myexception;

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: Java异常回顾
 */
public class JavaExceptionDemo01 {

    public static void main(String[] args) {
        try{
            //可疑的代码
            int i = 0;
            int b = 10;
            int c = b/i;//执行代码,抛出算术异常
        }catch (ArithmeticException ex){
            ex.printStackTrace();
        }catch (Exception ex){ //java中不可以把返回值大的异常写在前,否则报错!!
            ex.printStackTrace();
        }finally {
            //最终要执行的代码
            System.out.println("java finally");
        }
    }

}
