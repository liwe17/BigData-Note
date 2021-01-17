package com.weiliai.chapter06.dogcase

/**
 * @Author: Doug Li
 * @Date 2021/1/17
 * @Describe: 类与对象案例
 */
object DogCaseTest {

  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.name="tomcat"
    dog.age=2
    dog.weight=6
    print(dog.say())
  }
}

class Dog{
  var name=""
  var age = 0
  var weight=0.0

  def say():Unit={
    "小狗信息如下:name="+this.name+"\t age="+this.age+"\t weight="+this.weight
  }
}