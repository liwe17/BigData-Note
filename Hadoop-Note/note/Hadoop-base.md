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

![推荐系统框架图](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOib2HEMbgUQL77cxZaInDXtAStr4utX4cWNTolUbQ6ibUPUfy7G2pzWTg/0?wx_fmt=png)

## Hadoop运行环境搭建
### 虚拟机环境准备
> - 安装虚拟机,最小化安装即可
```shell script
[root@hadoop102 ~]# cat /etc/centos-release
CentOS Linux release 7.6.1810 (Core) 
```
> - 修改虚拟机的静态IP

```shell script
[root@hadoop102 ~]# cd /etc/sysconfig/network-scripts/
[root@hadoop102 network-scripts]# cat ifcfg-ens33 
TYPE="Ethernet"
BOOTPROTO="static"
DEFROUTE="yes"
PEERDNS="yes"
PEERROUTES="yes"
#修改为本机IP
IPADDR=192.168.108.106
NETMASK=255.255.255.0
#修改本地虚拟网卡地址
GATEWAY=192.168.108.2
DNS1=8.8.8.8
DNS2=114.114.114.114
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_PEERDNS="yes"
IPV6_PEERROUTES="yes"
IPV6_FAILURE_FATAL="no"
NAME="ens33"
DEVICE="ens33"
ONBOOT="yes"
```

> - 修改主机名

```shell script
[root@hadoop102 network-scripts]# vi /etc/hosts
#设置对应IP和主机名
192.168.108.106 hadoop102

[root@hadoop102 network-scripts]# vi /etc/hostname 
#设置主机名
hadoop101

[root@hadoop102 network-scripts]# reboot
重启生效
```

> - 关闭防火墙

```shell script
#Centos 7 firewall 命令：
#查看已经开放的端口：
firewall-cmd --list-ports

#开启端口
#firewall-cmd --zone=public --add-port=80/tcp --permanent
#命令含义：
#–zone #作用域
#–add-port=80/tcp #添加端口，格式为：端口/通讯协议
#–permanent #永久生效，没有此参数重启后失效

#重启防火墙
firewall-cmd --reload #重启firewall
systemctl stop firewalld.service #停止firewall
systemctl disable firewalld.service #禁止firewall开机启动
firewall-cmd --state #查看默认防火墙状态（关闭后显示notrunning，开启后显示running）

```
> - 在/opt目录下创建文件夹module和software,用于后续统一管理
```shell script
[root@hadoop102 opt]# mkdir module
[root@hadoop102 opt]# mkdir software
[root@hadoop102 opt]# ll
总用量 0
drwxr-xr-x. 4 root root 46 12月  9 09:58 module
drwxr-xr-x. 2 root root 67 12月  9 09:51 software
[root@hadoop102 opt]#
```
### 安装JDK
> - 卸载现有JDK
```shell script
#1. 查询是否安装Java软件
[root@hadoop102 opt]# rpm -qa | grep java
#2. 如果安装的版本低于1.7，卸载该JDK
[root@hadoop102 opt]# rpm -e 软件包
#3. 查看JDK安装路径
[root@hadoop102 opt]# which java
```

> - 用SecureCRT工具将JDK导入到opt目录下面的software文件夹下面(alt+p)

```shell script
# 进入到指定文件夹
sftp> cd /opt/software/
sftp> pwd              
/opt/software
# 进入jdk所在目录,并上传
sftp> lcd D:\BaiduNetdiskDownload\bigData\hadoop\2.资料\01_jar包\00_jdk
sftp> put jdk-8u144-linux-x64.tar.gz
```

> - 解压JDK到/opt/module目录下
```shell script
[root@hadoop102 software]# cd /opt/software/ && ls
jdk-8u144-linux-x64.tar.gz
[root@hadoop102 software]# tar -zxvf jdk-8u144-linux-x64.tar.gz -C /opt/module/
```

> - 配置JDK环境变量

```shell script
#在profile文件末尾添加JDK路径
[root@hadoop102 jdk1.8.0_144]# vi + /etc/profile
#JAVA_HOME
export JAVA_HOME=/opt/module/jdk1.8.0_144
export PATH=$PATH:$JAVA_HOME/bin
#保存后退出:wq

#让修改后的文件生效
[root@hadoop102 jdk1.8.0_144]# source /etc/profile
```

> - 测试JDK是否安装成功

