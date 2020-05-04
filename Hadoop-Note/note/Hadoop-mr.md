# Hadoop之MapReduce
## 第一章 MapReduce概述
### 1.1 MapReduce定义
> MapReduce的定义
> - MapReduce是一个分布式运算程序的编程框架,是用户开发"基于Hadoop的数据分析应用"的核心框架.
> - MapReduce核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的分布式运算程序,并发运行在一个Hadoop集群上.

### 1.2 MapReduce优缺点
#### 1.2.1 优点

![优点1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicbMnud5INwV8ruq55ywhgj2ZcaQGkef3qdPBlz6SFemiauQ2M0BDaVfw/0?wx_fmt=png)
![优点2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicUib9Fib9Ydb6BJ3e3Zx1wwswDzHWsT346uUd42zdRYmichzzP26avFrkA/0?wx_fmt=png)

#### 1.2.2 缺点

![缺点](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBics3uciciaH6p9KbsibnVsE2VzYWn5MPOTYzWVFB7iasffGtPNekMXPf4fjQ/0?wx_fmt=png)

### 1.3 MapReduce核心思想

![思想](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicf6PaJhwXHAGLhnecicBkyGB8VXox2qbiaDZz2ctNcNpUGibNUACDT6wDA/0?wx_fmt=png)

> - 分布式的运算程序往往需要分成至少2个阶段.
> - 第一个阶段的MapTask并发实例,完全并行运行,互不相干.
> - 第二个阶段的ReduceTask并发实例互不相干,但是他们的数据依赖与上一个阶段的所有MapTask并发实例的输出.
> - MapReduce编程模型只能包含一个Map阶段和一个Reduce阶段,如果用户的业务逻辑非常复杂,那就只能多个MapReduce程序,串行运行.

> 总结: 分析WordCount数据流走向深入理解MapReduce核心思想.

### 1.4 MapReduce进程

> 一个完整的MapReduce程序在分布式运行时有三类实例进程:
> - MrAppMaster:负责整个程序的过程调度及状态协调.
> - MapTask:负责Map阶段的整个数据处理流程.
> - ReduceTask:负责Reduce阶段的整个数据处理流程.

### 1.5 官方WordCount源码

> 采用反编译工具反编译源码,发现WordCount案例有Map类,Reduce类和驱动类,且数据的类型都是Hadoop自身封窗的序列化类型.

### 1.6 常用数据序列化类型

<table>
    <tr>
        <th>Java类型</th>
        <th>Hadoop Writable类型</th>
    </tr>
    <tr>
        <th>boolean</th>
        <th>BooleanWritable</th>
    </tr>
    <tr>
        <th>byte</th>
        <th>ByteWritable</th>
    </tr>
    <tr>
        <th>int</th>
        <th>IntWritable</th>
    </tr>
    <tr>
        <th>float</th>
        <th>FloatWritable</th>
    </tr>
    <tr>
        <th>long</th>
        <th>LongWritable</th>
    </tr>
    <tr>
        <th>double</th>
        <th>DoubleWritable</th>
    </tr>
    <tr>
        <th>String</th>
        <th>Text</th>
    </tr>
    <tr>
        <th>Map</th>
        <th>MapWritable</th>
    </tr>
    <tr>
        <th>array</th>
        <th>ArrayWritable</th>
    </tr>
</table>

### 1.7 MapReduce编程规范

> 用户编写的程序分为三个部分:Mapper,Reducer和Driver.

1. Mapper阶段
> - 用户自定义的Mapper要继承自己的父类
> - Mapper的输入数据是KV对的形式(KV的类型可自定义)
> - Mapper中的业务逻辑写在map()方法中
> - Mapper的输出数据是KV对的形式(KV的类型可自定义)
> - map()方法(MapTask进程)对每一个<K,V>调用一次

2. Reduce阶段
> - 用户自定义的Reducer要继承自己的父类
> - Reducer的输入数据类型对应Mapper的数据类型,也是KV
> - Reducer的业务逻辑编写在reduce()方法中
> - ReduceTask进程对每一组相同的K的<k,v>组调用一次reduce()方法

3. Driver阶段
> - 相当于YARN集群的客户端,用于提交我们整个程序到YARN集群,提交的是封装了MapReduce程序相关运行参数的job对象

### 1.8 WordCount案例

1. 需求:在给定的文本文件中统计输出每一个单词出现的总次数

> - 输入数据

```text
atguigu atguigu
ss ss
cls cls
jiao
banzhang
xue
hadoop
... 详见********hello.txt
```
> - 输入数据

```text
atguigu	2
banzhang	1
cls	2
hadoop	1
jiao	1
ss	2
xue	1
```

2. 需求分析

> 按照MapReduce编程规范，分别编写Mapper，Reducer，Driver

![分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBiccNG5ciaykpMpSmn9ibl1fglpUvq7icpLG7PYv7iaqVNMdVsD8L2DRtkFEw/0?wx_fmt=png)


3. 环境准备

> - 创建maven工程,导入依赖
> - 编写程序,Mapper类,Reducer类,Driver驱动类
```text
Hadoop-Demo项目中com.weiliai.mr.wordcount包
```
> - 本地测试,win8电脑和win10家庭版操作系统可能有问题,需要重新编译源码或者更改操作系统
> - 集群测试

