package com.weiliai.chapter04.ifelse

import scala.io.StdIn

/**
 * @Author: Doug Li
 * @Date 2021/1/1
 * @Describe:多分支
 */
object Demo03{

    def main(args:Array[String]):Unit={
      println("请输入成绩")
      val score=StdIn.readDouble()
      if(score==100){
        println("成绩为100分时,奖励一辆BMW")
      }else if(score>80&&score<=90){
        println("成绩为(80,99]时,奖励一台iPhone7plus")
      }else{
        println("没有任何奖励")
      }
    }
}
