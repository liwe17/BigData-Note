package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/1
 * @Describe: 类型转换最佳实践
 */
object TypeConvertCase {

  def main(args:Array[String]):Unit={

    val student = new Student400
    val emp = new Emp400

    test(student)
  }

  //写一个参数多态代码
  //因为在oop中一个父类的引用可以接收所有子类的引用,多态
  def test(p:Person400): Unit ={
//    p match {
//      case emp40: Emp400 => emp40.showInfo()
//      case student400: Student400=>student400.cry()
//      case _ => println("转换失败")
//    }
    //使用scala中类型检查和转换
    if(p.isInstanceOf[Emp400]){ //判断
      p.asInstanceOf[Emp400].showInfo()
    }else if(p.isInstanceOf[Student400]) {
      p.asInstanceOf[Student400].cry()
    }else{
      println("转换失败")
    }
  }

}

//base类
class Person400 {

  def printName(): Unit ={
    println("Person400 printName() ")
  }

  def sayOk(): Unit ={
    println("Person400 sayOk() ")
  }

}

class Student400 extends Person400{

  val stuId=400

  override def printName(): Unit = {
    println("Student400 printName() ")
  }

  def cry(): Unit ={
    println("学生的id="+stuId)
  }

}

class Emp400 extends Person400{

  val empId=200

  override def printName(): Unit = {
    println("Emp400 printName() ")
  }

  def showInfo(): Unit ={
    println("雇员的id="+empId)
  }

}
