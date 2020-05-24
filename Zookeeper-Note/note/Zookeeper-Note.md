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

1. 启动客户端
2. 显示所有操作命令
3. 查看当前znode中所包含的内容
4. 查看当前节点详细数据

```shell script
[root@hadoop102 zookeeper-3.4.10]# pwd
/opt/module/zookeeper-3.4.10
[root@hadoop102 zookeeper-3.4.10]# jps
6973 Jps
6911 QuorumPeerMain
[root@hadoop102 zookeeper-3.4.10]# bin/zkCli.sh 
[zk: localhost:2181(CONNECTED) 0] help
ZooKeeper -server host:port cmd args
        stat path [watch]
        set path data [version]
        ls path [watch]
        delquota [-n|-b] path
        ls2 path [watch]
        setAcl path acl
        setquota -n|-b val path
        history 
        redo cmdno
        printwatches on|off
        delete path [version]
        sync path
        listquota path
        rmr path
        get path [watch]
        create [-s] [-e] path data acl
        addauth scheme auth
        quit 
        getAcl path
        close 
        connect host:port
[zk: localhost:2181(CONNECTED) 1]  
[zk: localhost:2181(CONNECTED) 1] ls /
[zookeeper]
[zk: localhost:2181(CONNECTED) 2] ls2 /
[zookeeper]
cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0x0
cversion = -1
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 1
[zk: localhost:2181(CONNECTED) 3]

```

5. 分别创建2个普通节点
6. 获取节点值

```shell script
[zk: localhost:2181(CONNECTED) 5] create /sanguo "jinlian"
Created /sanguo
[zk: localhost:2181(CONNECTED) 6] create /sanguo/shuguo "liubei" 
Created /sanguo/shuguo
[zk: localhost:2181(CONNECTED) 7] get /sanguo
jinlian
cZxid = 0x200000004
ctime = Sun May 24 18:49:21 CST 2020
mZxid = 0x200000004
mtime = Sun May 24 18:49:21 CST 2020
pZxid = 0x200000005
cversion = 1
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 7
numChildren = 1
[zk: localhost:2181(CONNECTED) 8] 
```

7. 创建短暂节点

> - 当前客户端可以看到
> - 退出当前客户端在重启客户端
> - 再次查看短暂节点已经删除
```shell script
[zk: localhost:2181(CONNECTED) 10] create -e /sanguo/wuguo "zhouyu"
Created /sanguo/wuguo
[zk: localhost:2181(CONNECTED) 11] ls /sanguo
[wuguo, shuguo]
[zk: localhost:2181(CONNECTED) 12] quit
[root@hadoop102 zookeeper-3.4.10]# bin/zkCli.sh 
[zk: localhost:2181(CONNECTED) 0] ls /sanguo
[shuguo]
[zk: localhost:2181(CONNECTED) 1] quit
[root@hadoop102 zookeeper-3.4.10]# bin/zkCli.sh 
[zk: localhost:2181(CONNECTED) 0] ls /sanguo
[shuguo]
```

8. 创建带序号的节点

> 如果原来没有序号节点,节点号从0开始依次递增,如果已经有两个节点,则从2开始,依次类推

> - 先创建一个普通的根节点
> - 创建带序号的节点

```shell script
[zk: localhost:2181(CONNECTED) 2] create /sanguo/weiguo "caocao"
Created /sanguo/weiguo
[zk: localhost:2181(CONNECTED) 3] create -s /sanguo/weiguo/xiaoqiao "jinlian"
Created /sanguo/weiguo/xiaoqiao0000000000
[zk: localhost:2181(CONNECTED) 4] create -s /sanguo/weiguo/daqiao  "jinlian" 
Created /sanguo/weiguo/daqiao0000000001
[zk: localhost:2181(CONNECTED) 5] create -s /sanguo/weiguo/diaochan  "jinlian"
Created /sanguo/weiguo/diaochan0000000002
[zk: localhost:2181(CONNECTED) 6] 
```

9. 修改节点数据值

