package com.weiliai.chapter04.ifelse

import scala.io.StdIn

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 嵌套案例1
 */
object Exercise04 {

  def main(args:Array[String]):Unit={
    /**
     * 参加百米运动会,如果用时8秒内进入决赛,否则提示淘汰,并且根据性别提示进入男子组或女子组
     * 输入成绩和性别,进行判断
     */
    println("请输入成绩")
    val speed = StdIn.readDouble()
    if(speed<=8){
      println("请输入性别")
      val gender=StdIn.readChar()
      if('男'==gender){
        println("进入男子组")
      }else{
        println("进入女子组")
      }
    }else{
      println("你被淘汰")
    }
  }

}
