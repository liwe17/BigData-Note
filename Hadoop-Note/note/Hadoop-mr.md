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
   Hadoop-Demo项目中com.weiliai.mr.flowsum包
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
Hadoop-Demo项目中com.weiliai.mr.nline包
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
Hadoop-Demo项目中com.weiliai.mr.customize.input包
```

4. 测试

### 3.2 MapReduce工作流程

1. 流程示意图

![流程图1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIcibUybfrctN7ic7qEf24O5jia0U9d0R273lkYZMjgzWE4UsZn9x7oJywnZw/0?wx_fmt=png)
![流程图2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIcibFZgtu8TPQVewqKFGN2cAVPXsicVQjmN86a0beqRSWoicm4QBbUSUzCrQ/0?wx_fmt=png)

2. 流程详解

> 上面的流程是整个MapReduce最全工作流程,但是Shuffle过程只是从第7步开始到第16步结束,具体Shuffle过程详解:
> - MapTask收集到我们的map()方法输出的kv对,放到内存缓冲区
> - 从内存缓冲区不断溢出本地磁盘文件,可能会溢出多个文件
> - 多个溢出文件会被合成大的溢出文件
> - 在溢出及合并的过程中,都要调用partitioner进行分区和针对key进行排序
> - ReduceTask根据自己的分区号,去各个MapTask机器上取相应的结果分区数据
> - ReduceTask会取到同一个分区的来自不同MapTask的结果文件,ReduceTask会将这些文件在进行合并(归并排序)
> - 合成大文件后,shuffle的过程也就结束了,后面进入ReduceTask的逻辑运算过程(从文件中取出一个一个的键值对Group,调用用户自定义reduce()方法)

3. 注意

> Shuffle中的缓冲区大小会影响到MapReduce程序的执行效率,原则上说,缓冲区越大,磁盘io的次数越少,执行速度就越快.

> 缓存区的大小可以通过参数调整,参数:io.sort.mb默认100M

4. 源码解析

### 3.3 Shuffle机制

#### 3.3.1 Shuffle机制
> Map方法之后,Reduce方法之前的数据处理过程称之为Shuffle

![Shuffle机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIcibA461JiaDebK58IabwXib5CV3Aq9iby95TvzzzhHWibGOBQEIVDFnSQOvYw/0?wx_fmt=png)

#### 3.3.2 Partition分区

![分区1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIcibKtFHtqibC69W4N4yRicEBAzHL87ia2LOVXfqDiccNuiafxFibiblCywdUhadg/0?wx_fmt=png)
![分区2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIciblL1N5dDUJYPibsMcreIoUyV3biaQY4FbCiaib9M88Rpibbgv7cNrlicicXm5A/0?wx_fmt=png)
![分区3](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIcibzlwE9f2q9ia4ibglXStH0tpiak6zQMgavCA9fMBN2kwhLAJpJ21FUPUgQ/0?wx_fmt=png)

#### 3.3.3 Partition分区案例实操

1. 需求:将统计结果按照手机归属地不同省份输出到不同文件中(分区)

> 输入数据

```text
1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
2	13846544121	192.196.100.2			264	0	200
...详见phone_data.txt
```

> 期望输出数据
> - 手机号136,137,138,139开头都分别放到一个独立的4个文件中,其他开头的放到一个文件中.

2．需求分析

![分区案例需求分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1r2jxcCOCUEUIUMarSCIcibtTNOMb8b1gy51CnPq8kms9anhetH4u5lfKbr8iavtsOE3SZtvGwaVbw/0?wx_fmt=png)

3. 在案例2.4的基础上,增加一个分区类

```java
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {

        //获取key的前三位
        String preNum = text.toString().substring(0, 3);
        int partition;
        switch (preNum) {
            case "136":
                partition = 0;
                break;
            case "137":
                partition = 1;
                break;
            case "138":
                partition = 2;
                break;
            case "139":
                partition = 3;
                break;
            default:
                partition = 4;
        }
        
        return partition;
    }
}
```

4. 在驱动函数中增加自定义数据分区设置和ReduceTask设置

```java
public class FlowCountDriver {

