package com.weiliai.chapter04.mutlfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 打印99乘法表
 */
object Exercise02 {

  def main(args: Array[String]): Unit = {
    for(i <- 1 to 9){
      for(j <- i to 9){
        print(i+"*"+j+"="+(i*j)+"\t")
      }
      println()
    }
  }

}
