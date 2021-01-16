package com.weiliai.chapter05.fundetails

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: 形参默认值
 */
object Details04 {

  def main(args: Array[String]): Unit = {
    println(sayOk())
    println(sayOk("mary"))
  }

  //name 形参默认值为jack
  def sayOk(name:String="jack")={
    name+" ok!"
  }

}
