//代码说明
//1. package com.weiliai{} 表示我们创建了包 com.weiliai,在{}中我们可以继续写它的子包scala //com.weiliai.scala.
// 还可以写类,特质trait,还可以写object
//2. scala支持一个文件中,可以同时创建多个包,以及给各个包创建类,特质和object

package com.weiliai{ //包 com.weiliai

  package scala{ //包 com.weiliai.scala

    class Person{ //表示在com.weiliai.scala下创建类Person

      val name = "Nick"

      def play(message:String):Unit={
        println(this.name+" "+message)
      }
    }

    object Test100{ //表示在com.weiliai.scala创建object Test
      def main(args: Array[String]): Unit = {
        println("ok")
        //可以直接使用父包的内容
        val user = new User
        println("user="+user)

        val user2 = new com.weiliai.scala2.User
        println("user2="+user2)

      }
    }
  }

  class User { //在com.weiliai包下创建User类
      def sayHello(): Unit ={
        //使用com.weiliai.scala2.Monster
        import com.weiliai.scala2.Monster
        val monster = new Monster
        println(monster)
      }
  }

  package scala2{ //创建包com.weiliai.scala2

    class User //在包com.weiliai.scala2下创建个User类

    class Monster
  }

}


