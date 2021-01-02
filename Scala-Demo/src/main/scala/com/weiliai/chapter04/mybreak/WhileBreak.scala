package com.weiliai.chapter04.mybreak

import util.control.Breaks._

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: break应用
 */
object WhileBreak {

  def main(args:Array[String]):Unit={
    /**
     * breakable()函数
     * 说明
     * 1.breakable是一个高阶函数:可以接收函数的函数就是高阶函数
     * 2.def breakable(op:=>Unit){
     *  try{
     *    op
     *  }catch{
     *    case ex:BreakControl=>
     *      if(ex ne breakException) throw ex
     *  }
     * }
     *
     * (1)op:=>Unit 表示接收的参数是一个没有输入,也没有返回值的函数
     * (2)即可以简单理解可以接受一段代码块
     * 3.breakable对break()抛出的异常做了处理,代码就继续执行
     * 4.当我们传入的是代码块,scala程序员会将()改成{}
     *
     */
    var n=1
    breakable{
      while(n<=20){
        n+=1
        println("n="+n)
        if(n==18){
          /**
           * 中断while
           * 说明
           * 1.在scala中使用函数式的break函数中断循环
           * 2.def break():Nothing={throw breakException}
           */
          break()
        }
      }
    }
    println("ok~~")
  }

}
