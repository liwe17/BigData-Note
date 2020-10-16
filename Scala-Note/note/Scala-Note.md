# 大数据技术之 Scala 1
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
def main(args: Array[String]): Unit = {
  var a: Int = 1 //定义一个整型变量,取名a,并赋初值1
  var b: Int = 3 //定义一个整型变量,取名b,并赋初值3
  b = 89 //给变量b 赋 89
  println("a=" + a) //输出语句,把变量a的值输出
  println("b=" + b) //把变量b的值输出
}
```

### 2.2 变量的介绍
#### 2.2.1 概念

变量相当于内存中一个数据存储空间的表示,你可以把变量看做是一个房间的门牌号,通过门牌号我们可以找到房间,而通过变量名可以访问到变量(值)

#### 2.2.2 变量使用的步骤

- 声明/定义变量(scala要求声明时初始化)
- 使用

### 2.3 Scala变量的基本使用
#### 2.3.1 快速入门

![内存图](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWXoiaDqy8mYRpvs9TR0eicIH7ziaxKC2xicIAEjbcE2c6hOkPPiazLKjMqMQ/0?wx_fmt=png)

```scala
def main(args:Array[String]): Unit={
    var age:Int = 10
    var sal:Double = 10.9
    var name:String = "tom"
    var isPass:Boolean = true
    //在scala中,小数默认为Double,整数默认为Int
    var score:Float = 70.9f
    println(s"$age $isPass")
  }
```
### 2.4 Scala变量使用说明
#### 2.4.1 变量声明基本用法

var|val 变量名 [:变量类型]=变量值

#### 2.4.2 注意事项

- 声明变量时,类型可以省略(编译器自动推导,即类型推导)
- 类型确定后,就不能修改,说明Scala是强数据类型语言.
- 在声明/定义一个变量时,可以使用var或者val来修饰,var修饰的变量可改变,val修饰的变量不可改.
- val修饰的变量在编译后,等同于加上final,通过反编译看下底层代码.
- var修饰的对象引用可以改变,val修饰的则不可改变,但对象的状态(值)却是可以改变的.(比如::自定义对象,数组,集合等等)
- 变量声明时,需要初始值

```scala
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
```

```
object VarDemo03 {

  var name = "hello"
  val age = 100

  def main(args: Array[String]): Unit = {
    println("ok")
  }
}

//对应的反编译
public final class VarDemo03$
{
  public static final  MODULE$;
  private String name;
  private final int age;
}
```

### 2.5 程序中+号的使用

- 当左右两边都是数值型时,则做加法运算
- 当左右两边有一方为字符串,则做拼接运算

### 2.6 数据类型
#### 2.6.1 scala数据类型介绍

- Scala与Java有着相同的数据类型,在Scala中数据类型都是对象,也就是说scala没有java中的原生类型.
- Scala数据类型分为两大类AnyVal(值类型)和AnyRef(引用类型),注意:不管是AnyVal还是AnyRef都是对象
- 相对于java的类型系统,scala要复杂些!也正是这复杂多变的类型系统才让面向对象编程和函数式编程完美的融合在了一起

```scala
object TypeDemo01 {

  def main(args:Array[String]):Unit={

    var num1: Int = 10
    //因为Int是一个类,因此他的一个实例,就是可以用很多方法
    //在scala中如果方法没有形参,则可以省略
    println(num1.toDouble+"\t"+num1.toString+100.toString)

    sayHi
    sayHi()

    var isPass = true
    println(isPass.toString)
  }

  def sayHi():Unit={
    println("say hi")
  }

}
```
#### 2.6.2 数据类型体系图

![数据类型体系图](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWvw1RK3SZ9hzJ3VpibnLfOVgYq2Mbaiap7YERXdAfIYwY93jc83Gzdtxg/0?wx_fmt=png)

体系图总结
- 在scala中有一个根类型Any,他是所有类的父类.
- 在scala中一切皆为对象,分为两大类AnyVal(值类型),AnyRef(引用类型),他们都是Any子类.
- Null类型是scala的特别类型,她只有一个值null,他是bottom class,是所有AnyRef类型的子类.
- Nothing类型也是bottom class,他是所有类的子类,在开发中通常可以将Nothing类型的值返回给任意变量或者函数,这里抛出异常很多. 
- 在scala中仍然遵守,低精度的值,向高精度的值自动转换(implicit conversion)隐式转换

```scala
object TypeDemo02 {

  def main(args: Array[String]): Unit = {
    println(sayHello)

    var num = 1.2 //默认为Double
    var num2 = 1.7f//这是Float
    num2 = num.toFloat //num2 = num error

  }

