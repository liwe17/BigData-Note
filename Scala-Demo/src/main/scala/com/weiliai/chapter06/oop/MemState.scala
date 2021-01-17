package com.weiliai.chapter06.oop

/**
 * @Author: Doug Li
 * @Date 2021/1/17
 * @Describe: 类和对象内存分配机制
 */
object MemState {

  def main(args: Array[String]): Unit = {

    val p1 = new Person2
    p1.name = "jack"
    p1.age = 10

    val p2 = p1
    println(p1==p2)//true
    p1.name = "tom"
    println("p2.name"+p2.name)
  }

}

class Person2{
  var name=""
  var age:Int=_ //如果用_方式给默认值,则属性必须指定类型
}
