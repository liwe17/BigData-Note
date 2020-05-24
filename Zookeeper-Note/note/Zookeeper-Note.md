# 大数据技术之Zookeeper
## 第一章 Zookeeper入门
### 1.1 概述
> Zookeeper是一个开源的分布式的,为分布式应用提供协调服务的Apache项目.

![zookeeper工作机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxNQnSgMJlouqQAraUUA9FOKfybPfVag0vI66Fh9C6R5ahGbmRthKR3g/0?wx_fmt=png)

### 1.2 特点

![zookeeper特点](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxYkvSmwjJsKRrH5GcocCBtTVLQ0LI4Z0YfibzaW9YBWiaUI3tqq5wpsYg/0?wx_fmt=png)

### 1.3 数据结构

![zookeeper数据结构](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxVykbxHxIxTSiawNdq6ov9mhnDBXKB7GAMGGOC92HmaOzBTX8n0UdVCA/0?wx_fmt=png)

### 1.4 应用场景

> 提供的服务包括
> - 统一命名服务
> - 统一配置管理
> - 统一集群管理
> - 服务器节点动态上下线
> - 软负载均衡等...

![统一命名服务](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxpbu1XFuLkj3P4FqNlib9IFNse5xHM64B5NAHv2azNwhV7iaft8T5OmyQ/0?wx_fmt=png)

![统一配置管理](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxLNbaeKZUtAbZ5Cp1Hl93TljhZA6peZFPULn61n54o9JJe8Ve3qD1aw/0?wx_fmt=png)

![统一集群管理](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxlQev5rnfBY0liat2WNiae6fBamQmby21KWwyO1BROqjv7sU2tl4K02nQ/0?wx_fmt=png)

![服务器节点动态上下线](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbx6q1zIhBxNrU48lQE6gsJaAbMdhSXibrIJS0b8QMbjZXORdkzMDEacMQ/0?wx_fmt=png)

![软负载均衡](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxcuvWHryibG2Zmmgjzg48TUgcUV3ia6fn0gBPxxF5Vw3YKliaRUjrv9SbA/0?wx_fmt=png)

### 1.5 下载地址

> 官网地址: https://zookeeper.apache.org/

## 第二章 Zookeeper安装
### 2.1 本地模式安装部署

1. 安装前准备

> - 安装JDK
> - 拷贝Zookeeper安装包到Linux系统下,解压到指定目录

```shell script
[root@li software]# ll
总用量 64968
-rw-r--r-- 1 root root  5635116 5月  22 2017 apache-ant-1.9.9-bin.tar.gz
-rw-r--r-- 1 root root  5144659 3月  16 2017 apache-maven-3.0.5-bin.tar.gz
-rw-r--r-- 1 root root 18290860 3月  28 2017 hadoop-2.7.2-src.tar.gz
-rw-r--r-- 1 root root  2401901 5月  22 2017 protobuf-2.5.0.tar.gz
-rw-r--r-- 1 root root 35042811 6月  22 2017 zookeeper-3.4.10.tar.gz
[root@li software]# pwd
/opt/software
[root@li software]# tar -zxvf zookeeper-3.4.10.tar.gz -C /opt/module/
```

2. 配置修改

> - 将/opt/module/zookeeper-3.4.10/conf这个路径下的zoo_sample.cfg修改为zoo.cfg
> - 打开zoo.cfg文件,修改dataDir路径
> - 在/opt/module/zookeeper-3.4.10/这个目录上创建zkData文件夹

```shell script
[root@li conf]# ll
总用量 12
-rw-rw-r-- 1 1001 1001  535 3月  23 2017 configuration.xsl
-rw-rw-r-- 1 1001 1001 2161 3月  23 2017 log4j.properties
-rw-rw-r-- 1 1001 1001  922 3月  23 2017 zoo_sample.cfg
[root@li conf]# pwd
/opt/module/zookeeper-3.4.10/conf
[root@li conf]# mv zoo_sample.cfg zoo.cfg
[root@li conf]# mkdir -p /opt/module/zookeeper-3.4.10/zkData
```

3. 操作zookeeper

> - 启动zookeeper
> - 查看进程是否启动
> - 查看状态

```shell script
[root@li bin]# ls
README.txt  zkCleanup.sh  zkCli.cmd  zkCli.sh  zkEnv.cmd  zkEnv.sh  zkServer.cmd  zkServer.sh
[root@li bin]# pwd
/opt/module/zookeeper-3.4.10/bin
[root@li bin]# ./zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.4.10/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
[root@li bin]# jps
1105 QuorumPeerMain
1170 Jps
[root@li bin]# ./zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.4.10/bin/../conf/zoo.cfg
Mode: standalone
[root@li bin]#  
```

