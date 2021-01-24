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

```
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

```text
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

## 第四章 程序流程控制

### 4.1 程序的流程控制说明

在程序中,程序运行的流程控制决定程序是如何执行的,是我们必须掌握的,主要有三大流程控制语句.
- 顺序控制
- 分支控制
- 循环控制

注意:Scala语言中控制结构和Java语言中的控制结构基本相同,在不考虑特殊应用场景的情况下,代码书写方式以及理解方式都没有台大区别.

### 4.2 顺序控制的说明

顺序控制介绍
- 顺序从小到下逐行地执行,中间没有任何判断和跳转.

顺序控制举例和注意事项
- Scala中定义变量时采用合法的前向引用.

```text
//Scala中定义变量时采用合法的前向引用
def main(args:Array[String]):Unit={
  var num1=12
  var num2=num1+2
}

//错误形式
def main(args:Array[String]):Unit={
  var num2=num1+2
  var num1=12
}
```

### 4.3 分支控制if-else
#### 4.3.1 分支控制if-else介绍

让程序有选择的执行,分支控制有三种:
- 单分支
- 双分支
- 多分支

#### 4.3.2 单分支的使用

基本语法

```text
if(条件表达式){
    执行代码块
}
说明:当条件表达式为true时,就会执行{}的代码
```

案例说明

```text
编写一个程序,可以输入人的年龄,如果该同志的年龄大于18岁,则输出"age>18"
val age=20
if(age>20){
    println("age>18")
}
```

单分支的案例说明
```text
object Demo01 {

  def main(args:Array[String]):Unit={
    println("输入年龄")
    val age=StdIn.readInt()
    if(age>18){
      println("age>18")
    }
  }

  //小技巧,如何查看某个包下包含的内容
  //1.比如我们想看scala.io包有什么内容
  //2.将光标放在io上即可,输入ctrl+b
  //3.将光标放在StdIn上即可,输入ctrl+b,看的是StdIn源码
}
```

#### 4.3.3 双分支

基本语法
```text
if(条件表达式){
    执行代码块1
}else{
    执行代码块2
}

说明:当条件表达式1,否则执行代码块2
```

案例演示
```text
object Demo02 {

  def main(args:Array[String]):Unit={
    val age=6
    if(age>18){
      println("age>18")
    }else{
      println("age<=18")
    }
  }
}
```

#### 4.3.4 单分支和双分支课后题

- com.weiliai.chapter04.ifelse.Exercise01


#### 4.3.5 多分支

基本方法
```text
if(条件表达式){
    执行代码块1
}else if(条件表达式2){
    执行代码块2
}else{
    执行代码块n
}

注意:只能有一个执行入口
```

案例演示

- com.weiliai.chapter04.ifelse.Exercise02

#### 4.3.6 分支控制if-else注意事项

- 如果大括号{}内的逻辑代码只有一行,大括号可以省略,这点和Java的规定一样.
- Scala中任意表达式都是有返回值的,也就是以为if-else表达式其实是有返回结果的,具体返回结果的值取决于满足条件的代码块体的最后一行内容.
- Scala中是没有三元运算符的,因此可以简写

基础案例

- com.weiliai.chapter04.ifelse.Exercise03


### 4.4 嵌套分支
#### 4.4.1 基本介绍

在一个分支结构中又完整的嵌套了另一个完整的分支结构,里面的分支的结构称为内层分支,外面的分支结构称为外层分支,嵌套分支不要超过3层

#### 4.4.2 基本语法

```text
if(){
    if(){
        
    }else{
    
    }
}
```

#### 4.4.3 应用案例

- com.weiliai.chapter04.ifelse.Exercise04
- com.weiliai.chapter04.ifelse.Exercise05

### 4.5 switch分支结构

- scala中没有switch,而是使用模式匹配来处理.
- 模式匹配涉及到的知识点比较综合,后续处理,match-case

### 4.6 for循环控制
#### 4.6.1 基本介绍

Scala也为for循环这一常见的控制结构提供了非常多的特性,这些for循环的特性被称为for推倒式(for comprehension)或for表达式(for expression).

#### 4.6.2 范围数据循环方式1

