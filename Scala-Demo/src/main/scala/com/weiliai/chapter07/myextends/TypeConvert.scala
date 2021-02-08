package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/1
 * @Describe: 类型转换
 */
object TypeConvert {

  def main(args:Array[String]):Unit={
    //得到类名
    println(classOf[String])

    val s = "king"
    println(s.getClass.getName) //使用反射

    var p1= new Person200
    val emp = new Emp200
    //将子类引用给父类(向上转型,自动)
    p1 = emp
    //将父类引用重新转成子类引用(多态),向下转型
    val emp2 = p1.asInstanceOf[Emp200]
    emp2.sayHello()

  }
}

class Person200{
  var name = "tom"

  def printName(): Unit ={
    println("Person200 printName() "+name)
  }

  def sayHi(): Unit ={
    println("sayHi() ... ")
  }
}

class Emp200 extends Person200{

  //显示的使用override
  override def printName(): Unit = {
    //子类中需要调用父类的方法,使用super
    super.printName()
    sayHi()
  }

  def sayHello(): Unit ={
    println("sayHello() ... ")
  }

}
