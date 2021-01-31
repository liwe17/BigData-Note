package com.weiliai.chapter07.visit

/**
 * @Author: Doug Li
 * @Date 2021/1/27
 * @Describe: 访问限制
 */
object TestVisit {

  def main(args: Array[String]): Unit = {
    val c = new Clerk
    c.showInfo()
    Clerk.test(c)
  }
}

//类
class Clerk {
  var name: String = "jack"
  private var sal: Double = 9999.9 //sal_$eq()方法私有,sal()方法公有
  protected var age = 10
  var job="大数据工程师"

  def showInfo(): Unit = {
    //在本类可以使用私有的
    println("name=" + name + " sal=" + sal)
  }
}

//当一个文件中出现了class Clerk和object Clerk
//1.class Clerk 称为伴生类
//2.object Clerk 伴生对象
//3.因为scala设计者将static拿掉,设计了伴生类和伴生对象的概念
//4.伴生类写非静态内容,伴生对象就是静态内容
object Clerk {
  def test(c: Clerk) = {
    //这里体现出在伴生对象中,可以访问c.sal
    println("test() name=" + c.name + " sal=" + c.sal)
  }
}

class Person{
  //这里我们增加一个包访问权限
  //下面private[visit]: 1. 仍然是private 2. 在visit包(包括子包)下也可以使用name,相当于扩大访问范围
  protected [visit] val name = "jack"
}
