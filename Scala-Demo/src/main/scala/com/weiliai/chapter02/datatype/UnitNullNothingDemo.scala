package com.weiliai.chapter02.datatype

object UnitNullNothingDemo {

  def main(args: Array[String]): Unit = {
    val res = sayHello()
    println("res="+res) //res=()

    val dog:Dog = null
    println(dog) //null
    //var char1:Char=null
    println("ok~~~")

  }

  def sayHello():Unit={

  }

}

class Dog{

}
