package com.weiliai.chapter02.datatype

object CharDemo {

  def main(args:Array[String]):Unit={

    var char1:Char=97
    //当我们输出一个char类型,它会输出该数字对应的字符(码值表 unicode)
    println("char1="+char1) //a

    var char2:Char='a'
    var num = 10+char2
    println("num="+num) //107

    var char3:Char='\n'
    println("char3"+char3+".")

    //1.当把一个计算的结果赋值一个变量,则编译器会进行类型转换及判断(都会看范围+类型)
    //2.当把一个字面量赋值一个变量,则编译器会进行范围的判定
    //var c2:Char = 'a'+1 error
  }

}
