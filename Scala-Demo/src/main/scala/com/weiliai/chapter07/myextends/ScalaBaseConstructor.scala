package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/6
 * @Describe: Scala构造器
 */
object ScalaBaseConstructor {

  def main(args:Array[String]):Unit={
    /**
     * Person700...
     * 默认的名字
     * Emp700...
     */
    val emp = new Emp700

    println("======================")

    /**
     * Person700...
     * 默认的名字
     * Emp700...
     * Emp700 辅助构造器
     */
    val emp2 = new Emp700("mary")

    println("************************")

    /**
     * Person700...
     * Emp800...
     */
    val emp3 = new Emp800("terry",10)

  }

}

//父类Person
class Person700(pName:String){
  var name = pName
  println("Person700...")
  def this(){
    this("默认的名字")
    println("默认的名字")
  }
}

class Emp700 extends Person700{

  println("Emp700...")

  //辅助构造器
  def this(name:String){
    this//必须调用主构造器
    this.name=name
    println("Emp700 辅助构造器")
  }

}

class Emp800(eName:String,eAge:Int) extends Person700(eName){

  println("Emp800...")

  //辅助构造器
  def this(name:String){
    this(name,100)//必须调用主构造器
    this.name=name
    println("Emp 辅助构造器~")
  }

}
