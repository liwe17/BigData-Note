package com.weiliai.chapter05.exercises

import scala.io.StdIn

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: 函数练习题
 */
object Exercise01 {

  def main(args:Array[String]):Unit={
    f1
  }


  //函数可以没有返回值案例,编写一个函数,从终端输入一个整数,打印响应的金字塔
  def f1(): Unit ={
    val num = StdIn.readInt()
    var spaceNum = num / 2
    for(i <- 1 to num if i%2!=0){
        for(_ <- 0 to spaceNum){
          print(" ")
        }
        for(_ <- 1 to i) {
          print("*")
        }
      println()
      spaceNum=spaceNum-1
    }
  }

}
