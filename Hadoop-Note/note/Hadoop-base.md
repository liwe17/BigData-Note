# Hadoop入门
## Hadoop框架与大数据生态
### Hadoop是什么
> - Hadoop是由Apache基金会所开发的分布式系统基础框架.
> - 主要解决,海量数据存储和海量数据的分析计算问题.
> - 广义上来说,Hadoop通常是一个更广泛的概念-Hadoop生态圈

### Hadoop发展历史
> - Lucene是由Doug Cutting开创的开源软件,用java书写代码,实现与Google类似的全文搜索功能,它提供了全文检索引擎的架构,包括完整的查询引擎和索引引擎. 
> - 2001年年底成为apache基金会的一个子项目
> - 对于大数量的场景,Lucene面对与Google同样的困难
> - 学习和模仿Google解决这些问题的办法:微型版Nutch
> - 可以说Google是hadoop的思想之源(Google在大数据方面的三篇论文)
>  - GFS --->HDFS
>  - Map-Reduce --->MR
>  - BigTable --->HBase
> - 2003-2004年,Google公开了部分GFS和MapReduce思想的细节,以此为基础Doug Cutting等人用了2年业余时间实现了DFS和MapReduce机制,使Nutch性能飙升
> - 2005 年Hadoop 作为 Lucene的子项目Nutch的一部分正式引入Apache基金会.2006年3月份,Map-Reduce和Nutch Distributed File System(NDFS)分别被纳入称为Hadoop的项目中 
> - Hadoop名字来源于Doug Cutting儿子的玩具大象
> - Hadoop就此诞生并迅速发展,标志这云计算时代来临

### Hadoop三大发行版本
> Hadoop三大发行版本:Apache,Cloudera,Hortonworks.
> - Apache版本最原始(最基础)的版本,对于入门学习最好.
> - Cloudera在大型互联网企业中用的较多.
> - Hortonworks文档最好.

1. Apache Hadoop
> 官网地址:http://hadoop.apache.org/releases.html <br>
> 下载地址:https://archive.apache.org/dist/hadoop/common/ 

2. Cloudera Hadoop
> 官网地址:https://www.cloudera.com <br>
> 下载地址:https://www.cloudera.com/downloads/cdh.html <br>
> Cloudera产品主要分为:
> - CDH是Cloudera的Hadoop发行版,完全开源,比Apache Hadoop在兼容性,安全性,稳定性上有所增强.
> - Cloudera Manager是集群软件的分发及管理监控平台,可以在几个小时内部署好一个Hadoop集群,并对集群的节点及服务进行实时监控.
> - Cloudera Support是对Hadoop的技术支持

3. Hortonworks Hadoop
> 官网地址：https://hortonworks.com/products/data-center/hdp/<br>
> 下载地址：https://hortonworks.com/downloads/#data-platform <br>
> 2018年和Cloudera合并

### Hadoop的优势
> - 高可靠性:Hadoop底层维护多个数据副本,所以即使Hadoop某个计算元素或存储出现故障,也不会导致数据丢失.
> - 高扩展性:在集群间分配任务数据,可方便的扩展数以千计的节点.
> - 高效性:在MapReduce的思想下,Hadoop是并行工作的,以加快任务处理速度.
> - 高容错性:能够自动将失败的任务重新分配.

### Hadoop的组成
> Hadoop 1.x与Hadoop 2.x的区别

![版本区别](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhO4rO73HbNO81P3cKcGMt25vPWGJI6hb4ZZAPbTuC4ZeB3vjOoE1iaiaDg/0?wx_fmt=png)

#### HDFS架构概述
> HDFS(Hadoop Distributed File System)的架构概述:<br>
![HDFS概述](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOTLsXeibX15IA7z30o3oNlNjrGU90QicMdXdvnjubkvpsibdYtQibJ28bxg/0?wx_fmt=png)

#### YARN架构概述
> YARN架构概述

![YARN架构概述](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhO9HbygMapibjjheBzib4mODHba4kTBGNKgMD7sloaCiclWMDrthTicrDkhA/0?wx_fmt=png)

#### MapReduce架构概述
> MapReduce将计算分为两个阶段:Map和Reduce
> - Map阶段并行处理数据 
> - Reduce阶段对Map的结果记性汇总

![MapReduce架构概述](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhO6HJaRJOVAPaJ5rMgxB0yGicR4dEOIslAEFZPYhMNDrLfoXeSMiag03mg/0?wx_fmt=png)

### 大数据技术生态体系
> 大数据技术生态体系

![大数据技术生态体系](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOZybZTPqAFUtCCHmAicuicAEVNdopTKAMvQrFTUVsWJapDbmBd3cYzuibw/0?wx_fmt=png)

> 相关名词解释
> - Sqoop是一款开源的工具,主要用于在Hadoop,Hive与传统的数据库(MySql)间进行数据的传递,可以将一个关系型数据库(例:MySQL,Oracle等)中的数据导进到Hadoop的HDFS中,也可以将HDFS的数据导进到关系型数据库中.
> - Flume是Cloudera提供的一个高可用的,高可靠的,分布式的海量日志采集,聚合和传输的系统,Flume支持在日志系统中定制各类数据发送方,用于收集数据;同时,Flume提供对数据进行简单处理,并写到各种数据接受方(可定制)的能力
> - Kafka是一种高吞吐量的分布式发布订阅消息系统.
>  - 通过O(1)的磁盘数据结构提供消息的持久化,这种结构对于即使数以TB的消息存储也能够保持长时间的稳定性能
>  - 高吞吐量:即使是非常普通的硬件Kafka也可以支持每秒数百万的消息
>  - 支持通过Kafka服务器和消费机集群来分区消息
>  - 支持Hadoop并行数据加载
> - Storm用于"连续计算",对数据流做连续查询,在计算时就将结果以流的形式输出给用户
> - Spark是当前最流行的开源大数据内存计算框架.可以基于Hadoop上存储的大数据进行计算
> - Oozie是一个管理Hadoop作业(job)的工作流程调度管理系统
> - HBase是一个分布式的,面向列的开源数据库.HBase不同于一般的关系数据库,它是一个适合于非结构化数据存储的数据库
> - Hive是基于Hadoop的一个数据仓库工具,可以将结构化的数据文件映射为一张数据库表,并提供简单的SQL查询功能,可以将SQL语句转换为MapReduce任务进行运行,其优点是学习成本低，可以通过类SQL语句快速实现简单的MapReduce统计,不必开发专门的MapReduce应用,十分适合数据仓库的统计分析
> - R是用于统计分析,绘图的语言和操作环境.R是属于GNU系统的一个自由,免费,源代码开放的软件.它是一个用于统计计算和统计制图的优秀工具
> - Apache Mahout是个可扩展的机器学习和数据挖掘库
> - Zookeeper是Google的Chubby一个开源的实现.它是一个针对大型分布式系统的可靠协调系统,提供的功能包括:配置维护,名字服务,分布式同步,组服务等.ZooKeeper的目标就是封装好复杂易出错的关键服务,将简单易用的接口和性能高效,功能稳定的系统提供给用户

### 推荐系统框架图
> 推荐系统框架图

![推荐系统框架图](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOKEIJfoFXZUVmBdJHaYUMwvBbvKTIvW5LRC1YKAtAiaJSdbY8vunsE5g/0?wx_fmt=png)

## Hadoop运行环境搭建
