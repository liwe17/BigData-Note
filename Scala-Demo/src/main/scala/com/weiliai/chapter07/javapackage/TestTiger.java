package com.weiliai.chapter07.javapackage;

/**
 * @Author: Doug Li
 * @Date 2021/1/26
 * @Describe: 测试Java包
 */
public class TestTiger {

    public static void main(String[] args){
        //使用xh
        final com.weiliai.chapter07.javapackage.xh.Tiger tiger = new com.weiliai.chapter07.javapackage.xh.Tiger();
        //使用xm
        final com.weiliai.chapter07.javapackage.xm.Tiger tiger1 = new com.weiliai.chapter07.javapackage.xm.Tiger();

        System.err.println(tiger+":"+tiger1);
    }

}