    public static void main(String[] args) throws Exception{

        args = new String[]{"d:/fcinput","d:/fcoutput"};

        //1. 获取Job实例,并设置启动jar
        final Job job = Job.getInstance();
        job.setJarByClass(FlowCountDriver.class);
        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(5);
        //2. 关联mapper和reduce
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        //3. 设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //4. 设置最终输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //5. 设置输入输出
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //6. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
```
#### 3.3.4 WritableComparable排序

> 排序是MapReduce框架中最重要的操作之一.

> MapTask和ReduceTask均会对数据按照key进行排序,该操作属于Hadoop的默认行为,任何应用程序中数据均会被排序,而不管逻辑上是否需要.

> 默认排序是按照字典顺序排序,且实现该排序的方法的快速排序.

> 对MapTask,它会将处理的结果暂时放到环形缓冲区中,当环形缓冲区使用率达到一定阀值,再对缓冲区的数据进行一次快速排序,并将这些有序数据溢写到磁盘上,而当数据处理完毕后,它会磁盘上所有文件进行归并排序.

> 对于ReduceTask,它从每个MapTask上远程拷贝相应的数据文件,如果文件大小超过一定阀值,则溢写磁盘上,否则存储在内存中,如果磁盘上文件数目达到一定阀值,则进行一次归并排序以生成一个更大文件;如果内存中文件大小或者数据超过一定阀值,则进行一次合并后将数据溢写到磁盘上,当所有数据拷贝完成后,ReduceTask统一对内存和磁盘上的所有数据进行一次归并排序.

1. 排序的分类

> 部分排序
> - MapReduce根据输入记录的键对数据集排序,保证输出的每个文件内部有序.

> 全排序
> - 最终输出结果只有一个文件,且文件内部有序,实现方式是只设置了一个ReduceTask,但该方法在处理大型文件时效率低,因为一台机器处理所有文件,完全丧失了MapReduce所提供的并行架构.

> 辅助排序:(GroupingComparator分组)
> - 在Reduce端对key进行分组,应用于:在接收的key为bean对象时,想让一个或几个字段相同(全部字段比较不相同)的key进行到同一个reduce方法时,可以采用分组排序.

> 二次排序
> - 在自定义排序过程中,如果compareTo中的判断条件为两个即为二次排序

2. 自定义排序WritableComparable

> 原理分析
> - bean对象作为key传输,需要实现WritableComparable接口重写compareTo方法,就可以实现排序

#### 3.3.5 WritableComparable排序案例实操(全排序)
1. 需求:根据案例2.3产生的结果再次对总流量进行排序

> - 输入数据,原始数据第一次处理后的数据

2. 需求分析

![需求分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2d1qic5ohLjvgVyp3mia9lYyQVqAZBkcVKnOb6ia7aQ6rXeOypicF4CUlXq1hnmrKHQic9iaquHID8wKwA/0?wx_fmt=png)

3. 代码实现
> - 编写程序,Mapper类,Reducer类,Driver驱动类

```text
Hadoop-Demo项目中com.weiliai.mr.sort包
```

 
#### 3.3.6 WritableComparable排序案例实操（区内排序）

1. 需求:要求每个省份手机号输出的文件中按照总流量内部排序.

2. 需求分析,基于前一个需求,增加自定义分区类,分区按照省份手机号设置

![分区排序案例需求分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2d1qic5ohLjvgVyp3mia9lYy7BTIdtzTxI3WSSjA4icT3Ey1a4XoR3QSlcdQeodlmVZf7caEgs4lXqQ/0?wx_fmt=png)

3. 案例实操

```java
public class ProvincePartitioner extends Partitioner<FlowBean, Text> {

    @Override
    public int getPartition(FlowBean flowBean, Text value, int numPartitions) {
        String preNum = value.toString().substring(0, 3);
        switch (preNum){
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }
}

//
public class FlowCountSortDriver {

    public static void main(String[] args) throws Exception{

        args = new String[]{"D:\\fcinput2","D:\\fcoutput2"};

        //1. 获取job实例
        final Job job = Job.getInstance();
        //2. 设置jobClass
        job.setJarByClass(FlowCountSortDriver.class);
        // 加载自定义分区类
        job.setPartitionerClass(ProvincePartitioner.class);
        // 设置ReduceTask个数
        job.setNumReduceTasks(5);
        //3. 关联mapper和reducer
        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountSortReducer.class);
        //4. 设置mapper输入输出
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        //5. 设置最终输入输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //6. 设置job输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交任务
        System.out.println(job.waitForCompletion(true)?0:1);
    }

}
```

#### 3.3.7 Combiner合并

> Combiner合并
> - Combiner是MR程序中Mapper和Reducer之外的一种组件.
> - Combiner组件的父类就是Reducer.
> - Combiner和Reducer的区别在于运行位置.
>   - Combiner是在每一个MapTask所在节点运行
>   - Reducer是在接收全局所有Mapper的输出结果
> - Combiner的意义就是对每一个MapTask的输出进行局部汇总,以减少网络传输量.
> - Combiner能够应用的前提是不能影响最终的业务逻辑,而且,Combiner的输出应该根Reducer的输出kv类型要对应起来.
> - 自定义Combiner实现步骤,继承Reducer类,重写Reducer方法

#### 3.3.8 Combiner合并案例实操

1. 需求:统计过程中对每一个MapTask的输出进行局部汇总,以减少网络传输量采用Combiner功能.

> - 输入数据

```text
atguigu atguigu
ss ss
cls cls
jiao
banzhang
xue
hadoop
... 详见hello.txt
```

> - 期望输出数据
>   - 期望:Combine输入数据多,输出时经过合并,输出数据降低.

2. 需求分析

![输出分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2d1qic5ohLjvgVyp3mia9lYySmHWG1aQbnKkia4YExdfuSNDiaT235ia2pPvxxibwuic7KWVDZwYOP6022g/0?wx_fmt=png)

3. 代码实现

```text
Hadoop-Demo项目中com.weiliai.mr.wordcount包
```

#### 3.3.9 GroupingComparator分组(辅助排序)

> 对Reduce阶段的数据根据某一个或几个字段进行分组

> 分组排序步骤
> - 自定义类继承WritableComparator
> - 重写compare()方法
> - 创建一个构造将比较对象的类传给父类

#### 3.3.10 GroupingComparator分组案例

1. 需求,有如下订单,现在需要求出每个订单中最贵的商品

> - 输入数据

```text
订单id	商品id	成交金额
0000001	Pdt_01	222.8	
        Pdt_02	33.8
0000002	Pdt_03	522.8   
        Pdt_04	122.4
        Pdt_05	722.4
0000003	Pdt_06	232.8    
        Pdt_02	33.8

详见GroupComparator.txt
```

> - 期望输出数据

```text
1   222.8
2   722.4
3   232.8
```

2. 需求分析

![求每个订单中最贵的商品](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2d1qic5ohLjvgVyp3mia9lYyiav0134icclNYOZwyYrXYpHuRE0L2t3LCJPsiaAsuNyicpr588h1deeDIQ/0?wx_fmt=png)

3. 代码实现

```text
Hadoop-Demo项目中com.weiliai.mr.order包
```


### 3.4 MapTask工作机制

![MapTask工作机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2zo0BCPGcZw1IUb12qAuc7j2mLzz20qeYrpRAFhJoGx4o4H2ibcDhTfzvVEzEG23omQdbUZ5eoP3Q/0?wx_fmt=png)

> - Read阶段:MapTask通过用户编写的RecordReader,从输入的InputSplit中解析出一个个key/value.
> - Map阶段:该节点主要是将解析出的key/value交给用户编写map()函数处理,并产生一系列新的key/value.
> - Collect收集阶段:在用户编写map()函数中,当数据处理完成后,一般会调用outputCollector.collect()输出结果.在该函数内部,它会将生成的key/value分区(调用Partitioner),并写入一个环形内存缓冲区中.
> - Spill阶段:即溢写,当环形缓冲区满后,MapReduce会将数据写到本地磁盘上,生成一个临时文件,需要注意的是,将数据写入本地磁盘之前,先要对数据进行一次本地排序,并在必要时对数据合并,压缩等操作.
>   - 溢写阶段详情
>     - 利用快速排序算法对缓冲区内的数据进行排序,排序方式是,先按照分区编号Partition进行排序,然后按照key进行排序,这样,经过排序后,数据分区为单位聚集在一起,且同一分区所有数据按照key有序.
>     - 按照分区编号由小到大一次将每个分区中的数据写入任务工作目录下的临时文件output/spillN.out(N表示当前溢写次数)中.如果用户设置了Combiner,则写入文件之前,对每个分区中的数据进行一次聚集操作.
>     - 将分区数据的元信息写到内存索引数据结果SpillRecord中,其中每个分区的元信息包括在临时文件中的偏移量,压缩前数据大小和压缩后数据大小.如果当前内存索引大小超过1MB,则将内存索引写到文件output/spillN.out.index中
> - Combine阶段:当所有数据处理完成后,MapTask对所有临时文件进行一次合并,以确保最终只会生成一个数据文件.

> 当所有数据处理完成后,MapTask会将所有临时文件合并成一个大文件,并保存到文件output/file.out中,同时生成相应的索引文件output/file.out.index.

> 在进行文件合并过程中,MapTask以分区为单位进行合并,对于某个分区,它将采用多轮递归合并的方式,每轮合并io.sort.factor(默认10)个文件,并将产生的文件重新加入待合并列表中,对文件排序后,重复以上过程,直到最终得到一个大文件.

> 让每个MapTask最终只生成一个数据文件,可避免同时打开大量文件和同时读取大量小文件产生的随机读取带来的开销.

### 3.5 ReduceTask工作机制

1. ReduceTask工作机制,如图:

![ReduceTask工作机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2zo0BCPGcZw1IUb12qAuc7MxDrdJWFmP28DXl0wMRbUGN7Lzl1pa3NgBE8ocu69RkUF4Eakwm1Kw/0?wx_fmt=png)

> - Copy阶段:ReduceTask从各个MapTask上远程拷贝一片数据,并针对某一片数据,如果其大小超过一定阀值,则写到磁盘上,否则直接放到内存中.
> - Merge阶段:在远程拷贝数据的同时,ReduceTask启动了两个后台线程堆内存和磁盘上的文件进行合并,以防止内存使用过多或磁盘上文件过多.
> - Sort阶段:按照MapReduce语义,用户编写reduce()函数输入数据是按key进行聚集的一组数据,为了将key相同的数据聚在一起,Hadoop采用了基于排序的策略,由于每个MapTask已经实现了对自己的处理结果进行局部排序,因此ReduceTask只需要对所有数据进行一次归并排序即可.
> - Reduce阶段:reduce()函数将计算结果写到HDFS上.

2. 设置ReduceTask并行度(个数)

> ReduceTask的并行度同样影响整个Job的执行并发度和执行效率,但与MapTask的并发数由切片数决定不同,ReduceTask数量的决定是可以直接手动设置

```text
//默认值1,手动设置为4
job.setNumReduceTasks(4);
```

3. 实验:测试ReduceTask多少个合适

> 实验环境:1个Master节点,16个Slave节点:CPU:8GHZ,内存:2G

> 实验结论:

<table>
    <tr>
        <th>MapTask</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
        <th>16</th>
    </tr>
    <tr>
        <th>ReduceTask</th>
        <th>1</th>
        <th>5</th>
        <th>10</th>
        <th>15</th>
        <th>16</th>
        <th>20</th>
        <th>25</th>
        <th>30</th>
        <th>45</th>
        <th>60</th>
    </tr>
    <tr>
        <th>总时间</th>
        <th>892</th>
        <th>146</th>
        <th>110</th>
        <th>92</th>
        <th>88</th>
        <th>100</th>
        <th>128</th>
        <th>101</th>
        <th>145</th>
        <th>104</th>
    </tr>
</table>

4. 注意事项
    
> - ReduceTask=0,表示没有Reduce阶段,输出文件个数和Map个数一致.
> - ReduceTask默认是1,所以输出文件只有一个.
> - 如果数据分布不均匀,就可能在Reduce阶段产生数据倾斜.
> - ReduceTask数量并不是任意设置,还要考虑业务逻辑需求,有些情况下,需要计算全局汇总结果,就只能有一个ReduceTask.
> - 具体多少个ReduceTask,需要根据集群性能而定.
> - 如果分区数不是1,但是ReduceTask为1,是否执行分区过程,答案是:不执行分区过程,因为在MapTask的源码中,执行分区的前提是先判断ReduceNum个数是否大于1,不大于1肯定不执行.


### 3.6 OutputFormat数据输出
#### 3.6.1 OutputFormat接口实现类

> OutputFormat是MapReduce输出的基类,所有实现MapReduce输出都实现了OutputFormat接口.
> - 文本输出TextOutputFormat
>   - 默认的输出格式TextOutputFormat,它把每条记录写为文本行,它的键值可以使任意类型,应为TextOutputFormat调用toString()方法把它们转换为字符串.
> - SequenceFileOutputFormat
>   - 将SequenceFileOutputFormat输出作为后续MapReduce任务的输入,这便是一种好的输出格式,因为它的格式紧凑,很容易被压缩.
> - 自定义OutputFormat
>   - 根据用户需求,自定义实现输出.

#### 3.6.2 自定义OutputFormat

> 自定义outputFormat使用场景及步骤
> - 使用场景
>   - 为了实现控制最终文件的输出路径和输出格式,可以自定义OutputFormat.
>     - 例如:要在一个MapReduce程序中根据数据的不同输出两类结果到不同的目录,这类灵活的输出需求可以通过自定义OutputFormat来实现.
> - 自定义OutputFormat步骤
>   - 自定义一个类继承FileOutputFormat
>   - 改写RecordWriter,具体改写输出数据的方法write().

#### 3.6.3 自定义OutputFormat案例实操

1. 需求:过滤输入的log日志,包含atguigu的网站输出到d:/atguigu.log,不包含atguigu的网站输出到d:/other.log.

> - 输入数据

```text
http://www.baidu.com
http://www.google.com
http://cn.bing.com
http://www.atguigu.com
http://www.sohu.com
http://www.sina.com
http://www.sin2a.com
http://www.sin2desa.com
http://www.sindsafa.com
...详见log.txt
```

> - 期望输出数据

```text
atguigu.log
other.log
```

2. 需求分析

![自定义OutputFormat案例分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2zo0BCPGcZw1IUb12qAuc7ZLRGBECMckcWzLibVabuAeVwQHCgrhv6OfhNru2RKWn554Irq7AJZOg/0?wx_fmt=png)

3. 代码实现



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


