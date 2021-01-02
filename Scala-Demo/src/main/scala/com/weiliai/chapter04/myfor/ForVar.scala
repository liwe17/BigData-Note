package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 引入变量
 */
object ForVar {

  def main(args:Array[String]):Unit={
    for(i <- 1 to 3;j=4-i){
      print(j)
    }
  }
}
