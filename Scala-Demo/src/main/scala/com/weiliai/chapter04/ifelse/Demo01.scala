package com.weiliai.chapter04.ifelse

import scala.io.StdIn
;

/**
 * @Author: Doug Li
 * @Date 2021/1/1
 * @Describe: 单分支案例
 */
object Demo01 {

  def main(args:Array[String]):Unit={
    println("输入年龄")
    val age=StdIn.readInt()
    if(age>18){
      println("age>18")
    }
  }

  //小技巧,如何查看某个包下包含的内容
  //1.比如我们想看scala.io包有什么内容
  //2.将光标放在io上即可,输入ctrl+b
  //3.将光标放在StdIn上即可,输入ctrl+b,看的是StdIn源码
}
