package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: 抽象类入门
 */
object AbstractDemo01 {

  def main(args: Array[String]): Unit = {
    println("xxx")

    val animal = new Animal03 {
      override def sayHello(): Unit = {
        println("say hello~~")
      }

      override var food: String = _
    }

    animal.sayHello()
  }

}

//抽象类
abstract class Animal{

  var name:String //抽象的字段
  var age:Int //抽象字段
  var color:String = "black" //普通属性
  def cry() //抽象方法,无需标记abstract

}

abstract class Animal02{

  //在抽象类中可以有实现的方法
  def sayHi():Unit={
    println("xxx")
  }

}


abstract class Animal03{

  def sayHello()

  var food:String

}

class Dog extends Animal03{
  override def sayHello(): Unit = {
    println("小狗汪汪叫!")
  }

  var food: String = ""
}
