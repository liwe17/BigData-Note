package com.weiliai.chapter03.assignoper

object Demo01 {

  def main(args: Array[String]): Unit = {
    var num =2
    num <<= 2
    num >>= 1
    println(num)

    //在scala中支持代码块,返回值
    val res = {
      if(num>1) "hello,ok" else 100
    }
    println("res="+res)

    //有两个变量,a和b,要求将其进行交换,但是不允许使用中间变量
    var a = 10
    var b =20
    a=a+b
    b=a-b
    a=a-b
  }
}