```shell script
[root@hadoop102 opt]# java -version
java version "1.8.0_144"
Java(TM) SE Runtime Environment (build 1.8.0_144-b01)
Java HotSpot(TM) 64-Bit Server VM (build 25.144-b01, mixed mode)
```

### 安装Hadoop
> - Hadoop下载地址:https://archive.apache.org/dist/hadoop/common/hadoop-2.7.2/

> - 用SecureCRT工具将Hadoop导入到opt目录下面的software文件夹下面(alt+p)

```shell script
# 进入到指定文件夹
sftp> cd /opt/software/
sftp> pwd              
/opt/software
# 进入Hadoop所在目录,并上传
sftp> lcd D:\BaiduNetdiskDownload\bigData\hadoop\2.资料\01_jar包\
sftp> put hadoop-2.7.2.tar.gz
```

> - 解压Hadoop到/opt/module目录下

```shell script
[root@hadoop102 software]# cd /opt/software/ && ls
hadoop-2.7.2.tar.gz  jdk-8u144-linux-x64.tar.gz
[root@hadoop102 software]# tar -zxvf hadoop-2.7.2.tar.gz -C /opt/module/
```

> - 配置Hadoop环境变量

```shell script
#在profile文件末尾添加Hadoop路径
[root@hadoop102 module]# vi + /etc/profile
##HADOOP_HOME
export HADOOP_HOME=/opt/module/hadoop-2.7.2
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
#保存后退出:wq

#让修改后的文件生效
[root@hadoop102 module]# source /etc/profile

```

> - 测试Hadoop是否安装成功

```shell script
[root@hadoop102 opt]# hadoop version
Hadoop 2.7.2
Subversion Unknown -r Unknown
Compiled by root on 2017-05-22T10:49Z
Compiled with protoc 2.5.0
From source with checksum d0fda26633fa762bff87ec759ebe689c
This command was run using /opt/module/hadoop-2.7.2/share/hadoop/common/hadoop-common-2.7.2.jar
```

> - Hadoop目录结构

```shell script
[root@hadoop102 hadoop-2.7.2]# ll
总用量 28
drwxr-xr-x. 2 root root   194 5月  22 2017 bin
drwxr-xr-x. 3 root root    20 5月  22 2017 etc
drwxr-xr-x. 2 root root   106 5月  22 2017 include
drwxr-xr-x. 3 root root    20 5月  22 2017 lib
drwxr-xr-x. 2 root root   239 5月  22 2017 libexec
-rw-r--r--. 1 root root 15429 5月  22 2017 LICENSE.txt
-rw-r--r--. 1 root root   101 5月  22 2017 NOTICE.txt
-rw-r--r--. 1 root root  1366 5月  22 2017 README.txt
drwxr-xr-x. 2 root root  4096 5月  22 2017 sbin
drwxr-xr-x. 4 root root    31 5月  22 2017 share
[root@hadoop102 hadoop-2.7.2]# 
```

> - 重要目录
>  - bin目录:存放对Hadoop相关服务(HDFS,YARN)进行操作的脚本
>  - etc目录:Hadoop的配置文件目录,存放Hadoop的配置文件
>  - lib目录:存放Hadoop的本地库(对数据进行压缩解压缩功能)
>  - sbin目录:存放启动或停止Hadoop相关服务的脚本
>  - share目录:存放Hadoop的依赖jar包,文档和官方案例

## Hadoop运行模式
> Hadoop运行模式包括:本地模式,伪分布式模式以及完全分布式模式.<br>
> Hadoop官方网站:http://hadoop.apache.org/
### 本地模式
> 官方案例:https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html
#### 官方Grep案例
1. 在hadoop-2.7.2文件下面创建一个input文件夹

```shell script
[root@hadoop102 hadoop-2.7.2]# mkdir input
```

2. 将Hadoop的xml配置文件复制到input

```shell script
[root@hadoop102 hadoop-2.7.2]# cp etc/hadoop/*.xml input
```

3.执行share目录下的MapReduce程序

```shell script
[root@hadoop102 hadoop-2.7.2]# bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar grep input output 'dfs[a-z.]+'
```
4. 查看输出结果

```shell script
[root@hadoop102 hadoop-2.7.2]# cat output/*
1       dfsadmin
[root@hadoop102 hadoop-2.7.2]# cd output/
[root@hadoop102 output]# ll
总用量 4
-rw-r--r--. 1 root root 11 5月   1 22:12 part-r-00000
-rw-r--r--. 1 root root  0 5月   1 22:12 _SUCCESS
[root@hadoop102 output]# 
```

