package com.weiliai.chapter05.recursive

/**
 * @Author: Doug Li
 * @Date 2021/1/4
 * @Describe: 递归案例
 */
object Exercise01 {

  def main(args:Array[String]):Unit={
    println(fbn(6))
    println(peach(1))
  }


  /**
   * 斐波那契数
   * 1,1,2,3,5,8.....
   * 递归实现
   */
  def fbn(n:Int):Int={
    if(n==1){
      1
    }else if(n==2){
      1
    }else{
      fbn(n-1)+fbn(n-2)
    }
  }

  /**
   * 已知 f(1)=3;f(n)=2*f(n-1)+1
   */
  def f1(n:Int):Int={
    if(n==1){
      3
    }else{
      2*f1(n-1)+1
    }
  }

  /**
   * 有一堆桃子,猴子第一天吃了其中的一半,并再吃多了一个!以后每天猴子都吃其中的一半,然后在多吃一个
   * 当到第十天时,想再吃时(还没吃),发现只有一个1个桃子,问题:最初多少个桃子?
   * 第10天 1
   * 第9天 x x-x/2-1=1
   * 第8天 ((1+1)/2+1)2
   *
   */
  def peach(n:Int):Int={
    if(n==10){
      1
    }else{
      (peach(n+1)+1)*2
    }
  }


}
