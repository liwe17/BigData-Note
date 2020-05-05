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

## 第三章 MapReduce框架原理
### 3.1 InputFormat数据输入
#### 3.1.1 切片与MapTask并行度决定机制
1. 问题的引出
> MapTask的并行度决定Map阶段的任务处理并发度,进而影响到整个Job的处理速度.


2. MapTask并行度决定机制

![数据切片与MapTask并行度决定机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dYwfLFWRN0OUYKR2aPu55pIribRA6obUiar13RibdPicVp5KMBt2zyfN6vw/0?wx_fmt=png)

> 思考:1G的数据,启动8个MapTask,可以提高集群的并发处理能力.那么1K的数据,也启动8个MapTask,会提高集群性能吗?MapTask并行任务是否越多越好呢?哪些因素影响了MapTask并行度?　

> - 数据块:Block是HDFS物理上把数据分成一块一块.
> - 数据切片:在逻辑上对输入进行分片,并不会在磁盘上将其切成片进行存储.

#### 3.1.2 Job提交流程源码和切片源码详解
1. 提交流程源码详解

![提交流程源码分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dib2o15zpv807Pic2cWTvqPHJUftviaX9ZwBTV2zibDGicK4HxeDEIBLP8SQ/0?wx_fmt=png)

```text
//0. 方法入口
org.apache.hadoop.mapreduce.Job.waitForCompletion(boolean verbose);         
org.apache.hadoop.mapreduce.Job.submit();
//1. 建立连接
org.apache.hadoop.mapreduce.Job.connect();
   //1.1 创建提交job的代理  
   new Cluster(getConfiguration());
   //1.2 判断本地yarn还是远程
       initialize(jobTrackAddr, conf);

//2. 提交job
org.apache.hadoop.mapreduce.JobSubmitter.submitJobInternal(Job.this, cluster)
  //2.1 创建给集群提交数据的stag路径
  Path jobStagingArea = JobSubmissionFiles.getStagingDir(cluster, conf);
  //2.2 获取jobId,并创建job路径
  JobID jobId = submitClient.getNewJobID();
  //2.3 拷贝jar包到集群
  copyAndConfigureFiles(job, submitJobDir);
  //2.4 计算切片,生成切片规划文件
  int maps = writeSplits(job, submitJobDir);
                       input.getSplits(job);
  //2.5 向stag路径写入xml配置文件
  writeConf(conf, submitJobFile);
  //2.6 提交job,返回提交状态
  status = submitClient.submitJob(jobId, submitJobDir.toString(), job.getCredentials());
```

2. FileInputFormat切片源码解析(input.getSplits(job))

![切片源码解析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dU7ys3pAYe2IPaRN0ISQ9RewgH9bIBAy4LqyzsCdu9u7K11nVsJbeaA/0?wx_fmt=png)

#### 3.1.3 FileInputFormat切片机制

![切片机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dG43TVxRdqesibkdpaUjuvz2MolY24FliaR0gqcIIhLiclRQLeKj067XmA/0?wx_fmt=png)
![参数配置](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dLkS6iaHQqHd3ico9fvHuSXiagicDqhkWbeSTibojXngOia0bnicP9Dm0ibsLag/0?wx_fmt=png)

#### 3.1.4 CombineTextInputFormat切片机制
> 框架默认的TextInputFormat切片机制是对任务按文件规划切片,不管文件多小,都会是单独的一个切片,都会交给一个MapTask,这样如果有大量小文件,就会产生大量MapTask,处理效率极其低下.

1. 应用场景
> CombineTextInputFormat用于小文件过多的场景,它可以将多个小文件从逻辑上规划到一个切片中,这样多个小文件就可以交给一个MapTask处理.

2. 虚拟存储切片最大值设置
> CombineTextInputFormat.setMaxInputSplitSize(job,4194304); //4M <br>
> 注意:虚拟存储切片最大值设置最好根据实际的小文件大小情况来设置具体的值

3. 切片机制
> 生成切片过程包括:虚拟存储过程和切片过程两部分

![Combine机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dKjwapBLr4kyZYNfp2SCs6eLp5ic4cyAOoU4YiatVhHHdInbgxxulUhjw/0?wx_fmt=png)

> - 虚拟存储过程
>   - 将输入目录下所有文件大小,依次和设置的setMaxInputSplitSize值比较,如果不大于设置的最大值,逻辑上划分一个块.如果输入文件大于设置的最大值且大于两倍,那么以最大值切割一块;当剩余数据大小超过设置的最大值且不大于最大值2倍,此时将文件均分成2个虚拟存储块(防止出现太小切片)
>   - 例如setMaxInputSplitSize值为4M,输入文件大小为8.02M,则先逻辑上分成一个4M,剩余的大小为4.02M,如果按照4M逻辑划分,就会出现0.02M的小的虚拟存储文件,所以将剩余的4.02M文件切分成(2.01M和2.01M)两个文件
> - 切片过程
>   - 判断虚拟存储的文件大小是否大于setMaxInputSplitSize值,大于等于则单独形成一个切片
>   - 如果不大于则跟下一个虚拟存储文件进行合并,共同形成一个切片
>     - 测试举例:有4个小文件大小分别为1.7M,5.1M,3.4M以及6.8M这四个小文件,则虚拟存储之后形成6个文件块,大小分别为1.7M,(2.55M,2.55M),3.4M以及(3.4M,3.4M),最终会形成3个切片,大小分别为(1.7+2.55)M,(2.55+3.4)M,(3.4+3.4)M

#### 3.1.5 CombineTextInputFormat案例实操
1. 需求:将输入的大量小文件合并成一个切片统一处理