基本案例

```text
for(i-> 1 to 3){
    print(i+"")
}
println()

说明
- i表示循环的变量,<- 规定 to 规划
- i将会从1-3循环,前后闭合

com.weiliai.chapter04.myfor.ForDemo01
```
#### 4.6.3 范围数据循环方式2

基本案例

```text
for(i <- 1 unit 3){
    print(i+"")
}
println()

说明
- 前闭后开的范围和Java的arr.length()类似for(int i=0;i<arr.length;i++){}

com.weiliai.chapter04.myfor.ForUnitDemo02
```

#### 4.6.4 循环守卫

基本案例

```text
for(i <- 1 to 3 if i!=2){
   print(i+"") 
}
println()

说明
- 循环守卫,即循环保护式(条件判断式,守卫).
- 保护式为true则进入循环体内不,为false跳过,类似continue,等价于

for(i <- 1 to 3){
    if(i!=2){
        print(i+"")
    }
}
println()

com.weiliai.chapter04.myfor.ForGuard
```

#### 4.6.5 引入变量

基本案例
```text
for(i <- 1 to 3;j=4-i){
    print(j+"")
}

说明
- 没有关键字,所以范围后一定加;来隔断逻辑,代码等价于

for(i <- 1 to 3){
    val j=4-i
    print(4-i)
}

com.weiliai.chapter04.myfor.ForVar
```
#### 4.6.6 嵌套循环

基本案例
```text
for(i <- 1 to 3;j<-1 to 3){
  print("i="+i+"j="+j)
}

说明
- 上面代码等价于
for(i <- 1 to 3){
    for(j <- 1 to 3){
        print("i="+i+"j="+j)         
    }
}
```

#### 4.6.7 循环返回值

基本案例
```text
val res=for(i <- 1 to 10) yield i
println(res)

说明
- 将遍历过程中处理的结果返回到一个新的Vector集合中,使用yield关键字
```
#### 4.6.8 使用花括号{}代替小括号()

基本案例
```text
for(i <- 1 to 3;j=i*2){
    println("i="+i+"j="+j)
}
改写
for{
    i <- 1 to 3
    j=i*2}{
    println("i="+i+"j="+j)
}

说明
- {}和()对于for表达式来说都可以
- for推导式有一个不成文的约定:当for推倒式仅包含单一表达式时使用圆括号,当其包含多个表达式时使用大括号
- 当使用{}来换行写表达式时,分号就不用写了
```
#### 4.6.9 注意事项和细节说明

