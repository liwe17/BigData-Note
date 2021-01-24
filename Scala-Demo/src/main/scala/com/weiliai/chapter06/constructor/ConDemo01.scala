package com.weiliai.chapter06.constructor

/**
 * @Author: Doug Li
 * @Date 2021/1/21
 * @Describe: 构造器入门
 */
object ConDemo01 {

  def main(args:Array[String]):Unit={
      val p1 = new Person("jack",20)
      println(p1)

      val a = new A
      val a2 = new A()

      //下面这句话就会调用def this(name:String)
      val p2 = new Person("tom")
  }

}

//构造器快速入门
//创建Person对象同时初始化对象的age属性和name属性值
class Person(inName:String,inAge:Int){

  var name: String =inName
  var age:Int = inAge

  age+=10
  println("~~~~~")

  override def toString: String = {
    "name="+this.name+"\t age="+this.age
  }

  println("ok")

  println("age="+age)

  def this(name:String){
    //this
    this("jack",10)
    this.name=name
  }
}

class A{

}
