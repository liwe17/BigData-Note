package com.weiliai.chapter06.constructor

/**
 * @Author: Doug Li
 * @Date 2021/1/24
 * @Describe: 构造器
 */
object ConDemo03 {

  def main(args:Array[String]):Unit={
    val p2= new Person2
    println(p2)
  }

}


//定义了一个Person2类
class Person2(){

  var name:String = _

  var age:Int = _

  def this(name:String){
    //辅助构造器无论直接或间接,最终都一定要调用主构造器,执行主构造器的逻辑
    //而且需要放在辅助构造器的第一行,这点和Java一样,Java中一个构造器要调用同类的构造器也要放在第一行
    this //直接调用主构造器
    this.name = name
  }

  //辅助构造器
  def this(name:String,age:Int){
    this
    this.name = name
    this.age = age
  }

  def this(age:Int){
    this("匿名") // 调用主构造器,因为def this(name:String) 中调用了主构造器
    this.age = age
  }

  def showInfo(): Unit ={
    println("Person2信息如下:")
    println("name="+this.name)
    println("age="+this.age)
  }

}
