package com.weiliai.chapter05.mylazy

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: 懒加载
 */
object LazyDemo01 {

  def main(args: Array[String]): Unit = {
    lazy val res=sum(10,20)
    println("----------")
    println("res="+res)//在使用res前,才执行
  }

  //sum函数,返回和
  def sum(n1:Int,n2:Int)={
    println("sum() 执行了..")
    n1+n2
  }

}
