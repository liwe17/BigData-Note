package com.weiliai.chapter02.datatype


object TypeDemo03 {
  def main(args: Array[String]): Unit = {
    //long的取值范围:9223372036854775807~-9223372036854775808
    //num1 is 2.2345679 	 num2 is 2.2345678912

    println("long的取值范围:"+Long.MaxValue+"~"+Long.MinValue)

    var i = 10 //i Int
    var j = 10L // j Long
    var e = 9223372036854775807L //9223372036854775807 超过Int

    //2.2345678912f  , 2.2345678912
    var num1:Float=2.2345678912f
    var num2:Double=2.2345678912
    println(s"num1 is $num1 \t num2 is $num2")

  }
}
