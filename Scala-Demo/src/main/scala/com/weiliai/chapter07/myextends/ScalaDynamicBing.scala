package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: 动态绑定
 */
object ScalaDynamicBing {

  def main(args:Array[String]):Unit={

    val  obj1:AAA = new BBB
    val  obj2:BBB = new BBB

    //obj1.age => obj1.age()
    //obj2.age => obj2.age() //动态绑定
    println("obj1.age="+obj1.age+"\t obj2.age="+obj2.age)

  }

}

class AAA{

  val age:Int = 10//会生成public age()

}

class BBB extends AAA{

  override val age: Int = 20 //会生成public age()

}
