package com.weiliai.chapter05.myexception

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: Scala异常举例2
 */
object ScalaExceptionDemo02 {

  def main(args: Array[String]): Unit = {

    f11

  }

  @throws(classOf[NumberFormatException])
  def f11()={
    "abc".toInt
  }
  
}
