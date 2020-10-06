package com.weiliai.chapter01.vars

object VarDemo02 {

  def main(args:Array[String]):Unit={

    //类型推导
    var num = 10 //这时num就是Int
    //方式1,可以利用idea的提示证明
    //方式2,使用isInstanceof[Int]判断
    println(num.isInstanceOf[Int])

    //类型确定后,就不能修改,说明scala时强数据类型语言
    //num = 2.3 错误

    //在声明/定义一个变量时,可以使用var或者val来修饰,var修饰的变量可改变,val修饰的变量不可改
    var age = 10
    age = 20

    val num2 = 30
    // num2 = 40 val修饰的变量是不可以改变

    //scala设计者为什么设计 var和val
    //1.实际编程中,更多的是获取/创建一个对象后,读取该对象的属性或修改对象的属性值,很少改变对象本身
    //2.因为val没有线程安全性问题,效率高,推荐使用val
    //3.如果对象需要改变,使用var
    //4. val修饰的变量在编译后,等同于加上final,通过反编译看下底层代码
    val dog = new Dog
    // dog = new Dog Reassignment to val
    dog.age=50
    dog.name="小花"

  }

}

class Dog{
  //声明一个age属性,给一个默认值
  var age:Int =0
  //声明名字
  var name:String = ""
}
