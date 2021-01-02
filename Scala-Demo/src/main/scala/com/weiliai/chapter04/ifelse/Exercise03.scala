package com.weiliai.chapter04.ifelse

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 三元运算符
 */
object Exercise03 {

  def main(args: Array[String]): Unit = {
    val sumVal = 9
    val result = if (sumVal > 20) {
      "结果大于20"
    }
    println("res=" + result) //返回的是()即Unit
  }
}
