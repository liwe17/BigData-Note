package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: for循环1
 */
object ForDemo01 {

  def main(args:Array[String]):Unit={
    //输出10句"hello,world"
    //说明 start to end 前后闭合
    for(i<- 0 to 10){
      println(i+"hello,word")
    }

    val list=List("hello",10,30,"tom")
    for(item <- list){
      println("item="+item)
    }
  }
}
