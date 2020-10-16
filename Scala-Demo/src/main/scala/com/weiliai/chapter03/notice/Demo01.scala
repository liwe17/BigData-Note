package com.weiliai.chapter03.notice

/**
 * Scala不支持三目运算符,在Scala中是if-else替换
 */
object Demo01 {
  def main(args:Array[String]): Unit = {

    /**
     * 案例1: 求两个数中的最大数
     * 案例2: 求三个数中的最大数
     */
    val n1 = 4
    val n2 = 8
    var res = if(n1>n2) n1 else n2
    println("res="+res)

    val n3 = 11
    res = if(res > n3) res else n3
    println("res="+res)

  }

}
