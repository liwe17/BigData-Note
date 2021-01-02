package com.weiliai.chapter04.mywhile

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: while循环案例
 */
object WhileDemo01 {

  def main(args:Array[String]):Unit={
    /**
     * 输出10遍 hello world
     */
    var num = 0
    while(num<10){
      println("hello world"+num)
      num+=1;
    }
  }
}
