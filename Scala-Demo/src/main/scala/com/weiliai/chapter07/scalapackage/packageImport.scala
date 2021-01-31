package com.weiliai.chapter07.scalapackage

import scala.beans.BeanProperty
import scala.collection.mutable

/**
 * @Author: Doug Li
 * @Date 2021/1/27
 * @Describe: 包导入
 */
object packageImport {

  //在scala中,java.lang包,scala包,Predef包

  def main(args: Array[String]): Unit = {
    
  }

  def test():Unit={
    //可以使用选择器,选择引入包的内容,这里,我们只HashMap,HashSet
    import scala.collection.mutable.{HashMap,HashSet}
    var map = new HashMap()
    var set = new HashSet()
  }

  def test2():Unit={
    //下面的含义是将java.util.HashMap重命名为JavaHashMap
    import java.util.{HashMap=>JavaHashMap,List}
    import scala.collection.mutable._
    var map = new HashMap() //此时的HashMap指向的是scala中的HashMap
    var map1 = new JavaHashMap() //此时使用的Java中HashMap的别名
  }

  def test3():Unit={
    import java.util.{HashMap=>_,_} //含义为引入Java.util包的所有类,但是忽略HashMap类
    var map = new mutable.HashMap() //此时的HashMap指向scala中的HashMap
  }

}

class User{
  import scala.beans.BeanProperty
  @BeanProperty var name = ""
}

class Dog{
  @BeanProperty var name:String = "" //可以吗?
}
