package com.weiliai.chapter04.homework

import scala.util.control.Breaks._

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: TODO 
 */
object Homework01 {

  def main(args: Array[String]): Unit = {
    /**
     * 100以内的数的求和,求出当和第一次大于20的当前数
     */
    var sum = 0
    breakable {
      for (i <- 1 to 100) {
        sum += i
        if (sum > 20) {
          println("第一次大于20的当前数" + i)
          break()
        }
      }
    }

    println("=============================")
    var loop=true
    var sum2=0
    for(i <- 1 to 100;if loop==true){
      sum2+=i
      if(sum2>20){
        println("第一次大于20的当前数" + i)
        loop=false
      }
    }
  }
}