  //比如开发中,我们有一个方法,就会异常中断,这时就可以返回Nothing
  //即当Nothing做返回值,就是明确说明该方法没有正常返回值
  def sayHello:Nothing={
    throw new Exception("抛出异常")
  }
}
```

#### 2.6.3 Scala数据类型列表

![数据类型列表](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWlS7BlfnE0cbxdzj5Kv7Zg56XibhEfyiajEmoLVFdic7AXEvx2gclvcNfA/0?wx_fmt=png)

### 2.7 整数类型

### 2.7.1 基本介绍

Scala的整数类型就是用于存放整数值的,比如12,30,3456等等

### 2.7.2 整型的类型

![整数类型](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWIibqUaJHBrLQ6IuEjl7bkmYr9h1Uq7jMJuL1FeB6mgBjBSfUQ2CqibiaQ/0?wx_fmt=png)

### 2.7.3 使用细节

- Scala各整数类型有固定的表数范围和字段长度,不受具体OS的影响,以保证Scala程序的可移植性.
- Scala的整型 常量/字面量  默认为Int型,声明Long型 常量/字面量 须后加‘l’’或‘L’.
- Scala程序中变量常声明为Int型,除非不足以表示大数,才使用Long.

### 2.8 浮点类型
#### 2.8.1 基本介绍

Scala的浮点类型可以表示一个小数,比如 123.4f,7.8,0.12等等.

#### 2.8.2 浮点型分类

<table>
    <tr>
        <td>类型</td>
        <td>说明</td>
    </tr>
    <tr>
        <td>Float[4]</td>
        <td>32位,IEEE754标准的单精度浮点数</td>
    </tr>
    <tr>
        <td>Double[8]</td>
        <td>64位,IEEE754标准的双精度浮点数</td>
    </tr>
</table>

#### 2.8.3 使用细节

- 与整数类型类似,Scala浮点类型也有固定的表数范围和字段长度,不受具体OS的影响.
- Scala的浮点型常量默认为Double型,声明Float型常量,须后加‘f’或‘F’.
- 浮点型常量有两种表示形式
    - 十进制数形式:如:5.12,512.0f,.512(必须有小数点）
    - 科学计数法形式:如:5.12e2=5.12乘以10的2次方,5.12E-2=5.12除以10的2次方
- 通常情况下,应该使用Double型,因为它比Float型更精确(小数点后大致7位)

```scala
object TypeDemo03 {
  def main(args: Array[String]): Unit = {
    //long的取值范围:9223372036854775807~-9223372036854775808
    //num1 is 2.2345679 	 num2 is 2.2345678912

    println("long的取值范围:"+Long.MaxValue+"~"+Long.MinValue)

    var i = 10 //i Int
    var j = 10L // j Long
    var e = 9223372036854775807L //9223372036854775807 超过Int

    //2.2345678912f  , 2.2345678912
    var num1:Float=2.2345678912f
    var num2:Double=2.2345678912
    println(s"num1 is $num1 \t num2 is $num2")

  }
}
```
### 2.9 字符类型(Char)
#### 2.9.1 基本介绍

字符类型可以表示单个字符,字符类型是Char,16位无符号Unicode字符(2个字节),区间值为 U+0000 到 U+FFFF.

#### 2.9.2 使用细节

- 字符常量是用单引号('')括起来的单个字符.
- Scala也允许使用转义字符'\'来将其后的字符转变为特殊字符型常量.
- 可以直接给Char赋一个整数,然后输出时,会按照对应的unicode字符输出.
- Char类型是可以进行运算的,相当于一个整数,因为它都对应有Unicode码.  

```scala
object CharDemo {