> - 出入数据:准备4个小文件,大小分别为1.7M,1.7M,3.5M,3.5M
> - 期望:期望一个切片处理4个文件

2. 实现过程

> - 不做任何处理,运行wordcount案例,切片数为4
> - 在WordCountDriver中增加代码,运行程序,查看切片数3

```text
// 特殊处理,改变虚拟存储切片大小
job.setInputFormatClass(CombineTextInputFormat.class);
CombineTextInputFormat.setMaxInputSplitSize(job,1024*1024*4); //4M
``` 

> - 在WordCountDriver中增加代码,运行程序,查看切片数1

```text
// 特殊处理,改变虚拟存储切片大小
job.setInputFormatClass(CombineTextInputFormat.class);
CombineTextInputFormat.setMaxInputSplitSize(job,1024*1024*20); //20M
``` 

#### 3.1.6 FileInputFormat实现类

![FileInputFormat实现类0](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6d4ia0EZ1OqpZEE33ia3H8ics0gQbA2fVwnLPFRVdbk20g3Dibws0LrH4fBA/0?wx_fmt=png)
![FileInputFormat实现类1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6doMNlVuru0ia7W8iasdM5fX7wzhWo92eupTJ1VwEHx3EmuQvaFQZx7orQ/0?wx_fmt=png)
![FileInputFormat实现类2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dNqricdjo7ticbJGcHND3T8ibFelay1k9P3D06PYfSk1ttZfbMZg4kqdhw/0?wx_fmt=png)
![FileInputFormat实现类3](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dnAicaib5Bp6ZKQHIIkvibLSNDEnwsPlORReDXIORGg42k34MQV3SNGA5A/0?wx_fmt=png)

#### 3.1.7 KeyValueTextInputFormat使用案例
1. 需求:统计文件中每一行第一个单词相同的行数

> - 输入数据

```text
banzhang ni hao
xihuan hadoop banzhang
banzhang ni hao
xihuan hadoop banzhang
```

> - 期望结果数据

```text
banzhang	2
xihuan	2
```

2. 需求分析

![KV需求分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dtPN3A1Rn8lweecslMllBgdtnfakibVgNhVfP0HVNPYFalX0mXppfvng/0?wx_fmt=png)

3. 代码实现

> - 编写程序,Mapper类,Reducer类,Driver驱动类

```text
Hadoop-Demo项目中com.weiliai.mr.kv包
```

4. 测试

#### 3.1.8 NLineInputFormat使用案例

1. 需求:对每个单词进行个数统计,要求根据每个输入文件的行数来规定输出多少个切片,3行一切片.

> - 输入数据

```text
banzhang ni hao
xihuan hadoop banzhang
banzhang ni hao
xihuan hadoop banzhang
banzhang ni hao
xihuan hadoop banzhang
banzhang ni hao
xihuan hadoop banzhang
banzhang ni hao
xihuan hadoop banzhang banzhang ni hao
xihuan hadoop banzhang
```

> - 期望输出数据

```text
Number of splits:4
```

2. 需求分析

![NLine需求分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6d8eYibLSxzZcRrSJJ4RcU58dj7Y89YtqysdFxMpnxlkVib6pib5UGeAIZA/0?wx_fmt=png)

3. 代码实现

> - 编写程序,Mapper类,Reducer类,Driver驱动类

```text
Hadoop-Demo项目中com.weiliai.mr.nl包
```

4. 测试

#### 3.1.9 自定义InputFormat

> 企业开发中,Hadoop框架自带的InputFormat类型不能满足所有应用场景,需要自定义InputFormat来解决实际问题.

> 自定义InputFormat步骤
> - 自定义一个类继承FileInputFormat
> - 改写RecordReader,实现一个一次读取一个完整文件封装为K,V
> - 在输出时使用SequenceFileOutFormat输出合并文件

#### 3.1.10 自定义InputFormat案例实操

> 无论HDFS还是MapReduce,在处理小文件时效率都非常低,但又难免面临处理大量小文件的场景,此时,就需要相应的解决方案,可以自定义InputFormat实现小文件合并

1. 需求:多个小文件合并成一个SequenceFile文件(SequenceFile文件是Hadoop用来存储二进制形式的key-value的文件格式),SequenceFile里面存储多个文件,存储的形式为文件路径+名称为key,文件内容为value.

> - 输入数据

```text
one.txt two.txt three.txt
```

> - 期望输出文件格式

```text
part-r-00000
```

2. 需求分析

![自定义InputFormat案例分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GkRXDPXDGM5QWQaAxiaib6dS6lXQ42NKAxtgMgjk7LmiaL1Q0O4glgD3tUPxcDC9szwBB3lWrWC9jA/0?wx_fmt=png)

3. 代码实现

> - 编写程序,Mapper类,Reducer类,Driver驱动类

```text
Hadoop-Demo项目中com.weiliai.mr.包
```

4. 测试

### 3.2 MapReduce工作流程


### 3.3 Shuffle机制

### 3.4 MapTask工作机制


### 3.5 ReduceTask工作机制


### 3.6 OutputFormat数据输出
#### 3.6.1 OutputFormat接口实现类


#### 3.6.2 自定义OutputFormat


#### 3.6.3 自定义OutputFormat案例实操


### 3.7 Join多种应用

#### 3.7.1 Reduce Join



#### 3.7.2 Reduce Join案例实操


#### 3.7.3 Map Join


#### 3.7.4 Map Join案例实操


### 3.8 计数器应用



### 3.9 数据清洗(ETL)

#### 3.9.1 数据清洗案例实操-简单解析版


#### 3.9.2 数据清洗案例实操-复杂解析版


### 3.10 MapReduce开发总结


