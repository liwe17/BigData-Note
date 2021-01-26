package com.weiliai.chapter06.constructor

/**
 * @Author: Doug Li
 * @Date 2021/1/26
 * @Describe: 构造器参数
 */
object ConDemo04 {

  def main(args: Array[String]): Unit = {
    val worker = new Worker("smith")
    println(worker.name) //不能访问inName

    val worker2 = new Worker2("smith2")
    println(worker2.inName) //可以访问inName
    //worker2.inName = _ 不能修改

    val worker3 = new Worker3("smith3")
    worker3.inName="marry"
    println(worker3.inName)

  }
}

//1. inName是一个局部变量
class Worker(inName:String){
  var name= inName
}

//2. inName是worker2的一个private的只读属性
class Worker2(val inName:String){
  var name = inName
}

//3. inName是worker3的一个private的可以读写属性
class Worker3(var inName:String){
  var name=inName
}

