package com.weiliai.chapter01

object PrintDemo {
  def main(args: Array[String]): Unit = {
    var str1: String = "hello"
    var str2: String = "world!"
    printf(str1 + str2)

    var name:String="tom"
    var age:Int = 10
    var sal:Float = 10.57f
    var height:Double =100.15
    //格式化输出
    printf("名字:%s 年龄是%d 薪水%f 身高%.3f",name,age,sal,height)

    //支持$输出内容
    printf(s"个人信息如下: \n 名字$name \n年龄$age")

    //如果字符串中出现了类似${age+10},则表示{}是一个表达式
    printf(s"个人信息如下: \n 名字${name+10} \n年龄${age+10}")
  }
}
