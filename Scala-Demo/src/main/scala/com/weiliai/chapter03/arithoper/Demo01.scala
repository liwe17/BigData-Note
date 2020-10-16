package com.weiliai.chapter03.arithoper

object Demo01 {
  def main(args: Array[String]): Unit = {

    // /的使用
    var r1 : Int = 10 / 3 // 3
    println("r1=" + r1)
    var r2 : Double = 10 / 3 //3.0
    println("r2=" + r2)
    var r3 : Double = 10.0 / 3 //3.333333333
    println("r3=" + r3 )
    println("r3=" + r3.formatted("%.2f") ) // 3.33

    //% 的使用
    // % 的规则: a%b = a-a/b*b
    println(10 % 3) //1
    println(-10 % 3) //-1
    println(-10 % -3) //-1
    println(10 % -3) //1

    // ++ --
    // scala 没有 ++ --,使用+= -=
    var num1 =10
    num1 += 1
    num1 -= 1
    println(num1)
  }
}
