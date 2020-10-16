package com.weiliai.chapter03.inputcon

import scala.io.StdIn

object Demo01 {

  def main(args:Array[String]):Unit={

    println("请输入姓名")
    val name = StdIn.readLine()
    println("请输入年龄")
    val age  = StdIn.readInt()
    println("请输入薪水")
    val sal =StdIn.readDouble()
    printf("用户的信息为 name = %s age = %d sal = %.2f",name,age,sal)


//    Cat.sayHi
//    Cat.sayHello

  }
}

//声明了一个对象(伴生对象)
object Cat extends AAA{
  def sayHi:Unit={
    println("小狗汪汪汪....")
  }
}

//AAA是特质,等价于Java中的interface+abstract class
trait AAA{
  def sayHello():Unit={
    println("AAA sayHello")
  }
}
