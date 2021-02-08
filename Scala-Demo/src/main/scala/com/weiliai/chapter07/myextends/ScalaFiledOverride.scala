package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: scala字段覆写
 */
object ScalaFiledOverride {

  def main(args: Array[String]): Unit = {
    val  obj1:AAAA = new BBBB
    val  obj2:BBBB = new BBBB

    //obj1.age => obj1.age()
    //obj2.age => obj2.age() //动态绑定
    println("obj1.age="+obj1.age+"\t obj2.age="+obj2.age)
  }

}

class AAAA{

  //var 会报错
  val age:Int = 10

}

class BBBB extends AAAA{

  override val age: Int = 20

}