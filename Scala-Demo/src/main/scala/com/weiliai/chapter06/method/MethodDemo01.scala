package com.weiliai.chapter06.method

/**
 * @Author: Doug Li
 * @Date 2021/1/17
 * @Describe: 方法案例
 */
object MethodDemo01 {

  def main(args: Array[String]): Unit = {
      val dog = new Dog
      println(dog.cal(10,20))
  }
}

class Dog{
  private var sal:Double = _
  var food:String = _
  //方法
  def cal(n1:Int,n2:Int)={
    n1+n2
  }
}
