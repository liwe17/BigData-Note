package com.weiliai.chapter05.fundetails

/**
 * @Author: Doug Li
 * @Date 2021/1/16
 * @Describe: return关键字省略
 */
object Details02 {

  def main(args: Array[String]): Unit = {
    println(getSum3(1,2)) //()
    println(f3("12"))
  }

  //如果写return,返回值就不能省略
  def getSum(n1:Int,n2:Int):Int={
    return n1+n2
  }

  //如果返回值什么都没有写,表示该函数没有返回值
  //即使有return也无效
  def getSum2(n1:Int,n2:Int){
    return n1+n2
  }

  //如果函数明确声明无返回值(声明Unit),那么函数体中即使使用return关键字也会有返回值
  def getSum3(n1:Int,n2:Int):Unit={
    return n1+n2
  }

  //如果明确函数无返回值或不确定返回值类型,那么返回值类型可以省略或声明为Any
  def f3(s:String)={
    if(s.length>2){
      s+"123"
    }else{
      3
    }
  }
  //和上面f3等价
  def f4(s:String):Any={
    if(s.length>2){
      s+"123"
    }else{
      3
    }
  }

  def f5="com.weiliai"
  //f5<=>f6
  def f6():String={
    "com.weiliai"
  }

}
