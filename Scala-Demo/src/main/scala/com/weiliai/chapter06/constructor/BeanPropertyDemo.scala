package com.weiliai.chapter06.constructor

import scala.beans._

/**
 * @Author: Doug Li
 * @Date 2021/1/26
 * @Describe: 生成Java规范的getXxx和setXxx方法,boolean isXXX
 */
object BeanPropertyDemo{

  def main(args:Array[String]):Unit={
    val car = new Car
    println(car.isRed)
    println(car.red)
    println(car.setRed(true))

    println(car.name)
  }

}


class Car{

  @BeanProperty var name:String =_

  @BooleanBeanProperty var red:Boolean =_

}
