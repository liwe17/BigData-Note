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

1. 需求

2. 分析

3. 环境准备

