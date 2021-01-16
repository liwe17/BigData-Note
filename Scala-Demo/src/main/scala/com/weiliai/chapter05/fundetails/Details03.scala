package com.weiliai.chapter05.fundetails

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: 语法灵活可嵌套
 */
object Details03 {

  def main(args: Array[String]): Unit = {
    //方法中声明方法
    def f1(){ //ok private final
      println("f1")
    }

    println("OK~")

    def sayOk(){ //private final sayOk$1()
      println("main sayOk")

      def sayOk(){ //private final sayOk$2()
        println("sayOk sayOk")
      }

    }
  }

  def sayOk(): Unit ={ //成员
    println("sayOk")
  }

}
