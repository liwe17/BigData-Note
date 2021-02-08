package com.weiliai.chapter07.myextends

/**
 * @Author: Doug Li
 * @Date 2021/2/1
 * @Describe: 子类如何继承
 */
object Extends02 {

  def main(args:Array[String]):Unit={
    val sub = new Sub
    sub.sayOK()
    sub.test100() //可以访问
    //sub.test200() //不能访问
  }

}

//父类(基类)
class Base{
  var n1:Int = 1 //public n1(),public n1_$eq()
  protected var n2:Int =2
  private var n3:Int = 3 //private n3(),private n3_$eq()

  def test100():Unit={ //public test100()
    println("base 100")
  }

  protected def test200():Unit={
    println("base 200")
  }

  private def test300():Unit={
    println("base 300")
  }
}

//Sub继承Base
class Sub extends Base{

  def sayOK():Unit={
    this.n1 = 20 //本质访问this.n1_$eq()
    this.n2 = 40

    println("范围 "+this.n1+this.n2)
    //test100();
    //test200();
  }
}