> - 启动客户端
> - 退出客户端
> - 停止zookeeper

```shell script
[root@li bin]# ./zkCli.sh 
Connecting to localhost:2181
[zk: localhost:2181(CONNECTED) 0] quit
[root@li bin]# ./zkServer.sh stop
ZooKeeper JMX enabled by default
Using config: /opt/module/zookeeper-3.4.10/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED
[root@li bin]# 
```

### 2.2 配置参数解读

> Zookeeper中的配置文件zoo.cfg中参数含义
> - tickTime=2000:通信心跳数,Zookeeper服务器与客户端心跳时间,单位毫秒,代表2秒一次,用于心跳机制,并且设置最小的session超时时间为两倍心跳时间
> - initLimit=10:LF初始通信时限,集群中的Follower跟随者服务器与Leader领导者服务器之间初始连接时能容忍的最多心跳数(tickTime的数量),用它来限定集群中的Zookeeper服务器连接到Leader的时限
> - syncLimit=5:LF同步通信时限,集群中Leader与Follower之间的最大响应时间单位,假如响应超过syncLimit * tickTime,Leader认为Follower死掉,从服务器列表中删除Follower
> - dataDir:数据文件目录+数据持久化路径
> - clientPort=2181:客户端连接端口

## 第三章 zookeeper内部原理
### 3.1 选举机制-面试重点

>  半数机制:集群中半数以上机器存活,集群可用,所以zookeeper适合安装在奇数台服务器

>  zookeeper虽然配置文件中没有指定master和slave,但是zookeeper工作时,是有一个节点leader,其他为follower,leader是内部选举机制临时产生的

> 选举的过程: <br> 假设有五台服务器组成的zookeeper集群,他们的id从1-5,同时它们都是最新启动的,也就是没有历史数据,在存放数据量这一点都一样,假设这些服务器依次启动.

![leader选举过程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxSwKxm1ERuDOcZgYyGXBPQlDOWKcicO60ltpibjYoS3fryKVALYyHpWPw/0?wx_fmt=png)

> - 服务器1启动,此时只有它一台服务器启动了,它发出的报文没有任何响应,所以它的选举状态是looking
> - 服务器2启动,它与服务器1进行通信,互相交换自己的选举结果,由于都无历史数据,所以id值较大的服务器2胜出,但是由于没有达到半数以上(3台)都选举它,所以服务器1,2还是继续保持looking
> - 服务器3启动,根据前面分析,服务器3成为服务器1,2,3种老大,所以它成为了这次选举的leader
> - 服务器4启动,根据前面分析,理论上服务器4应该是服务器1,2,3,,4中最大的,但是由于已经有半数以上选举了服务器3,因此服务器4只能当follower
> - 服务器5启动,同服务器4一样.


### 3.2 节点类型

![节点类型](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxG9jI5v5eic93TYSEkFicibpEx76rg274ML2zhemptPviagr7N5h0ezovsg/0?wx_fmt=png)

### 3.3 stat结构体

> - czxid-创建节点的事务zxid
>   - 每次修改zookeeper状态都会收到一个zxid形式的时间戳,也就是zookeeper事务id
>   - 事务id是zookeeper中所有修改总的次序,每个修改都有唯一的zxid,如果zxid1小于zxid2,那么zxid1在zxid2之前发生
> - ctime-znode:被创建的毫秒数(从1970年开始)
> - mzxid-znode:最后跟新的zxid
> - mtime-znode:最后修改的毫秒数(从1970年开始)
> - pzxid-znode:最后跟新的子节点zxid
> - cversion-znode:子节点变化号,znode子节点修改次数
> - dataversion-znode:数据变化号
> - aclversion-znode:访问控制列表变化号
> - ephemeralowner-如果是临时节点,这个znode是拥有者的session_id,如果不是临时节点则是0
> - datalength-znode的数据长度
> - numchildren-znode子节点数量

### 3.4 监听器原理-面试重点

![监听器原理](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxicekos3kVyrmK8HUicJmE14uA0uR1EhGz7YnEhrKJLCnu1lHlwnpr5Yw/0?wx_fmt=png)

### 3.5 写数据流程

