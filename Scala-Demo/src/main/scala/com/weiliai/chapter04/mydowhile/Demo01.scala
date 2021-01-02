package com.weiliai.chapter04.mydowhile

/**
 * @Author: Doug Li
 * @Date 2021/1/2
 * @Describe: do..while
 */
object Demo01 {

  def main(args:Array[String]):Unit={
    var i=0
    do{
      print(s"$i+ hello world\n")
      i+=1
    }while(i<10)
  }
}