  def main(args:Array[String]):Unit={

    var char1:Char=97
    //当我们输出一个char类型,它会输出该数字对应的字符(码值表 unicode)
    println("char1="+char1) //a

    var char2:Char='a'
    var num = 10+char2
    println("num="+num) //107

    var char3:Char='\n'
    println("char3"+char3+".")

    //1.当把一个计算的结果赋值一个变量,则编译器会进行类型转换及判断(都会看范围+类型)
    //2.当把一个字面量赋值一个变量,则编译器会进行范围的判定
    //var c2:Char = 'a'+1 error
  }

}
```

### 2.10 布尔类型:Boolean
#### 2.10.1 基本介绍

- 布尔类型也叫Boolean类型,Boolean类型数据只允许取值true和false.
- boolean类型占1个字节.
- boolean类型适于逻辑运算,一般用于程序流程控制.

### 2.11 Unit类型,Null类型和Nothing类型
#### 2.11.1 基本说明

<table>
    <tr>
        <td>类型</td>
        <td>说明</td>
    </tr>
    <tr>
        <td>Unit</td>
        <td>表示无值,和其他语言中void等同.用作不返回任何结果的方法的结果类型.Unit只有一个实例值,写成()</td>
    </tr>
    <tr>
        <td>Null</td>
        <td>null,Null类型只有一个实例值null</td>
    </tr>
    <tr>
        <td>Nothing</td>
        <td>Nothing类型在Scala的类层级的最低端;它是任何其他类型的子类型.当一个函数,我们确定没有正常的返回值,可以用Nothing来指定返回类型,这样有一个好处,就是我们可以把返回的值(异常)赋给其它的函数或者变量(兼容性)</td>
    </tr>
</table>

#### 2.11.2 使用细节

- Null类只有一个实例对象null,类似于Java中的null引用.null可以赋值给任意引用类型(AnyRef),但是不能赋值给值类型(AnyVal:比如 Int, Float, Char, Boolean, Long, Double, Byte, Short)
- Unit类型用来标识过程,也就是没有明确返回值的函数.由此可见,Unit类似于Java里的void.Unit只有一个实例,这个实例也没有实质的意义.
- Nothing,可以作为没有正常返回值的方法的返回类型,非常直观的告诉你这个方法不会正常返回,而且由于Nothing是其他任意类型的子类,他还能跟要求返回值的方法兼容.  

```scala
object UnitNullNothingDemo {

  def main(args: Array[String]): Unit = {
    val res = sayHello()
    println("res="+res) //res=()

    val dog:Dog = null
    println(dog) //null
    //var char1:Char=null
    println("ok~~~")

  }

  def sayHello():Unit={

  }

}

class Dog{

}
```
### 2.12 值类型转换
#### 2.12.1 值类型隐式转换

当Scala程序在进行赋值或者运算时,精度小的类型自动转换为精度大的数据类型,这个就是自动类型转换(隐式转换).

自动类型转换细节说明
- 有多种类型的数据混合运算时,系统首先自动将所有数据转换成容量最大的那种数据类型,然后再进行计算.
- 当我们把精度(容量)大的数据类型赋值给精度(容量)小的数据类型时,就会报错,反之就会进行自动类型转换.
- (byte, short) 和 char之间不会相互自动转换.
- byte,short,char他们三者可以计算,在计算时首先转换为int类型.
- 自动提升原则:表达式结果的类型自动提升为操作数中最大的类型.

```scala
object Demo01 {
  def main(args: Array[String]): Unit = {
    var n1=10
    var n2=1.1f
    // n3自动转换为精度高的Float
    var n3=n1+n2

    var n4:Byte = 10
    // var char1:Char = n4 错误,因为byte不能自动转换为char
  }
}
```

#### 2.12.2 高级隐式转换和隐式函数

scala还提供了非常强大的隐式转换机制(隐式函数,隐式类等等),我们放在高级部分专门用一个章节来讲解.

#### 2.12.3 强制类型转换

自动类型转换的逆过程,将容量大的数据类型转换为容量小的数据类型.使用时要加上强制转函数,但可能造成精度降低或溢出,格外要注意

强制类型转换细节说明
- 当进行数据的 从 大——>小,就需要使用到强制转换.
- 强转符号只针对于最近的操作数有效,往往会使用小括号提升优先级.
- Char类型可以保存Int的常量值,但不能保存Int的变量值,需要强转.
- Byte和Short类型在进行运算时,当做Int类型处理.

```
判断是否能够通过编译,并说明原因
1）var s : Short  = 5 // ok
   s = s-2    //  error  Int -> Short                
2）var b : Byte  = 3    // ok
   b = b + 4              // error Int ->Byte     
   b = (b+4).toByte       // ok ，使用强制转换
3）var c : Char  = 'a'  //ok
   var  i : Int = 5 //ok
   var d : Float = .314F //ok
   var result : Double = c+i+d     //ok Float->Double
4）var b : Byte  = 5 // ok
   var s : Short  = 3 //ok
   var t : Short = s + b // error Int->Short
   var t2 = s + b   // ok, 使用类型推导
```

### 2.13 值类型和String类型的转换
#### 2.13.1 介绍

在程序开发中,我们经常需要将基本数据类型转成String类型,或者将String类型转成基本数据类型.

#### 2.13.2 基本类型转String类型

将基本类型的值+"" 即可.

#### 2.13.3 String类型转基本数据类型

通过基本类型的String的 toXxx方法即可

#### 2.13.4 使用细节

- 在将String 类型转成基本数据类型时,要确保String类型能够转成有效的数据,比如我们可以把"123",转成一个整数,但是不能把"hello"转成一个整数.
- "12.5" 不能转成Int,但可以转成Double

```scala
object String2Basic {