#### 官方WordCount案例
1. 在hadoop-2.7.2文件下面创建一个wcinput文件夹

```shell script
[root@hadoop102 hadoop-2.7.2]# mkdir wcinput
```

2. 在wcinput文件下创建一个wc.input文件

```shell script
[root@hadoop102 hadoop-2.7.2]## cd wcinput/ && touch wc.input
[root@hadoop102 wcinput]# ls
wc.input
[root@hadoop102 wcinput]# 
```

3. 编辑wc.input文件

```shell script
[root@hadoop102 wcinput]# vi wc.input 
hadoop yarn
hadoop mapreduce
aiguigu
atguigu

~
"wc.input" 5L, 46C written
[root@hadoop102 wcinput]# 
```

4. 回到Hadoop目录/opt/module/hadoop-2.7.2,执行程序

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar wordcount wcinput wcoutput
```
5. 查看结果

````shell script
[root@hadoop102 hadoop-2.7.2]# cat wcoutput/
part-r-00000       .part-r-00000.crc  _SUCCESS           ._SUCCESS.crc      
[root@hadoop102 hadoop-2.7.2]# cat wcoutput/part-r-00000 
aiguigu 1
atguigu 1
hadoop  2
mapreduce       1
yarn    1
[root@hadoop102 hadoop-2.7.2]# 
````

### 伪分布式运行模式
#### 启动HDFS并运行MapReduce程序
> 分析
>  - 配置集群
>  - 启动,测试集群增删查
>  - 执行WordCount案例

> 执行步骤
>  - 配置集群
>    - 设置 /opt/module/hadoop-2.7.2/etc/core-site.xml
>    - 设置 /opt/module/hadoop-2.7.2/etc/hdfs-site.xml
```shell script
[root@hadoop102 hadoop]# vi core-site.xml 
<configuration>
    <!-- 指定HDFS中NameNode的地址 -->
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop102:9000</value>
    </property>
    <!-- 指定Hadoop运行时产生文件的存储目录 -->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/opt/module/hadoop-2.7.2/data/tmp</value>
    </property>
"core-site.xml" 31L, 1101C written
[root@hadoop102 hadoop]# 
[root@hadoop102 hadoop]# vi hdfs-site.xml 
<configuration>
   <!-- 指定HDFS副本的数量 -->
   <property>
        <name>dfs.replication</name>
        <value>1</value>