- scala的for循环形式和Java时较大差异,这点请同学们注意,但是基本的原理还是一样的.
- scala的for循环的步长如何控制![for(i <- Range(1,3,2)]
- 思考:如何使用循环守卫控制步长?

基本案例

- com.weiliai.chapter04.myfor.StepFor


#### 4.6.10 for循环练习题

基本案例

- com.weiliai.chapter04.myfor.ForExercise01


### 4.7 while循环控制
#### 4.7.1 基本语法

循环变量初始化
```text
while(循环条件){
    循环体(语句)
    循环变量迭代
}
```

#### 4.7.2 while循环应用实例

基本案例

- com.weiliai.chapter04.mywhile.WhileDemo01


#### 4.7.3 注意事项和细节说明

- 循环条件是返回一个布尔值的表达式
- while循环是先判断在执行语句
- 与IF语句不同,while语句本身没有值,即整个while语句的结果是Unit类型的()
- 因为while没有返回值,所以当要用该语句来计算并返回结果时,就不可避免使用变量,而变量需要声明在while循环的外部,那么等同于循环内部对外部的变量造成了影响,所以不推荐使用,而是推荐是for循环.

### 4.8 do..while循环控制
#### 4.8.1 基本用法

```text
do{
    循环体(语句)
    循环变量迭代
}while(循环条件)
```

#### 4.8.2 do..while循环应用实例

基本案例

- com.weiliai.chapter04.mydowhile.Demo01


#### 4.8.3 注意事项和细节说明

- 循环条件是返回一个布尔值的表达式
- do..while循环是先执行,再判断
- 和while一样,因为do..while中没有返回值,所以当要用该语句来计算并返回结果时,就不可避免的使用变量,而变量的声明需要在do..while循环外部,那么等同于循环的内部对外部的变量造成了影响,不推荐使用.

### 4.9 多重循环控制
#### 4.9.1 介绍

- 将一个循环放在另一个循环体内,就形成了嵌套循环,其中for,while,do..while均可作为外层循环和内层循环.[建议不超过3层]
- 实质上,嵌套循环就是把内层循环当成外层循环的循环体.当只有内层循环条件为false,才会完全跳出内层循环,才会外层当次循环,开始下一次循环.
- 设外层循环次数为m次,内层为n次,则内层循环体实际上需要执行m*n=mn次.

#### 4.9.2 应用实例

- com.weiliai.chapter04.mutlfor.Exercise01
- com.weiliai.chapter04.mutlfor.Exercise02

### 4.10 while循环的中断
#### 4.10.1 基本说明

Scala内置控制结构特地去掉了break和continue,是为了更好的适应函数化编程,推荐使用函数式风格解决break和continue的功能,而不是一个关键字.

#### 4.10.2 break的应用实例

- com.weiliai.chapter04.mybreak.WhileBreak

#### 4.10.3 如何实现continue的效果

Scala内置控制结构特地也去掉了continue,是为了更好的适应函数化编程,可以使用if-else或循环守卫实现continue效果.

- com.weiliai.chapter04.homework.Homework01
- com.weiliai.chapter04.mycontinue.ContinueDemo


## 第五章 函数式编程的基础
### 5.1 函数式编程内容

函数式编程的基础
- 函数定义/声明
- 函数运行机制
- 递归/难点[最短路径,邮差问题,迷宫问题,回溯]
- 过程
- 惰性函数和异常

函数式编程高级
- 值函数(函数字面量)
- 高阶函数
- 闭包
- 应用函数
- 柯里化函数,抽象控制...

#### 5.1.2 函数式编程授课顺序说明

- 在scala中,函数式编程和面向对象编程融合在一起,学习函数式编程需要OOP知识,同样学习OOP需要函数式编程的基础.
- 授课顺序: 函数式编程基础->面向对象编程->函数式编程高级

### 5.2 函数式编程介绍
#### 5.2.1 几个概念的说明

在学习scala中将方法,函数,函数式编程和面向对象编程明确一下:
- 在scala中,方法和函数几乎可以等同(比如他们的定义,使用,运行机制都一样),只是函数的使用方式更加灵活多样.
- 函数式编程从编程方式(范式)的角度来谈的,可以这样理解:函数式编程把函数当作一等公民,充分利用函数,支持函数的多种使用方式.
    - 在scala中,函数是一等公民,像变量一样,既可以做函数的参数使用,也可以将函数赋值给一个变量
    - 函数的创建不依赖与类或者对象,而在Java中,函数的创建需要依赖于类,抽象类或者接口.
- 面向对象编程是以对象为基础的编程方式
- 在scala中函数式编程和面向对象编程融合在了一起.

#### 5.2.2 在学习scala中将方法,函数,函数式编程和面向对象编程关系分析图

![关系图](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14YPbSrQSdYjI9sTuONnyUeIc6CbrN5uxylFYfAY4iaDk1brDq8RMaj1nNrYDWD1lUgiaHTQgHvCaQ/0?wx_fmt=png)

#### 5.2.3 函数式编程的小结

- 函数式编程是一种编程范式(programming paradigm)
- 它属于结构化编程的一种,主要思想是把运算过程尽量写成一系列嵌套的函数调用.
- 函数式编程汇中,将函数也当作数据类型,因此可以接受函数当作输入(参数)和输出(返回值)
- 函数式编程中,最重要的就是函数.

### 5.3 为什么需要函数

![为什么要用函数](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14YPbSrQSdYjI9sTuONnyU9tmPPxeYibibz3w8KlDULFnzKDMz5zLibibRXuRTBXrSR2gMx2aoS203uw/0?wx_fmt=png)

### 5.4 函数的定义
#### 5.4.1 基本语法

```text
def 函数名([参数名:参数类型],...)[[:返回值类型]=]{
    语句
    return 返回值
}
```
- 函数声明关键字为def(definition)
- [参数名:参数类型],...: 表示函数的输入(就是参数列表),可以没有.如果有,多个参数使用逗号分割.
- 函数中的语句: 表示为了实现某一功能代码块
- 函数可以有返回值,也可以没有
    - 返回值形式1: : 返回值类型=
    - 返回值形式2: = 表示返回值类型不确定,使用类型推导完成
    - 返回值形式3:   表示没有返回值,return不生效
- 如果没有return,默认以执行到最后一行的结果作为返回值

#### 5.4.2 快速入门案例

- com.weiliai.chapter05.fun.FunDemo01


### 5.5 函数-调用机制
#### 5.5.1 函数-调用过程

为了让大家更好的理解函数调用机制,看1个案例,并画出示意图,这个很重要,比如getSum计算两个数的和,并返回结果

![调用过程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14YPbSrQSdYjI9sTuONnyUYAlatXO5VmAQSocvfnoGOjPKRdspLNibQVwoGV4f98EPfky0y8g94og/0?wx_fmt=png)

#### 5.5.2 函数递归调用的重要的规则和小结

函数递归需要遵守的重要原则(总结)
- 程序执行一个函数时,就创建要给新的受保护的独立空间(新函数栈).
- 函数的局部变量是独立的,不会相互影响
- 递归必须向退出递归的条件逼近,否则就是无限递归
- 当一个函数执行完毕或遇到return,就会返回,遵守谁调用,就把结果返给谁.

#### 5.5.3 使用scala递归的应用案例

- com.weiliai.chapter05.recursive.Exercise01


### 5.6 函数注意事项和细节讨论

- 函数的形参列表可以是多个,如果函数没有形参,调用时可不带()
- 形参列表和返回值列表的数据类型可以是值类型和引用类型
- Scala中函数可以根据函数体最后一行代码自行推断函数返回值类型,这种情况可以省略return
- 因为Scala可以自行推断,所以省略return关键字的场合,返回值类型也可以省略
- 如果函数明确使用return关键字,那么函数返回就不能使用自行推断了,这时要明确写成 : 返回值类型= ,如果都不写,即使有return 返回值为()
- 如果函数明确声明无返回值(声明Unit),那么函数体中即使使用return关键字也会有返回值
- 如果明确函数无返回值或不确定返回值类型,那么返回值类型可以省略或声明为Any
- Scala语法中任何的语法结构都可以嵌套其他语法结构(灵活),即:函数中可以可以在声明/定义函数,类中可以声明类,方法中可以再声明/定义方法
- Scala函数的形参,在声明参数时,直接赋值初始值(默认值),这时调用函数时,如果没有指定实参,则使用默认值,如果指定了实参,则覆盖默认值
- 如果函数存在多个参数,每一个参数都可以设定默认值,那么这时,传递参数到底是覆盖默认值还是赋值给没有默认值的参数,就不确定了(默认声明顺序,从左到右).这时可以采用带名参数
- 递归函数未执行之前是无法推断出来结果类型,在使用时必须明确的返回值类型
- Scala函数支持可变参数

```text
com.weiliai.chapter05.fundetails
```

### 5.7 过程

#### 5.7.1 基本概念

将函数的返回类型为Unit的函数称之为过程(procedure),如果明确函数没有返回值,那么等号可以省略.

代码实例

```text
//f10没有返回值,可以使用Unit来说明
//这时,这个函数我们也叫过程(procedure)
def f10(name:String): Unit={
    println(name+"hello")
}
```

#### 5.7.2 注意事项

注意事项和细节说明
- 注意区分:如果函数声明时没有返回值类型,但是有=号,可以进行类型推断最后一行代码,这时这个函数实际是有返回值的,该函数不是过程.
- 开发工具的自动代码补全功能,虽然会自动加上Unit,但是考虑到Scala语言的简单灵活,最后不加

### 5.8 惰性函数
#### 5.8.1 应用场景

惰性计算(尽可能延迟表达式求值)是许多函数式编程语言的特性.
- 惰性集合在需要时提供其元素,无需预先计算它们
- 将耗时的计算推迟到绝对需要的时候
- 可以创建无限个集合,只要它们继续收到请求,就会继续提供元素

#### 5.8.2 惰性函数介绍

当函数返回值声明为lazy时,函数的执行将被推迟,直到我们首次对此取值,该函数才会执行,这种函数我们称之为惰性函数.在Java的某些框架代码汇总称之为懒加载(延迟加载).

代码实例

- com.weiliai.chapter05.mylazy.LazyDemo01


#### 5.8.3 注意事项和细节

- lazy不能修饰var类型的变量
- 调用函数时,加了lazy,会导致函数的执行被推迟
- 声明变量,加了lazy,那么变量分配也会推迟(命令行定义变量即可,例 lazy val i = 10)

### 5.9 异常
#### 5.9.1 介绍

- Scala提供了try和catch块来处理异常.try块包含可能出错的代码.catch块用于处理try块中发生的异常.
- 语法处理上与Java类型,但又不尽相同

#### 5.9.2 Java异常处理回顾

代码实例

- com.weiliai.chapter05.myexception.JavaExceptionDemo01

#### 5.9.3 Java异常处理的注意点

- Java语言按照try-catch-catch..finally的方式来处理异常
- 不管有没有异常捕获,都会执行finally,因此通常在finally代码块中释放资源
- 可以有多个catch,分别捕获对应的异常,需要把范围小的异常类写在前面,范围大的类在后面,否则编译报错.

#### 5.9.4 Scala异常处理举例


- com.weiliai.chapter05.myexception.ScalaExceptionDemo
- com.weiliai.chapter05.myexception.ScalaExceptionDemo02


#### 5.9.5 Scala异常处理小结

- 可疑代码封装在try块中.在try块之后使用catch处理程序捕获异常,如果发生任何异常,catch程序处理它,程序不会异常终止.
- Scala和Java异常工作机制一样,但是Scala中没有"checked(编译期)"异常,即scala没有编译异常概念,都是运行的时候捕获处理的.
- 用throw关键字,抛出一个异常对象,所有异常都是Throwable的子类型,throw表达式是由类型的,就是Nothing,因为Nothing是所有类型的子类型,所以throw表达式可以用在需要类型的地方
- 在scala里,借用了模式匹配的思想来做异常匹配,因此catch代码里,是一系列case子句来匹配异常,当匹配上后=>有多条语句可以换行写,类型Java的switch case x:代码块
- 异常捕捉的机制和其他语言中的一样,如果有异常发生,catch子句是按次捕获的,因此在catch子句中,越具体越靠前,越普遍越靠后,把大异常放到小异常前面不会报错,但是编程风格不好
- finally子句用于执行不管是正常处理还是有异常发生时都需要执行的步骤,一般用于对象的清理工作.
- scala提供了throws关键字来声明异常,可以使用方法定义声明异常,它向调用者函数提供了此方法可能引发的异常信息,有助于调用函数处理避免程序异常终止,在scala中,可以使用throws注释来声明异常

## 第六章 面向对象编程(基础部分)
### 6.1 类与对象
#### 6.1.1 Scala语言是面向对象的

- Java是面向对象的编程语言,由于历史原因,Java中还存在着非面向对象的内容:基本类型,null,静态方法等
- Scala语言来自于Java,所以天生就是面向对象的语言,而且scala是纯粹的面向对象的语言,即在scala中,一切皆为对象
- 在面向对象的学习过程中可以对比Java学习

#### 6.1.2 快速入门案例

- com.weiliai.chapter06.oop.CatDemo

#### 6.1.3 类和对象的区别和联系

- 类是抽象的,概念的,代表一类事物,比如人类,猫类等
- 对象是具体的,实际的,代表一个具体事物
- 类是对象的模版,对象是类的一个个体,对应一个实例
- Scala中类和对象的区别和联系与Java中一样

#### 6.1.4 如何定义类

基本语法
```text
[修饰符] class 类名{
    类体
}
```

注意事项
- scala语法中,类并不声明为public,所有这些类都具有共有可见性(即默认是public)
- 一个Scala源文件可以包含多个类,而且默认都是public

#### 6.1.5 属性

基本介绍
- 属性是类的一个组成部分,一般是值数据类型,也可是引用类型.

#### 6.1.6 属性/成员变量

注意事项和细节
- 属性的定义语法同变量,示例:[访问修饰符] var 属性名称[: 类型]=属性值
- 属性的定义类型可以任意类型,包含值类型或引用类型
- Scala中声明一个属性,必须显示的初始化,然后根据初始化数据的类型自动推断,属性类型可以省略
- 如果赋值为null,则一定要加类型,因为不加类型,那么该属性的类型就是Null类型.
- 如果在定义属性时,暂时不赋值,也可以使用符号_(下划线),让系统分配默认值

#### 6.1.7 属性的高级部分

属性的高级部分和构造器(构造方法/函数)相关,我们把属性高级部分放到构造器哪里讲解

#### 6.1.8 如何创建对象

基本语法

```text
val|var 对象名[:类型] = new 类型()
```

说明
- 如果我们不希望改变对象的引用(即:内存地址),应该声明为val性质的,否则声明为var,推荐使用val,因为一般来说,我们只改变对象属性值,而不是改变对象的引用
- scala在声明变量时,可以根据创建对象类型自动推断,所以类型声明可以省略,但当类型和后面new对象类型有继承关系即多态时,就必须写

#### 6.1.9 类和对象的内存分配机制

- com.weiliai.chapter06.oop.MemState

### 6.2 方法
#### 6.2.1 基本说明

Scala中的方法其实就是函数,声明规则请参考函数式编程中的函数声明.

#### 6.2.2 基本语法

```text
def 方法名(参数列表)[: 返回值类型]={
    方法体
}
```
#### 6.2.3 方法案例演示
- com.weiliai.chapter06.method.MethodDemo01
- com.weiliai.chapter06.method.MethodDemo02

### 6.3 类与对象应用实例

- com.weiliai.chapter06.dogcase.DogCaseTest

### 6.4 构造器
#### 6.4.1 一个需求

我们在创建一个对象时,一般先创建好对象在赋值,如果在创建时候就直接指定属性呢,就需要用到构造器/构造方法

#### 6.4.2 回顾-Java构造器基本语法

```text
[修饰符] 方法名(参数列表){
    构造方法体
}
```

#### 6.4.3 回顾-Java构造器的特点

- 一个类定义多个不同的构造方法,构造方法重载
- 如果程序员没有定义构造方法,系统会自动给类生成一个默认的无参构造方法即默认构造器
- 一旦构造了自己的构造方法(构造器),默认的构造方法就被覆盖了,就不能再使用默认的无参构造方法,除非显示的定义

#### 6.4.4 Java构造器的案例

```Java
public class Person{
    private String name;
    private Integer age;
    public Person(){
    }
    public Persion(String name,Integer age){
        this.age=age;
        this.name=name;
    }
}   
```

#### 6.4.5 Scala构造器的介绍

```text
class 类名(形参列表){ //主构造器
    def this(形参列表){ //辅助构造器

    }
    def this(形参列表){ //辅助构造器可以有多个

    }
    
}

//辅助构造器,函数的名称this,可以有多个,编译器通过不同的参数来区分
```

#### 6.4.6 Scala构造器的快速入门

- com.weiliai.chapter06.constructor.ConDemo01
- com.weiliai.chapter06.constructor.ConDemo03

#### 6.4.7 Scala构造器注意事项和细节

- Scala构造器的作用是完成对新对象的初始化,构造器没有返回值
- 主构造器的声明是直接放置于类名之后
- 主构造器会执行类定义中的所有语句,这里可以体会到Scala的函数式编程和面向对象编程融合在一起,即:构造器也是方法(函数),传递参数和使用方法和前面的函数部分内容没有区别.
- 如果主构造器无参数,小括号可省略,构建对象时调用的构造方法的小括号也可以省略
- 辅助构造器名称为this(这个和Java是不一样的),多个辅助构造器通过不同参数列表进行区分,在底层就是父构造器重载
- 如果像让主构造器私有,可以在()前加private,这样用户就只能通过辅助构造器来构造对象了

### 6.5 属性高级
#### 6.5.1 构造器参数






