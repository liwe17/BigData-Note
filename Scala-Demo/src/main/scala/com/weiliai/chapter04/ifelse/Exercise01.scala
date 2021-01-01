package com.weiliai.chapter04.ifelse

/**
 * @Author: Doug Li
 * @Date 2021/1/1
 * @Describe: 案例
 */
object Exercise01 {

  def main(args:Array[String]):Unit={

    //既能被3整除又能被5整除
    val num1=10
    val num2=5
    val sum=num1+num2
    if(sum%3 ==0&&sum%5==0){
      println("能被3整除又能被5整除")
    }else{
      println("能被3又能被5整除不成立")
    }

    //判断闰年
    val year = 2018
    if((year%4==0 && year%100!=0)||(year%400==0)){
      println(s"${year}是闰年")
    }else{
      println(s"${year}不是闰年")
    }
  }

}
