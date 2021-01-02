package com.weiliai.chapter04.ifelse

import scala.math._;

/**
 * @Author: Doug Li
 * @Date 2021/1/1
 * @Describe: 多分支练习
 */
object Exercise02 {

  def main(args:Array[String]):Unit={
    /**
     * 求 ax2+bx+c=0方程的根,abc分别为函数的参数,如果:b2-4ac>0,则有两个解:
     * b2-4ac=0,则有一个解:b2-4ac<0,则无解:[a=3 b=100 c=6]
     * 提示1: x1=(-b+sqrt(b2-4ac))/2a
     *        x2=(-b-sqrt(b2-4ac))/2a
     * 提示2: sqrt(num) 在scala包中默认引入的math包对象有很多方法可以直接可用
     */
    val a=3
    val b=100
    val c=6
    val m=b*b-4*a*c
    var x1=0.0
    var x2=0.0
    if(m>0){
      x1=(-b+sqrt(m))/2*a
      x2=(-b-sqrt(m))/2*a
      println("有两个解x1="+x1.formatted("%.2f")+"x2="+x2.formatted("%.2f"))
    }else if(m==0){
      x1=(-b+sqrt(m))/2*a
      println("有一个解x1="+x1)
    }else{
      println("无解...")
    }
  }

}
