package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/8
 * @Describe: 匿名子类案例
 */
object ScalaNoNameDemo02 {

  def main(args:Array[String]):Unit={
    val monster = new Monster {
      override var name: String = _

      override def cry(): Unit = {
        println("妖怪嗷嗷叫...")
      }
    }

    monster.cry()
  }

}

abstract class Monster{

  var name:String

  def cry()
}
