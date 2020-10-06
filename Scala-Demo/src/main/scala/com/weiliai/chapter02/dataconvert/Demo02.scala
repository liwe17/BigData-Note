package com.weiliai.chapter02.dataconvert

object Demo02 {
  def main(args: Array[String]): Unit = {

    val num1: Int = 10 * 3.5.toInt + 6 * 1.5.toInt  // 36
    val num2: Int = (10 * 3.5 + 6 * 1.5).toInt // 44
    println(num1 + " " + num2)
  }
}
