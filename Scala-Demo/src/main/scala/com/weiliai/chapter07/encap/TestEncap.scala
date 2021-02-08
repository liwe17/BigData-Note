package com.weiliai.chapter07.encap


/**
 * @Author: Doug Li
 * @Date 2021/1/31
 * @Describe: 封装
 */
object TestEncap {
  def main(args: Array[String]): Unit = {
    val person = new Person
    println(person)
  }
}

//不能随便查看别人的年龄,工资等因袭,并对输入的年龄进行合理的验证
class Person{
  var name:String =_
  // var age 当时public时,可以随意修改不安全
  var age:Int=_
  private var salary:Float=_
  private var job:String = _

  def setAge(age:Int): Unit ={
    if(age>=0 && age<=120){
      this.age =age
    }else{
      println("输入的数据不合理")
      this.age=20
    }
  }
}
