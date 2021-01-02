package com.weiliai.chapter04.myfor;

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: 循环返回值
 */
object YieldFor {

  def main(args:Array[String]):Unit={
    //说明 val res=for(i <- 1 to 10) yield i含义
    //1. 对1 to 10进行遍历
    //2. yield i 将每次循环得到的i放入集合Vector中,并返回给res
    //3. i这里是一个代码块,这就意味我们可以对i进行处理
    //4. 下面的这个方式,就体现出scala的一个重要的语法特点,就是将一个集合中各个数据进行处理,返回给新的集合
    val res=for(i <- 1 to 10) yield {
      if(i%2==0){
        i+"是偶数"
      }else{
        i+"不是偶数"
      }
    }
    println(res)
  }
}