```shell script
[zk: localhost:2181(CONNECTED) 6] set /sanguo/weiguo "simayi"
cZxid = 0x20000000b
ctime = Sun May 24 18:57:34 CST 2020
mZxid = 0x20000000f
mtime = Sun May 24 19:00:15 CST 2020
pZxid = 0x20000000e
cversion = 3
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 6
numChildren = 3
[zk: localhost:2181(CONNECTED) 7] ls /sanguo/weiguo
[xiaoqiao0000000000, diaochan0000000002, daqiao0000000001]
[zk: localhost:2181(CONNECTED) 8] get /sanguo/weiguo
simayi
cZxid = 0x20000000b
ctime = Sun May 24 18:57:34 CST 2020
mZxid = 0x20000000f
mtime = Sun May 24 19:00:15 CST 2020
pZxid = 0x20000000e
cversion = 3
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 6
numChildren = 3
[zk: localhost:2181(CONNECTED) 9] 
```

10. 节点值变化监听

> - 在hadoop100主机上注册监听/sanguo节点数据变化
> - 在hadoop101主机上修改/sanguo节点数据
> - 观察hadoop100主机收到的数据变化监听

```shell script
# hadoop100
[zk: localhost:2181(CONNECTED) 29] get /sanguo watch                   
xisi
cZxid = 0x200000004
ctime = Sun May 24 18:49:21 CST 2020
mZxid = 0x200000014
mtime = Sun May 24 19:05:53 CST 2020
pZxid = 0x20000000b
cversion = 4
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 4
numChildren = 2
[zk: localhost:2181(CONNECTED) 30] 

# hadoop101
[zk: localhost:2181(CONNECTED) 2] set /sanguo "xishi"
cZxid = 0x200000004
ctime = Sun May 24 18:49:21 CST 2020
mZxid = 0x200000015
mtime = Sun May 24 19:07:45 CST 2020
pZxid = 0x20000000b
cversion = 4
dataVersion = 2
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 5
numChildren = 2
[zk: localhost:2181(CONNECTED) 3] 


# hadoop100
[zk: localhost:2181(CONNECTED) 30] 
WATCHER::

WatchedEvent state:SyncConnected type:NodeDataChanged path:/sanguo
[zk: localhost:2181(CONNECTED) 30] 

```

11. 节点的子节点变化监听(路径变化)

> - 在hadoop100主机注册监听/sanguo节点的子节点变化
> - 在hadoop101主机/sanguo节点上创建子节点
> - 在hadoop100主机收到子节点的变化的监听

```shell script
# hadoop100

[zk: localhost:2181(CONNECTED) 30] ls /sanguo watch
[shuguo, weiguo]

#hadoop101
[zk: localhost:2181(CONNECTED) 3] create /sanguo/jin "simayi"
Created /sanguo/jin
[zk: localhost:2181(CONNECTED) 4] 

# hadoop100
[zk: localhost:2181(CONNECTED) 31] 
WATCHER::

WatchedEvent state:SyncConnected type:NodeChildrenChanged path:/sanguo

[zk: localhost:2181(CONNECTED) 31] 
```

12. 删除节点

```shell script
[zk: localhost:2181(CONNECTED) 4] delete /sanguo/jin
```

13. 递归删除几点

```shell script
[zk: localhost:2181(CONNECTED) 5] ls /sanguo/shuguo
[]
[zk: localhost:2181(CONNECTED) 7] rmr /sanguo/shuguo
[zk: localhost:2181(CONNECTED) 8] ls /sanguo/shuguo 
Node does not exist: /sanguo/shuguo
[zk: localhost:2181(CONNECTED) 9] 
```

14. 查看节点状态

```shell script
[zk: localhost:2181(CONNECTED) 9] stat /sanguo
cZxid = 0x200000004
ctime = Sun May 24 18:49:21 CST 2020
mZxid = 0x200000015
mtime = Sun May 24 19:07:45 CST 2020
pZxid = 0x200000018
cversion = 7
dataVersion = 2
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 5
numChildren = 1
[zk: localhost:2181(CONNECTED) 10] 
```

### 4.3 API应用

#### 4.3.1 环境搭建

1. 创建maven工程
2. 添加pom依赖
3. 拷贝log4j.properties文件到resources目录

```text
Zookeeper-Demo zookeeper学习项目
```

#### 4.3.2 创建zookeeper客户端

