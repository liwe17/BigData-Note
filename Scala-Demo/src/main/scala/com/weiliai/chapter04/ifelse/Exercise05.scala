package com.weiliai.chapter04.ifelse

import scala.io.StdIn

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 多分支案例2
 */
object Exercise05 {

  def main(args: Array[String]): Unit = {
    /**
     * 4_10 旺季
     *  成人(18-60):60
     *  儿童(<18):半价
     *  老人(>60):1/3
     * 淡季
     *  成人:40
     *  其他:20
     */
    println("请输入月份")
    val month=StdIn.readInt()
    println("请输入年龄")
    val age=StdIn.readInt()
    val ticket=60
    if(month>10 || month<4){
      if(age<18 || age >60){
        println("您的票价是20")
      }else{
        println("您的票价是40")
      }
    }else{
      if(age<18){
        println("您的票价"+ticket/2)
      }else if(age>60){
        println("您的票价"+ticket/3)
      }
      println("您的票价"+ticket)
    }
  }

}
