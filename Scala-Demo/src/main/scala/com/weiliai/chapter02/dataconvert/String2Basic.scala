package com.weiliai.chapter02.dataconvert

object String2Basic {

  def main(args: Array[String]): Unit = {

    val d1 = 1.2
    //基本类型转String
    val s1 = d1 + ""
    //String转基本数据类型
    val s2 = "12"
    val num1 = s2.toInt
    val num2 = s2.toByte
    val num3 = s2.toDouble
    val num4 = s2.toLong

    println("ok~")

    val s3="hello"
    println(s3.toInt) //error

    // 在scala不是将小数点后的进行截取,而是会抛出异常
    val s4="12.5"
    println(s4.toDouble) //正确
    println(s4.toInt) //error
  }

}