```text
private static String CONNECT_ADDR = "hadoop100:2181,hadoop101:2181,hadoop102:2181";
private static int SESSION_TIMEOUT = 2000;
private ZooKeeper zkClient;

//1. 创建Zookeeper客户端
@Before
public void init() throws Exception{
    zkClient = new ZooKeeper(CONNECT_ADDR, SESSION_TIMEOUT, event -> {
        //收到事件通知后的回调函数(用户的业务逻辑)
        System.out.println(event.getType()+"--"+event.getPath());
        //再次启动监听
        try {
            zkClient.getChildren("/", true).forEach(System.err::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}
```

#### 4.3.3 创建子节点

```text
//2. 创建节点
    @Test
    public void createNode() throws Exception{
        final String path = zkClient.create("/atguigu2", "dagezuishuai".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }
```

#### 4.3.4 获取子节点并监听节点变化

```text
//3. 获取子节点,并监控节点变化
@Test
public void getChildren() throws Exception{
    List<String> children = zkClient.getChildren("/", true);
    children.forEach(System.out::println);
    //延时阻塞
    TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
}
```


#### 4.3.5 判断znode是否存在

```text
//4. 判断节点Znode是否岑在
@Test
public void exist() throws Exception{
    Stat stat = zkClient.exists("/eclipse", false);
    System.out.println(null == stat? "not exist" : "exist");
}

```

### 4.4 监听服务器节点动态上下线案例

1. 需求

> - 某分布式系统,主节点可以有多台,可以动态上下线,任意一台客户端都能实时感知到节点服务器的上下线

2. 需求分析

![服务器动态上下线案例分析](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2cCECYQgCHQaGBslYowDbxwwibOiboQ86VjiamofGZwG2gGOIdhT0pqbK8iaHftLGLREhia5lk4vS4aUw/0?wx_fmt=png)


3. 具体实现

> - 先在集群上创建/servers节点

```shell script
[zk: localhost:2181(CONNECTED) 0] create /servers "servers"
Created /servers
[zk: localhost:2181(CONNECTED) 1] 
```

> - 服务器端向Zookeeper注册代码

```java
public class DistributeServer {

    private static String CONNECT_ADDR = "hadoop100:2181,hadoop101:2181,hadoop102:2181";
    private static int SESSION_TIMEOUT = 2000;
    private ZooKeeper zkClient;
    private String parentNode = "/servers";


    public static void main(String[] args){
        //1. 获取zk连接
        final DistributeServer server = new DistributeServer();
        //2. 利用zk连接注册服务器信息
        server.registerServer(args[0]);
        //3. 启动业务功能
        server.business(args[0]);
    }


    public void getConnect(){
        zkClient = new ZooKeeper(CONNECT_ADDR,SESSION_TIMEOUT,event -> {});
    }

    public void registerServer(String hostname){
        String create = zkClient.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+" is online "+create);
    }

    public void business(String hostname){
        System.out.println(hostname+" is working ...");

        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }
}

```

> - 客户端代码

```java
public class DistributeClient {

    private static String CONNECT_ADDR = "hadoop100:2181,hadoop101:2181,hadoop102:2181";

    private static int SESSION_TIMEOUT = 2000;

    private ZooKeeper zkClient;

    private String parentNode = "/servers";

    public static void main(String[] args) {

        //1. 获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnect();

        //2. 获取servers的子节点信息,从中获取服务器信息列表
        client.getServerList();

        //3. 业务进程启动
        client.business();
    }

    // 创建连接
    public void getConnect(){
        zkClient = new ZooKeeper(CONNECT_ADDR,SESSION_TIMEOUT,event -> {
            //启动监听
            try {
                getServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 获取服务器列表
    public void getServerList(){

        //1. 获取服务器子节点信息,并且对父节点进行监听
        List<String> children = zkClient.getChildren(parentNode, true);

        //2. 存储服务器信息列表
        children.stream().map(e-> {
            try {
                return new String((zkClient.getData(parentNode + "/" + e, false, null)));
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()).forEach(System.out::println);

    }

    // 业务功能
    public void business(){
        System.out.println("client is working ...");
        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }

}
```

## 第五章 企业面试真题
### 5.1 zookeeper的部署方式有几种?集群中的角色有哪些?集群最少需要几台机器?
> - 部署方式:单机/集群
> - 角色:Leader和Follower
> - 集群最少需要机器数:3

### 5.2 常用命令
> ls/create/get/delete/set...

