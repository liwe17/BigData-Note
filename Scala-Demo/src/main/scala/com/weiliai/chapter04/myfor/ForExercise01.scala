package com.weiliai.chapter04.myfor

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: for循环练习题
 */
object ForExercise01 {

  def main(args: Array[String]): Unit = {
    /**
     * 打印1~100所有是9的倍数的整数的个数及总和
     */
    var sum=0
    var count=0
    val start=1
    val end=100
    for(i <- start to end;if i%9==0){
      count+=1
      sum+=i
    }
    println(s"count=$count,sum=$sum")

    val num=6
    for(i <-0 to num){
      printf("%d+%d=%d\n",i,(num-i),num)
    }
  }
}
