package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/1
 * @Describe: 重写方法
 */
object MethodOverride01 {

  def main(args:Array[String]): Unit ={
    val emp =new Emp
    emp.printName()
  }
}

class Person100{

  var name = "tom"

  def printName(): Unit ={ //输出名字
    println("Person printName() "+ name)
  }

  def sayHi():Unit={
    println("sayHi()..")
  }

}

class Emp extends Person100{

  //显示的使用override
  override def printName(): Unit = {
    //子类中需要调用父类的方法,使用super
    super.printName()
    sayHi()

  }

}