  def main(args: Array[String]): Unit = {

    val d1 = 1.2
    //基本类型转String
    val s1 = d1 + ""
    //String转基本数据类型
    val s2 = "12"
    val num1 = s2.toInt
    val num2 = s2.toByte
    val num3 = s2.toDouble
    val num4 = s2.toLong

    println("ok~")

    val s3="hello"
    println(s3.toInt) //error

    // 在scala不是将小数点后的进行截取,而是会抛出异常
    val s4="12.5"
    println(s4.toDouble) //正确
    println(s4.toInt) //error
  }
}
```

### 2.14 标识符的命名规范
#### 2.14.1 标识符概念

- Scala 对各种变量,方法,函数等命名时使用的字符序列称为标识符.
- 凡是自己可以起名字的地方都叫标识符.

#### 2.14.2 标识符的命名规则

Scala中的标识符声明,基本和Java是一致的,但是细节上会有所变化.
- 首字符为字母,后续字符任意字母和数字,美元符号,可后接下划线_.
- 数字不可以开头.
- 首字符为操作符(比如+ - * / ),后续字符也需跟操作符,至少一个(反编译)
- 操作符(比如+-*/)不能在标识符中间和最后.
- 用反引号`....`包括的任意字符串,即使是关键字(39个)也可以[true].

```scala
object IdenDemo01 {

  def main(args:Array[String]):Unit={

    //首字符为操作符(比如+ - * / )，后续字符也需跟操作符 ,至少一个
    val ++ = "hello,world"
    println(++)

    val -+ = 90
    // val +q = "acb" //error

    //用反引号`....`包括的任意字符串
    val `true` = "hello,scala!"
    println("内容="+`true`)

    var Int = 90.5
    println("Int="+Int)

    //不能使用下划线做标识符
    var _ = "123"
    println(_)
  }

}
```

```text
hello    // ok
hello12 // ok
1hello  // error
h-b   // error
x h   // error
h_4   // ok
_ab   // ok
Int    // ok, 在scala中，Int 不是关键字，而是预定义标识符,可以用，但是不推荐
Float  // ok
_   // 不可以，因为在scala中，_ 有很多其他的作用，因此不能使用
Abc    // ok
+*-   // ok
+a  // error
```

#### 2.14.3 标识符的命名规范

- 尽量采取有意义的包名,简短,有意义.
- 变量名,函数名,方法名采用驼峰法.

#### 2.14.4 关键字

Scala有39个关键字
- package, import, class, object, trait, extends, with, type, forSome
- private, protected, abstract, sealed, final, implicit, lazy, override
- try, catch, finally, throw 
- if, else, match, case, do, while, for, return, yield
- def, val, var 
- this, super
- new
- true, false, null

## 第三章 运算符
### 3.1 运算符介绍

运算符是一种特殊的符号,用以表示数据的运算,赋值和比较等.
- 算术运算符
- 赋值运算符 
- 比较运算符(关系运算符)
- 逻辑运算符
- 位运算符

### 3.2 算术运算符

算术运算符(arithmetic)是对数值类型的变量进行运算的,在Scala程序中使用的非常多.

![算数运算符列表](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWa9r6icVLsSiau33hYxrwArIlUiaKurRDqnxTibCKZp87SK0zmVIc3Yo2XQ/0?wx_fmt=png)

```scala
object Demo01 {
  def main(args: Array[String]): Unit = {

    // /的使用
    var r1 : Int = 10 / 3 // 3
    println("r1=" + r1)
    var r2 : Double = 10 / 3 //3.0
    println("r2=" + r2)
    var r3 : Double = 10.0 / 3 //3.333333333
    println("r3=" + r3 )
    println("r3=" + r3.formatted("%.2f") ) // 3.33

    //% 的使用
    // % 的规则: a%b = a-a/b*b
    println(10 % 3) //1
    println(-10 % 3) //-1
    println(-10 % -3) //-1
    println(10 % -3) //1

    // ++ --
    // scala 没有 ++ --,使用+= -=
    var num1 =10
    num1 += 1
    num1 -= 1
    println(num1)
  }
}
```

```scala
/**
   *假如还有97天放假，问：xx个星期零xx天
   * 1.搞清楚需求(读题)
   * 2.思路分析
   * (1) 变量保存97
   * (2) 使用/7 得到几个星期
   * (3) 使用%7 得到x天
   * 3.代码实现
   * 定义一个变量保存华氏温度，华氏温度转换摄氏温度的公式为：5/9*(华氏温度-100),请求出华氏温度对应的摄氏温度。[测试：232.5]
   */
  def main(args: Array[String]): Unit = {

    val days = 97
    printf("统计结果是 %d个星期零%d天",days/7,days%7)

    val hs = 232.5
    val ss = 5.0/9*(hs-100)
    println("对应的摄氏温度"+ss.formatted("%.2f"))
  }