"hdfs-site.xml" 25L, 903C written
[root@hadoop102 hadoop]# 
```
>  - 启动集群
>    - 格式化NameNode(第一次启动时格式化,以后就不要总格式化)
>    - 启动NameNode
>    - 启动DataNode
```shell script
[root@hadoop102 hadoop-2.7.2]# bin/hdfs namenode -format
20/05/01 22:49:48 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = hadoop102/192.168.108.106
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 2.7.2
STARTUP_MSG:   classpath = /opt/module/hadoop-2.7.2/etc/hadoop:
....省略
20/05/01 22:49:49 INFO util.ExitUtil: Exiting with status 0
20/05/01 22:49:49 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at hadoop102/192.168.108.106
************************************************************/
[root@hadoop102 hadoop-2.7.2]#
[root@hadoop102 hadoop-2.7.2]# sbin/hadoop-daemon.sh start namenode
starting namenode, logging to /opt/module/hadoop-2.7.2/logs/hadoop-root-namenode-hadoop102.out
[root@hadoop102 hadoop-2.7.2]#
[root@hadoop102 hadoop-2.7.2]# sbin/hadoop-daemon.sh start datanode
starting datanode, logging to /opt/module/hadoop-2.7.2/logs/hadoop-root-datanode-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# 
```

> - 查看集群
>    - 查看是否启动成功
>    - web端查看HDFS文件系统
>    - 查看产生的Log日志
```shell script
[root@hadoop102 hadoop-2.7.2]# jps
6855 NameNode
6935 DataNode
7006 Jps
[root@hadoop102 hadoop-2.7.2]#
```
> 查看控制台:http://hadoop102:50070/dfshealth.html#tab-overview

![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOSsiaqEcicicNzswsBAzsmKx5wM4Tb6bOSypEGW7GUiaWHzhj3jSD5NmT8g/0?wx_fmt=png)

```shell script
[root@hadoop102 logs]# pwd
/opt/module/hadoop-2.7.2/logs
[root@hadoop102 logs]# ll
总用量 60
-rw-r--r--. 1 root root 23925 5月   1 22:52 hadoop-root-datanode-hadoop102.log
-rw-r--r--. 1 root root   714 5月   1 22:52 hadoop-root-datanode-hadoop102.out
-rw-r--r--. 1 root root 27533 5月   1 22:52 hadoop-root-namenode-hadoop102.log
-rw-r--r--. 1 root root   714 5月   1 22:51 hadoop-root-namenode-hadoop102.out
-rw-r--r--. 1 root root     0 5月   1 22:51 SecurityAuth-root.audit
[root@hadoop102 logs]# more  hadoop-root-datanode-hadoop102.log
```

> 思考:为什么不能一直格式化NameNode，格式化NameNode，要注意什么？<br>
> - 因为格式化NameNode,会产生新的集群id,导致NameNode和DataNode的集群id不一致,集群找不到已往数据.所以,格式NameNode时,一定要先删除data数据和log日志,然后再格式化NameNode.
```shell script
[root@hadoop102 current]# pwd
/opt/module/hadoop-2.7.2/data/tmp/dfs/name/current
[root@hadoop102 current]# cat VERSION 
#Fri May 01 22:49:49 CST 2020
namespaceID=869888055
clusterID=CID-e3c27b4a-5c50-48cc-b7c8-46cd15a2a396
cTime=0
storageType=NAME_NODE
blockpoolID=BP-1896525074-192.168.108.106-1588344589329
layoutVersion=-63
[root@hadoop102 current]# 
[root@hadoop102 current]# cat VERSION 
#Fri May 01 22:49:49 CST 2020
namespaceID=869888055
clusterID=CID-e3c27b4a-5c50-48cc-b7c8-46cd15a2a396
cTime=0
storageType=NAME_NODE
blockpoolID=BP-1896525074-192.168.108.106-1588344589329
layoutVersion=-63
[root@hadoop102 current]#
```
> - 操作集群
>   - 在HDFS文件系统上创建一个input文件夹
>   - 将测试文件内容上传到文件系统上
>   - 查看上传的文件是否正确
>   - 运行MapReduce程序
>   - 查看输出结果
>   - 将测试文件内容下载到本地
>   - 删除输出结果
```shell script
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -mkdir -p /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -put wcinput/wc.input /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -ls /user/atguigu/input
Found 1 items
-rw-r--r--   1 root supergroup         46 2020-05-01 23:09 /user/atguigu/input/wc.input
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -cat /user/atguigu/input/wc.input
hadoop yarn
hadoop mapreduce
aiguigu
atguigu