![写数据流程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxjpticQ3FX1H7FuhUdzK7rDOZTZSnIdHVK2hy1HA8TeqOj7QzHmpBqdg/0?wx_fmt=png)

## 第四章 zookeeper实战(开发重点)
### 4.1 分布式安装部署

1. 集群规划:在hadoop100,hadoop101和hadoop102三个节点上部署Zookeeper
2. 解压安装,并同步到其他机器

```shell script
[root@hadoop100 software]# ls
hadoop-2.7.2.tar.gz  jdk-8u144-linux-x64.tar.gz  zookeeper-3.4.10.tar.gz
[root@hadoop100 software]# tar -zxvf zookeeper-3.4.10.tar.gz -C /opt/module/
[root@hadoop100 software]# xsync zookeeper-3.4.10/
```

3. 配置服务器编号,3台服务器myid分别为0,1,2,必须和后面service.id一致

```shell script
[root@hadoop100 zookeeper-3.4.10]# mkdir -p zkData
[root@hadoop100 zookeeper-3.4.10]# touch myid
[root@hadoop100 zookeeper-3.4.10]# vi myid
```

4. 配置zoo.cfg,并同步配置文件

> - 重命名/opt/module/zookeeper-3.4.10/conf这个目录下的zoo_sample.cfg为zoo.cfg
> - 打开zoo.cfg文件,修改数据存储路径配置并增加配置
> - 同步zoo.cfg配置文件

```shell script
[root@hadoop100 zookeeper-3.4.10]# cd conf && mv zoo_sample.cfg zoo.cfg
[root@hadoop102 conf]# vi zoo.cfg
dataDir=/opt/module/zookeeper-3.4.10/zkData
#######################cluster##########################
server.0=hadoop100:2888:3888
server.1=hadoop101:2888:3888
server.2=hadoop102:2888:3888
~
"zoo.cfg" 33L, 1111C written
[root@hadoop102 conf]# xsync zoo.cfg
```

> - 配置参数解读,server.A=B:C:D
>   - A是一个数字，表示这个是第几号服务器
>     - 集群模式下配置一个文件myid,这个文件在dataDir目录下,这个文件里面有一个数据就是A的值,Zookeeper启动时读取此文件，拿到里面的数据与zoo.cfg里面的配置信息比较从而判断到底是哪个server
>   - B是这个服务器的ip地址
>   - C是这个服务器与集群中的Leader服务器交换信息的端口
>   - D是万一集群中的Leader服务器挂了,需要一个端口来重新进行选举,选出一个新的Leader,而这个端口就是用来执行选举时服务器相互通信的端口


5. 集群的操作<3台服务器均需要执行以下命令>

> - zookeeper的启动
> - zookeeper的查看
> - zookeeper的关闭

```shell script
[root@hadoop101 zookeeper-3.4.10]# bin/zkServer.sh start
[root@hadoop101 zookeeper-3.4.10]# bin/zkServer.sh status
[root@hadoop101 zookeeper-3.4.10]# bin/zkServer.sh stop
```

### 4.2 客户端命令行操作

<table>
    <tr>
        <th>命令基本语法</th>
        <th>功能描述</th>
    </tr>
    <tr>
        <th>help</th>
        <th>显示所有操作命令</th>
    </tr>
    <tr>
        <th>ls path[watch]</th>
        <th>使用ls命令来查看当前znode中所包含的内容</th>
    </tr>
    <tr>
        <th>ls2 path[watch]</th>
        <th>查看当前节点数据并能看到更新次数等数据</th>
    </tr>
    <tr>
        <th>create</th>
        <th>普通创建<br>-s 含有序列<br>-e 临时(重启或者超时消失)</th>
    </tr>
    <tr>
        <th>get path[watch]</th>
        <th>获得节点的值</th>
    </tr>
    <tr>
        <th>set</th>
        <th>设置节点的具体值</th>
    </tr>
    <tr>
        <th>stat</th>
        <th>查看节点状态</th>
    </tr>
    <tr>
        <th>delete</th>
        <th>删除节点</th>
    </tr>
    <tr>
        <th>rmr</th>
        <th>递归删除节点</th>
    </tr>
</table>


### 4.3 API应用




## 第五章 企业面试真题
### 5.1 zookeeper的部署方式有几种?集群中的角色有哪些?集群最少需要几台机器?
> - 部署方式:单机/集群
> - 角色:Leader和Follower
> - 集群最少需要机器数:3

### 5.2 常用命令
> ls/create/get/delete/set...

