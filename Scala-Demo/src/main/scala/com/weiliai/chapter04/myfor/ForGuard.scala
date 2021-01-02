package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 循环守卫
 */
object ForGuard {

  def main(args:Array[String]):Unit={
    for(i <- 1 to 3 if i!=2){
      println(i+"")
    }
    println()
  }
}
