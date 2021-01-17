package com.weiliai.chapter06.oop

/**
 * @Author: Doug Li
 * @Date 2021/1/17
 * @Describe: 类型自动推断
 */
object CreateObj {

  def main(args:Array[String]):Unit={
    val emp = new Emp //emp类型就是Emp
    val emp2:Person = new Emp //如果将子类对象,交给父类引用,这就需要写上类型
  }

}

class Person{

}

class Emp extends Person{

}
