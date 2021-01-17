package com.weiliai.chapter06.method

/**
 * @Author: Doug Li
 * @Date 2021/1/17
 * @Describe: 练习
 */
object MethodDemo02 {
  def main(args: Array[String]): Unit = {
    // 编写类(MethodExec),编写一个方法,不需要参数,在方法中打印10*8的矩形
    val exec = new MethodExec
    exec.printRect()
    // 类中编写一个方法计算矩形面积,保留两位小数
    exec.width=10
    exec.len=10
    exec.area()
  }

}

class MethodExec{

  var len=0.0
  var width=0.0

  def printRect(): Unit ={
    for(_<- 1 to 10){
      for(_<- 1 to 8){
        print("*")
      }
      println()
    }
  }

  def area()={
    (this.len*this.width).formatted("%.2f").toDouble
  }

}

