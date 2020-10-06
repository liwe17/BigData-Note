package com.weiliai.chapter02.dataconvert

object Demo01 {
  def main(args: Array[String]): Unit = {
    var n1=10
    var n2=1.1f
    // n3自动转换为精度高的Float
    var n3=n1+n2

    var n4:Byte = 10
    // var char1:Char = n4 错误,因为byte不能自动转换为char

  }
}
