package com.weiliai.chapter02.iden

object IdenDemo01 {

  def main(args:Array[String]):Unit={

    //首字符为操作符(比如+ - * / )，后续字符也需跟操作符 ,至少一个
    val ++ = "hello,world"
    println(++)

    val -+ = 90
    // val +q = "acb" //error

    //用反引号`....`包括的任意字符串
    val `true` = "hello,scala!"
    println("内容="+`true`)

    var Int = 90.5
    println("Int="+Int)

    //不能使用下划线做标识符
    var _ = "123"
    //println(_)
  }

}
