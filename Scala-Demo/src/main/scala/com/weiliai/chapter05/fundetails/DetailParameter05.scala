package com.weiliai.chapter05.fundetails

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: 多形参默认值传递
 */
object DetailParameter05 {

  def main(args: Array[String]): Unit = {
      //mysqlCon()
      //mysqlCon("127.0.0.1",7777) //从左到右
    mysqlCon(user="tom") //指定覆盖某个值
    // f6("v2") //错误
    f6(p2="v2")

  }


  def mysqlCon(add:String="localhost",port:Int=3306,user:String="root",pwd:String="root"):Unit={
    println("add="+add)
    println("port="+port)
    println("user="+user)
    println("pwd="+pwd)
  }

  def f6(p1:String="v1",p2:String): Unit ={
    println(p1+p2)
  }

  //递归不能使用类型推断,必须指定返回值类型
  def f8(n:Int):Int={
    if(n<=0)
      1
    else
      n*f8(n-1)
  }

}
