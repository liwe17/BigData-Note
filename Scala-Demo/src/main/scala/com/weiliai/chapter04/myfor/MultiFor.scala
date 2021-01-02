package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 嵌套for
 */
object MultiFor {

  def main(args: Array[String]): Unit = {
    for (i <- 1 to 3; j <- 1 to 3; k <- 1 to 3; l <- 1 to 3) {
      println("i=" + i + " j=" + j + " k=" + k + " l=" + l)
    }

    for (i <- 1 to 3; j <- 1 to 3) {
      println("i=" + i + " j=" + j)
    }

    //等价

    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        println("i=" + i + " j=" + j)
      }
    }
  }

}
