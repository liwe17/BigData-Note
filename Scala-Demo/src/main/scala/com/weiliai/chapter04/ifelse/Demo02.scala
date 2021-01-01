package com.weiliai.chapter04.ifelse

/**
 * @Author: Doug Li
 * @Date 2021/1/1
 * @Describe: 可以输入人的年龄,如果该同志的年龄大于18岁,则输出"age>18",否则,输出"age<=18"
 */
object Demo02 {

  def main(args:Array[String]):Unit={
    val age=6
    if(age>18){
      println("age>18")
    }else{
      println("age<=18")
    }
  }
}
