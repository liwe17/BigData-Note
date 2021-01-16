package com.weiliai.chapter05.fundetails

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: 可变参
 *
 *  基本语法:
 *    def sum(args:Int*)
 *    def sum(n1:Int,args:Int*)
 *  注意事项:
 *  1. args是集合,通过for循环可以访问到各值
 *  2. 可变参数需要写在形参列表最后
 */
object VarParameters {

  def main(args: Array[String]): Unit = {
    //编写一个函数sum,可以求出 1 到 多个Int 的和
    println(sum(1,10,30,50))
  }

  //可变参数需要放到最后,否则报错
  def sum(n1:Int,args:Int*):Int={
    println("args.length="+args.length)
    //遍历
    var sum = n1
    for(item <- args){
      sum += item
    }
    sum
  }
}