[root@hadoop102 hadoop-2.7.2]# 
[root@hadoop102 hadoop-2.7.2]# bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar wordcount /user/atguigu/input/ /user/atguigu/output
[root@hadoop102 hadoop-2.7.2]# 
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -cat /user/atguigu/output/*
aiguigu 1
atguigu 1
hadoop  2
mapreduce       1
yarn    1
[root@hadoop102 hadoop-2.7.2]# 
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -get /user/atguigu/output/part-r-00000 ./wcoutput/
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -rm -r /user/atguigu/output
20/05/01 23:19:55 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 0 minutes, Emptier interval = 0 minutes.
Deleted /user/atguigu/output
[root@hadoop102 hadoop-2.7.2]# 

```
> 查看控制台:http://hadoop102:50070/explorer.html#/user/atguigu/input

![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhO3WfaiaJia3jA7ibYLPbwAzo8DS4iavl7sl7MvWkp8uHSxaEicjhAr3IRl1g/0?wx_fmt=png)

#### 启动YARN并运行MapReduce程序
> 分析
>   - 配置集群在YARN上运行MR
>   - 启动,测试集群增删查 
>   - 在YARN上执行WordCount案例

> 执行步骤
>   - 配置集群
>    - 配置/opt/module/hadoop-2.7.2/etc/hadoop/yarn-site.xml
>    - 配置:将mapred-site.xml.template重新命名为mapred-site.xml

```shell script
[root@hadoop102 hadoop]# pwd
/opt/module/hadoop-2.7.2/etc/hadoop
[root@hadoop102 hadoop]# 
[root@hadoop102 hadoop]# 
[root@hadoop102 hadoop]# vi yarn-site.xml 
<configuration>
<!-- Site specific YARN configuration properties -->
    <!-- Reducer获取数据的方式 -->
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>

    <!-- 指定YARN的ResourceManager的地址 -->
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>hadoop102</value>
    </property>
</configuration>
~
"yarn-site.xml" 29L, 1009C written
[root@hadoop102 hadoop]# mv mapred-site.xml.template mapred-site.xml
[root@hadoop102 hadoop]# vi mapred-site.xml 
<configuration>
   <!-- 指定MR运行在YARN上 -->
   <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
   </property>

"mapred-site.xml" 27L, 892C written
[root@hadoop102 hadoop]# 
```

> - 启动集群
>   - 启动前必须保证NameNode和DataNode已经启动
>   - 启动ResourceManager
>   - 启动NodeManager

```shell script
[root@hadoop102 hadoop-2.7.2]# jps
6855 NameNode
6935 DataNode
7468 Jps
[root@hadoop102 hadoop-2.7.2]# 
[root@hadoop102 hadoop-2.7.2]# sbin/yarn-daemon.sh start resourcemanager
starting resourcemanager, logging to /opt/module/hadoop-2.7.2/logs/yarn-root-resourcemanager-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# sbin/yarn-daemon.sh start nodemanager
starting nodemanager, logging to /opt/module/hadoop-2.7.2/logs/yarn-root-nodemanager-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# jps
7734 NodeManager
6855 NameNode
6935 DataNode
7496 ResourceManager
7835 Jps
[root@hadoop102 hadoop-2.7.2]# 
```

> - 操作集群
>   - YARN的浏览器页面查看
>   - 删除文件系统上的output文件
>   - 执行MapReduce程序
>   - 查看运行结果

> 查看控制台:http://hadoop102:8088/cluster

![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOMK97b37T7hu9GlkmzD793wiajEcALKqrpIsG8ichkL2bu4QHlVuiaa0vg/0?wx_fmt=png)
![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOYPxAd3bzP2bPuNwrSUMR9IA4aczvsppYNPeAeErib5pozN616sAPcmQ/0?wx_fmt=png)
```shell script
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -rm -R /user/atguigu/output
rm: `/user/atguigu/output': No such file or directory
[root@hadoop102 hadoop-2.7.2]# bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar wordcount /user/atguigu/input /user/atguigu/output
[root@hadoop102 hadoop-2.7.2]# bin/hdfs dfs -cat /user/atguigu/output/*
aiguigu 1
atguigu 1
hadoop  2
mapreduce       1
yarn    1
[root@hadoop102 hadoop-2.7.2]# 
```
#### 配置历史服务器
> 为了查看程序的历史运行情况,需要配置一下历史服务器

> - 配置mapred-site.xml
> - 启动历史服务器
> - 查看历史服务器是否启动
> - 查看JobHistory

```shell script
[root@hadoop102 hadoop]# pwd
/opt/module/hadoop-2.7.2/etc/hadoop
[root@hadoop102 hadoop]# vi mapred-site.xml 
<configuration>
   <!-- 指定MR运行在YARN上 -->
   <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
   </property>

   <!-- 历史服务器端地址 -->
   <property>
        <name>mapreduce.jobhistory.address</name>
        <value>hadoop102:10020</value>
   </property>
   <!-- 历史服务器web端地址 -->
   <property>
        <name>mapreduce.jobhistory.webapp.address</name>
        <value>hadoop102:19888</value>
   </property>
</configuration>
~
~
"mapred-site.xml" 36L, 1211C written
[root@hadoop102 hadoop]# 
[root@hadoop102 hadoop-2.7.2]# sbin/mr-jobhistory-daemon.sh start historyserver
starting historyserver, logging to /opt/module/hadoop-2.7.2/logs/mapred-root-historyserver-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# jps
8498 Jps
7734 NodeManager
6855 NameNode
6935 DataNode
7496 ResourceManager
8462 JobHistoryServer
[root@hadoop102 hadoop-2.7.2]# 
```
> 查看控制台:http://hadoop102:19888/jobhistory

![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOicO3qebEX5f9ttiaLCVF9OD6ibc9F8tY0l2FCu2bSiaEhyicURRT5tZaR7A/0?wx_fmt=png)

#### 配置日志的聚集
> 日志聚集概念:应用运行完成以后,将程序运行日志信息上传到HDFS系统上.<br>
> 日志聚集功能好处:可以方便的查看到程序运行详情,方便开发调试.<br>
> 注意:开启日志聚集功能,需要重新启动NodeManager,ResourceManager和HistoryManager.

> 开启日志聚集功能具体步骤
> - 配置yarn-site.xml
> - 关闭NodeManager,ResourceManager和HistoryManager
> - 启动NodeManager,ResourceManager和HistoryManager    
> - 删除HDFS上已经存在的输出文件
> - 执行WordCount程序
> - 查看日志

```shell script
[root@hadoop102 hadoop]# pwd
/opt/module/hadoop-2.7.2/etc/hadoop
[root@hadoop102 hadoop]# vi yarn-site.xml 
    <!-- 日志聚集功能使能 -->
    <property>
        <name>yarn.log-aggregation-enable</name>
        <value>true</value>
    </property>

    <!-- 日志保留时间设置7天 -->
    <property>
        <name>yarn.log-aggregation.retain-seconds</name>
        <value>604800</value>
    </property>

"yarn-site.xml" 42L, 1322C written
[root@hadoop102 hadoop]#
[root@hadoop102 hadoop-2.7.2]# sbin/yarn-daemon.sh stop resourcemanager
stopping resourcemanager
[root@hadoop102 hadoop-2.7.2]# sbin/yarn-daemon.sh stop nodemanager
stopping nodemanager
nodemanager did not stop gracefully after 5 seconds: killing with kill -9
[root@hadoop102 hadoop-2.7.2]# sbin/mr-jobhistory-daemon.sh stop historyserver
stopping historyserver
[root@hadoop102 hadoop-2.7.2]# jps
6855 NameNode
6935 DataNode
8671 Jps
[root@hadoop102 hadoop-2.7.2]# 
[root@hadoop102 hadoop-2.7.2]# sbin/yarn-daemon.sh start resourcemanager
starting resourcemanager, logging to /opt/module/hadoop-2.7.2/logs/yarn-root-resourcemanager-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# sbin/yarn-daemon.sh start nodemanager
starting nodemanager, logging to /opt/module/hadoop-2.7.2/logs/yarn-root-nodemanager-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# sbin/mr-jobhistory-daemon.sh start historyserver
starting historyserver, logging to /opt/module/hadoop-2.7.2/logs/mapred-root-historyserver-hadoop102.out
[root@hadoop102 hadoop-2.7.2]# jps
8704 ResourceManager
8947 NodeManager
6855 NameNode
6935 DataNode
9099 JobHistoryServer
9135 Jps
[root@hadoop102 hadoop-2.7.2]#
[root@hadoop102 hadoop-2.7.2]# hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar wordcount /user/atguigu/input /user/atguigu/output

```

> 查看控制台:http://hadoop102:19888/jobhistory

![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhO4fSicX7icJOtjDfIfDly0c07OSQicpXLfapuUrSUfAnjv2tHl65elRsAg/0?wx_fmt=png)
![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhO1pCsf15xDkzUqiaEZe4EEiaSK4xZjic1TYyTsGhAmXt76Sgj4pDlJBxDw/0?wx_fmt=png)
![web控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q39icTnx0nPicyicQ6SYgKTQhOIo44uq9sOmGLJdo2h5EMfgyqwG9NxMGYvIJYhGfibCv6nus3KpRC6Nw/0?wx_fmt=png)

#### 配置文件说明
> Hadoop配置文件分两类:
> - 默认配置文件
<table>
    <tr>
        <th>要获取的默认文件</th>
        <th>文件存放在Hadoop的jar包中的位置</th>
    </tr>
    <tr>
        <th>core-default.xml</th>
        <th>hadoop-common-2.7.2.jar/core-default.xml</th>
    </tr>
    <tr>
        <th>hdfs-default.xml</th>
        <th>hadoop-hdfs-2.7.2.jar/hdfs-default.xml</th>
    </tr>
    <tr>
        <th>yarn-default.xml</th>
        <th>hadoop-yarn-common-2.7.2.jar/ yarn-default.xml</th>
    </tr>
    <tr>
        <th>mapred-default.xml</th>
        <th>hadoop-mapreduce-client-core-2.7.2.jar/mapred-default.xml</th>
    </tr>    
</table>

> - 自定义配置文件:只有用户想修改某一默认配置值时,才需要修改自定义配置文件,更改相应属性值
>  - core-site.xml,hdfs-site.xml,yarn-site.xml,mapred-site.xml四个配置文件存放在$HADOOP_HOME/etc/hadoop这个路径上,用户可以根据项目需求重新进行修改配置

