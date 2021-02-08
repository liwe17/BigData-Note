package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/1
 * @Describe: scala 继承快速入门
 */
object Extends01 {

  def main(args: Array[String]): Unit = {
    //使用
    val student = new Student
    student.name="jack" //name 继承自person
    student.showInfo()
    student.studying()
  }
}

class Person { //person类

  var name: String = _
  var age: Int = _
  def showInfo(): Unit ={
    println("学生信息如下:")
    println("姓名:"+this.name)
  }
}

//Student继承person
class Student extends Person{

  def studying(): Unit ={
    //这里使用父类属性
    println(this.name+"学习 scala 中...")
  }
}
