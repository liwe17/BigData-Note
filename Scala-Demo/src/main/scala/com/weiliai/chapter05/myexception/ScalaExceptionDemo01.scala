package com.weiliai.chapter05.myexception

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: Scala异常举例
 * <p>
 *   说明
 *   1. 在scala中只有一个catch
 *   2. 在catch中有多个case,每个case可以匹配一种异常 case ex:ArithmeticException
 *   3. =>关键符号,表示后面是对该异常的处理代码块
 *   3. finally 最终要执行的
 * </p>
 */
object ScalaExceptionDemo01 {

  def main(args: Array[String]): Unit = {

    try {
      val r = 10 / 0
    } catch {
      case ex: ArithmeticException => {
        println("捕获除数为0的算数异常")
      }
      case ex: Exception => println("捕获了异常")
    } finally {
      //最终要执行的代码
      println("scala finally...")
    }
    println("ok,继续执行")
  }

  //Nothing是任何类型的子类型
  def test()={
    throw new ArithmeticException("算数异常")
  }

}
