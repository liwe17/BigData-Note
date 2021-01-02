package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 循环步长控制
 */
object StepFor {
  def main(args: Array[String]): Unit = {
    //def apply(start: Int, end: Int, step: Int): Range = new Range(start, end, step)
    for(i <- Range(1,10,2)){
      println("i="+i)
    }

    //循环守卫
    for(i <- 1 to 10;if i%2==1){
      println("i="+i)
    }
  }
}
