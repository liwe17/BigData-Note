package com.weiliai.chapter07.scalapackage

/**
 * @Author: Doug Li
 * @Date 2021/1/26
 * @Describe: 测试scala包
 */
object TestTiger {

  def main(args:Array[String]):Unit={
    val tiger = new com.weiliai.chapter07.scalapackage.xm.Tiger
    val tiger1 = new com.weiliai.chapter07.scalapackage.xh.Tiger
    println(tiger+":"+tiger1)
  }
}
