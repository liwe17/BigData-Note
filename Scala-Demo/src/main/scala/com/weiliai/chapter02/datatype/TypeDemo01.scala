package com.weiliai.chapter02.datatype

object TypeDemo01 {

  def main(args:Array[String]):Unit={

    var num1: Int = 10
    //因为Int是一个类,因此他的一个实例,就是可以用很多方法
    //在scala中如果方法没有形参,则可以省略
    println(num1.toDouble+"\t"+num1.toString+100.toString)

    sayHi
    sayHi()

    var isPass = true
    println(isPass.toString)
  }

  def sayHi():Unit={
    println("say hi")
  }

}
