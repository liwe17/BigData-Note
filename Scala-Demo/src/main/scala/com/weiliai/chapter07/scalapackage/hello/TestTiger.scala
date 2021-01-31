package com.weiliai.chapter07.scalapackage.hello

/**
 * @Author: Doug Li
 * @Date 2021/1/27
 * @Describe: 包名和源码所在目录可以不一致
 * <p>
 *    package com.weiliai.chapter07.scalapackage.hello
 *    修改如下,不报错,class文件自动生成到hello2
 *    package com.weiliai.chapter07.scalapackage.hello.hello2
 * </p>
 */
object TestTiger {

  def main(args: Array[String]): Unit = {
    //使用xh的Tiger
    val tiger1 = new com.weiliai.chapter07.scalapackage.xh.Tiger
    val tiger2 = new com.weiliai.chapter07.scalapackage.xm.Tiger
    println(tiger1+" "+tiger2)
  }
}

class Employee