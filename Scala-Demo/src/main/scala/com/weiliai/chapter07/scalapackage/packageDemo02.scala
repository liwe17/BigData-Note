package com.weiliai.scala2

import scala.beans.BeanProperty

class Manager(var name:String){

  //第一种形式[相对路径引入包]
  @BeanProperty var age:Int=_

  //第二种形式,相对路径导入
  @scala.beans.BeanProperty var age2:Int = _

  //第三种形式,绝对路径引入,可以解决包名冲突
  @_root_.scala.beans.BeanProperty var age3:Int=_

}

object TestBean{
  def main(args:Array[String]):Unit={
    val m = new Manager("jack")
    println("m="+m)
  }
}
