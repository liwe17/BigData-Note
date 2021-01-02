package com.weiliai.chapter04.mycontinue

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: continue案例代码
 */
object ContinueDemo {

  def main(args:Array[String]):Unit={

    /**
     * 说明
     * 1. 1 to 10
     * 2. 循环守卫if(i!=2 && i!=3)这个条件为true,才执行循环体,即当i==2或者i==3时,跳过
     */
    for(i <- 1 to 10;if(i!=2 && 1!=3)){
      println("i="+i)
    }

    println("===============================")
    for(i <- 1 to 10){
      if(i!=2 && 1!=3){
        println("i="+i)
      }
    }

  }

}
