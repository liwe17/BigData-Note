package com.weiliai{ //包 com.weiliai

  //说明:
  //1. 在包中直接写方法,或者定义变量,就错误==>使用包对象的技术来解决
  //2. package object scala 表示创建一个包对象scala,它是com.weiliai.scala 这个包对应的包对象
  //3. 每一个包都可以有一个包对象
  //4. 包对象的名字需要和子包一样
  //5. 在包对象中定义变量,方法
  //6. 在包对象中定义的变量和方法,就可以在对应的包中使用
  //7. 在底层这个包对象会生成两个类 package.class 和package$.class

  package object scala{

    var name = "king"

    def sayHiv():Unit={
      println("package object scala sayHI~")
    }

  }


}
