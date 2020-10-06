# 大数据技术之 Scala
## 第一章 Scala语言概述
### 1.1 学习Scala原因
- Scala是Scalable Language的简写,是一门多范式(范式/编程方式[面向对象/函数式编程])的编程语言.
- Spark就是使用Scala编写的,Spark是新一代内存级大数据计算框架,是大数据的重要内容.
- 联邦理工学院洛桑(EPFL)的Martin Odersky于2001年开始设计Scala.
- Spark的兴起,带动Scala语言的发展.

### 1.2 Scala与Java以及Jvm的关系

![scala与Java以及jvm关系](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1kx7Rc3OZ0xkAgg7bqVl4ThWcMMGND0z0HBWY8lzYVgprb21WEEZYuia9JiamjibYTuTHNv6JxIx4Cg/0?wx_fmt=png)

### 1.3 Scala语言特点

Scala是一门以java虚拟机为运行环境并将面向对象和函数式编程的最佳特性结合在一起的静态类型编程语言.

- Scala是一门多范式(multi-paradigm)的编程语言,Scala支持面向对象和函数式编程.
- Scala源代码(.scala)会被编译成Java字节码(.class),然后运行于JVM之上,并可以调用现有的Java类库,实现两种语言的无缝对接.
- scala单作为一门语言来看,非常的简洁高效.

快速有效掌握Scala的建议
- 学习scala特有的语法 
- 搞清楚scala和java区别
- 如何规范的使用scala

### 1.4 Scala开发环境搭建
### 1.4.1 Windows/Linux下搭建Scala开发环境

Scala需要Java运行时库,安装Scala需要首先安装JVM虚拟机并配置好,推荐安装JDK1.8.

- 在http://www.scala-lang.org/ 下载Scala2.11.12程序安装包
- 配置Jdk的环境变量
- 配置SCALA_HOME,SCALA_HOME= D:\program\scala-2.11.12
- 将Scala安装目录下的bin目录加入到PATH环境变量在PATH变量中添加:%SCALA_HOME%\bin  
- 在终端中输入“scala”命令打开scala解释器

### 1.5 Scala的开发工具

- Java开发工具很多,比如NetBean,eclipse等等,单开发Scala可选的工具不多,主要使用IDEA.
- Idea工具开发Scala的快捷键也不是很多,所以使用相对比较简单.
- IDEA不是专门用于开发Scala的IDE,但是确是最适合开发Scala的工具,因为在我们实际工作中,大部分是开发项目,而大数据项目不可避免的会使用到Java,所以会进行Java和Scala两种语言的混合编程.而Idea可以很好的支持Java和Scala的开发

### 1.6 Scala快速开发入门
#### 1.6.1 Scala执行流程分析

![Scala执行流程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1kx7Rc3OZ0xkAgg7bqVl4TVwbaEEib8qzmVnwcLfF4NZ8FhpBfHHYibMvIWeAfgDdcsbRW3K4Aasmg/0?wx_fmt=png)

### 1.7 Scala程序开发注意事项

- Scala源文件以".scala"为扩展名.
- Scala程序的执行入口是main()函数.
- Scala语言严格区分大小写.
- Scala方法由一条条语句构成,每个语句后不需要分号(Scala语言会在每行后自动加分号),这也体现出Scala的简洁性.
- 如果在同一行有多条语句,除了最后一条语句不需要分号,其它语句需要分号

### 1.8 Scala语言转义字符

<table>
    <tr>
        <td>字符</td>
        <td>含义</td>
    </tr>
    <tr>
        <td>\t</td>
        <td>一个制表符</td>
    </tr>
    <tr>
        <td>\n</td>
        <td>一个换行符</td>
    </tr>
    <tr>
        <td>\\</td>
        <td>一个\</td>
    </tr>
    <tr>
        <td>\"</td>
        <td>一个"</td>
    </tr>
    <tr>
        <td>\r</td>
        <td>一个回车</td>
    </tr>
</table>

### 1.9 Scala语言输出的三种方式

- 字符串通过+号连接(类似java).
- printf用法(类似C语言)字符串通过%传值.
- 字符串通过$引用(类似PHP)

```scala
package com.weiliai.chapter01

object PrintDemo {
  def main(args: Array[String]): Unit = {
    var str1: String = "hello"
    var str2: String = "world!"
    printf(str1 + str2)

    var name:String="tom"
    var age:Int = 10
    var sal:Float = 10.57f
    var height:Double =100.15
    //格式化输出
    printf("名字:%s 年龄是%d 薪水%f 身高%.3f",name,age,sal,height)

    //支持$输出内容
    printf(s"个人信息如下: \n 名字$name \n年龄$age")

    //如果字符串中出现了类似${age+10},则表示{}是一个表达式
    printf(s"个人信息如下: \n 名字${name+10} \n年龄${age+10}")
  }
}
```

### 1.10 Scala 注释

Scala中的注释类型

- 单行注释,格式: //注释文字  
- 多行注释,格式: /*  注释文字 */
- 文档注释,格式: /** 注释文字 */

## 第二章 变量

### 2.1 变量是程序的基本组成单位

- 一个程序就是一个世界,在scala中一切都是对象.
- 不论是使用哪种高级程序语言编写程序,变量都是其程序的基本组成单位.

```scala

```


