package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: 重写不带参数的def
 */
object ScalaFieldOverrideDetail02 {

  def main(args:Array[String]):Unit={
    println("xxx")
    val b1 = new BBBBB
    println(b1.sal())

    val b2:AAAAA = new BBBBB
    println("b2.sal="+b2.sal())
  }
}


class AAAAA{
  def sal():Int={
    10
  }
}

class BBBBB extends AAAAA{
  override def sal(): Int = 0 //底层public sal
}