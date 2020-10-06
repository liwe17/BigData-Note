package com.weiliai.chapter01

/**
 * 只要以后看到object TestScala,应该有一个认识
 * 1. object TestScala 对应的是一个 TestScala$的一个静态对象 MODULE$
 * 2. 在我们的程序中是一个单例
 */
object TestScala {

  def main(args: Array[String]): Unit = {
    printf("hello,scala,idea...")
  }

}