```shell script
[root@hadoop102 software]# hadoop jar wc.jar com.weiliai.mr.wordcount.WordCountDriver /user/atguigu/input /user/atguigu/output
[root@hadoop102 software]# hadoop fs -ls /user/atguigu/output
Found 2 items
-rw-r--r--   3 root supergroup          0 2020-05-04 21:49 /user/atguigu/output/_SUCCESS
-rw-r--r--   3 root supergroup         86 2020-05-04 21:49 /user/atguigu/output/part-r-00000
[root@hadoop102 software]# hadoop fs -cat /user/atguigu/output/part-r-00000
        1
aiguigu 1
atguigu 3
banzhang        1
cls     2
hadoop  3
jiao    1
mapreduce       1
ss      2
xue     1
yarn    1
[root@hadoop102 software]# 
```

## 第二章 Hadoop序列化
### 2.1 序列化概述
#### 2.1.1 什么是序列化

> - 序列化就是把内存中的对象,转化成字节序列(或其他数据传输协议)以便于存储到磁盘(持久化)和网络传输.
> - 反序列化就是接收到字节序列(或其他数据传输协议)或者是磁盘的持久化数据,转换成内存中的对象.

#### 2.1.2 为什么要序列化

> - 一般来说,"活的"对象只生存在内存里,关机断电就没有了,只能由本地的进程使用,不能被发送到网络上的另外一台计算机.
> - 序列化可以存储"活的"对象,可以将"活的"对象发送到远程计算机.

#### 2.1.3 为什么不用Java的序列化

> Java的序列化是一个重量级序列化框架(Serializable),一个对象被序列化后,会附带很多额外的信息(各种校验信息,Header,继承体系等),不便于在网络中高效传输,所以Hadoop自己开发了一套序列化机制(Writable).

> Hadoop序列化特点
> - 紧凑:高效实用存储空间
> - 快速:读写数据的额外开销小
> - 可扩展:随着通信协议的升级而可升级
> - 互操作:支持多语言的交互

### 2.2 自定义bean对象实现序列化接口(Writable)

> 企业开发中往往常用的基本序列化类型不能满足所有需求,比如在Hadoop框架内部传递一个bean对象,那么该对象就需要实现序列化接口.

> 实现Bean对象序列化步骤
> - 必须实现Writable接口 
> - 反序列化时,需要反射调用空参构造函数,所以必须有空参构造
> - 重写序列化方法
> - 重写反序列化方法
> - 注意反序列化的顺序和序列化的顺序完全一致
> - 要想把结果显示在文件中,需要重写toString(),可用"\t"分开,方便后续用
> - 如果需要将自定义的bean放在key中传输,则还需要实现Comparable接口,因为MapReduce框中的Shuffle过程要求对key必须能排序

### 2.3 序列化案例
1. 需求:统计每一个手机号耗费的总上行流量,下行流量,总流量

> - 输入数据

```text
1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
2	13846544121	192.196.100.2			264	0	200
...详见phone_data.txt
```

> - 输入数据格式

```text
id	手机号码		网络ip			上行流量  下行流量     网络状态码
7 	13560436666	120.196.100.99		1116		 954			200
```

> - 输出数据格式

```text
手机号码		    上行流量        下行流量		总流量
13560436666 		1116		      954 			2070
```

2. 需求分析

![分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicv8D56T8Qx0jwVcmGL50PepBfibdiah0iazKU6zOWqT7iaS2crkJeMkR2IQ/0?wx_fmt=png)

3. 编写程序,Mapper类,Reducer类,Driver驱动类

```text
   Hadoop-Demo项目中com.weiliai.mr.wordcount包
```

4. 执行程序
```shell script
[root@hadoop101 hadoop-2.7.2]# hadoop fs -rm -r  /user/atguigu/input/*
[root@hadoop101 hadoop-2.7.2]# hadoop fs -rm -r  /user/atguigu/output
[root@hadoop101 hadoop-2.7.2]# hadoop fs -put wcinput/phone_data.txt /user/atguigu/input/
[root@hadoop101 hadoop-2.7.2]# hadoop fs -ls /user/atguigu/input/
Found 1 items
-rw-r--r--   3 root supergroup       1157 2020-05-04 23:34 /user/atguigu/input/phone_data.txt
[root@hadoop101 hadoop-2.7.2]#
[root@hadoop102 software]# hadoop jar fc.jar com.weiliai.mr.flowsum.FlowCountDriver /user/atguigu/input /user/atguigu/output
[root@hadoop102 software]# hadoop fs -ls /user/atguigu/output
Found 2 items
-rw-r--r--   3 root supergroup          0 2020-05-04 23:36 /user/atguigu/output/_SUCCESS
-rw-r--r--   3 root supergroup        550 2020-05-04 23:36 /user/atguigu/output/part-r-00000
[root@hadoop102 software]# hadoop fs -cat /user/atguigu/output/part-r-00000
13470253144     180     180     360
13509468723     7335    110349  117684
13560439638     918     4938    5856
13568436656     3597    25635   29232
13590439668     1116    954     2070
13630577991     6960    690     7650
13682846555     1938    2910    4848
13729199489     240     0       240
13736230513     2481    24681   27162
13768778790     120     120     240
13846544121     264     0       264
13956435636     132     1512    1644
13966251146     240     0       240
13975057813     11058   48243   59301
13992314666     3008    3720    6728
15043685818     3659    3538    7197
15910133277     3156    2936    6092
15959002129     1938    180     2118
18271575951     1527    2106    3633
18390173782     9531    2412    11943
84188413        4116    1432    5548
[root@hadoop102 software]#
```