```

### 3.3 关系运算符(比较运算符)

基本介绍
- 关系运算符的结果都是boolean型,也就是要么是true,要么是false.
- 关系表达式经常用在if结构的条件中或循环结构的条件中.
- 关系运算符的使用和java一样.

![关系运算符列表](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWGRDqv6Mhbia1n5KMdaYgo6icdqEYGc428BibAQUtSw4cAomI2ibrY1iaL7g/0?wx_fmt=png)

细节说明:
- 关系运算符的结果都是Boolean型,也就是要么是true,要么是false.
- 关系运算符组成的表达式,我们称为关系表达式.a > b 
- 比较运算符"=="不能误写成"="
- 使用陷阱:如果两个浮点数进行比较,应当保证数据类型一致.

### 3.4 逻辑运算符

用于连接多个条件(一般来讲就是关系表达式),最终的结果也是一个Boolean值.

![逻辑运算符列表](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWVERCEluY8pRxJdgxb4jNMclMsoDwx4K25vSnica4W7w4jiaiaXk1wmjTQ/0?wx_fmt=png)

### 3.5 赋值运算符

赋值运算符就是将某个运算后的值,赋给指定的变量.

![赋值运算符列表](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbWo9MDD1loI5CNDT29WhAwfdBwh7icPIBLJxdFtms99ibAcD3SvfBbAEhg/0?wx_fmt=png)
![赋值运算符列表2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0edZ7IZ53ic8xWiaHNVlhzbW2el7MN13psAk5B1fwzHGvexrLIgqqtVAczFth8VIwiay1JpXM9l1W8Q/0?wx_fmt=png)

赋值运算符特点
- 运算顺序从右往左
- 赋值运算符的左边 只能是变量,右边可以是变量,表达式,常量值/字面量
- 复合赋值运算符等价于下面的效果,a+=3 等价于a=a+3

### 3.5 运算符优先级

运算符优先级
- 运算符有不同的优先级,所谓优先级就是表达式运算中的运算顺序.
- 只有单目运算符,赋值运算符是从右向左运算的.
- 运算符的优先级和Java一样.

![运算符优先级](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0Ncribg9X2TswbHDJg2CqmTObmQPK13TSL07a5atzSRFzKJYC0YoVWymGGDaSPrSpbcsuRWBqJnbw/0?wx_fmt=png)

小结运算符的优先级
- () []
- 单目运算
- 算术运算符
- 移位运算
- 比较运算符(关系运算符)
- 位运算
- 关系运算符
- 赋值运算

```scala
object Demo01 {

  def main(args: Array[String]): Unit = {
    var num =2
    num <<= 2
    num >>= 1
    println(num)

    //在scala中支持代码块,返回值
    val res = {
      if(num>1) "hello,ok" else 100
    }
    println("res="+res)

    //有两个变量,a和b,要求将其进行交换,但是不允许使用中间变量
    var a = 10
    var b =20
    a=a+b
    b=a-b
    a=a-b
  }
}
```

```scala
object Demo01 {
  def main(args:Array[String]): Unit = {

    /**
     * 案例1: 求两个数中的最大数
     * 案例2: 求三个数中的最大数
     */
    val n1 = 4
    val n2 = 8
    var res = if(n1>n2) n1 else n2
    println("res="+res)

    val n3 = 11
    res = if(res > n3) res else n3
    println("res="+res)

  }

}
```

```scala
object Demo01 {

  def main(args:Array[String]):Unit={

    println("请输入姓名")
    val name = StdIn.readLine()
    println("请输入年龄")
    val age  = StdIn.readInt()
    println("请输入薪水")
    val sal =StdIn.readDouble()
    printf("用户的信息为 name = %s age = %d sal = %.2f",name,age,sal)


//    Cat.sayHi
//    Cat.sayHello

  }
}

//声明了一个对象(伴生对象)
object Cat extends AAA{
  def sayHi:Unit={
    println("小狗汪汪汪....")
  }
}

//AAA是特质,等价于Java中的interface+abstract class
trait AAA{
  def sayHello():Unit={
    println("AAA sayHello")
  }
}
```

