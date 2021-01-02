package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: for循环2
 */
object ForUnitDemo02 {

  def main(args:Array[String]):Unit={
    /**
     * hello,word 10遍
     */
    for(i <- 0 until 10){
      println("hello world"+i)
    }
  }

}
