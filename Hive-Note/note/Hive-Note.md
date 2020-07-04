# 大数据技术之Hive
## 第一章 Hive基本概念
### 1.1 什么是Hive

> Hive:由Facebook开源于解决海量结构化日志的数据统计

> Hive是基于Hadoop的一个数据仓库工具,可以将结构化的数据文件映射为一张表,并提供类SQL查询功能

> 本质是:将HQL转化为MapReduce程序

![SQL转MapReduce](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GrEONzaeOMd0LVBgjqqpMEHQbUkh6WU4X2PlseJzKXiaDB5mDRroQj8HMQLOiaSVPhx4zubUricxiag/0?wx_fmt=png)

> - Hive处理的数据存储在HDFS
> - Hive分析数据底层的实现是MapReduce
> - 执行程序运行在Yarn上

### 1.2 Hive的优缺点
#### 1.2.1 优点

> - 操作接口采用类SQL语法,提供快速开发的能力(简单,容易上手)
> - 避免了去写MapReduce,减少开发人员学习成本
> - Hive的执行延迟比较高,因此Hive常用于数据分析,对实时性要求不高的场合
> - Hive优势在于处理大数据,对于处理小数据没有优势,因为Hive的执行延迟比较高
> - Hive支持用户自定义函数,用户可以根据自己的需求来实现自己的函数

#### 1.2.2 缺点

1. Hive的HQL表达能力有限

> 迭代式算法无法表达
> 数据挖掘方面不擅长

2. Hive的效率比较低

> Hive自动生成MapReduce作业,通常情况下不够智能化
> Hive调优比较困难,粒度较粗

### 1.3 Hive架构原理

![Hive架构原理](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GrEONzaeOMd0LVBgjqqpM9a2pGFIhZgO1TFWmSjXWN2pk3u37Sjamia0GKicEu6CgyO02E1wIFBGg/0?wx_fmt=png)

> - 用户接口:Client
>   - CLI(Hive shell),JDBC/ODBC(java访问hive),WEBUI(浏览器访问hive)
> - 元数据:Metastore
>   - 元数据包括:表名,表所属的数据库(默认是default),表的拥护者,列/分区字段,表的类型(是否是外部表),表的数据所在的目录等;
>   - 默认存储在自带的derby数据库中,推荐使用MySql存储Metastore
> - Hadoop
>   - 使用HDFS进行存储,使用MapReduce进行计算
> - 驱动器:Driver
>   - 解析器(SQL Parser):将SQL字符串转换成抽象语法树AST,这一步一般都用第三方工具库完成,比如antlr;对AST进行语法分析,比如表是否存在,字段是否存在,SQL语义是否有误
>   - 编译器(Physical Plan):将AST编译生成逻辑执行计划
>   - 执行器(Execution):把逻辑执行计划转换成可以运行的物理计划,对于Hive来说,就是MR/Spark

![hive运行机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q2GrEONzaeOMd0LVBgjqqpMJma2ib8BYsxsibRibicbFMjvkOtDC3icIJWItu1yccWDd0vic6uWmpPvalog/0?wx_fmt=png)

> Hive通过给用户提供的一系列交互接口,接收到用户的指令(SQL),使用自己的Driver,结合元数据(MetaStore),将这些指令翻译成MapReduce,提交到Hadoop中执行,最后,将执行返回的结果输出到用户交互接口    

### 1.4 Hive和数据库比较

> 由于Hive采用了类似SQL的查询语言HQL(Hive Query Language),因此很容易将Hive理解为数据库,其实从结构上来看,Hive和数据库除了拥有类似的查询语言,再无类似之处,本文将从多个方面来阐述Hive和数据库的差异,数据库可以用在Online的应用中,但是Hive是为数据仓库而设计的,清楚这一点,有助于从应用角度理解Hive的特性

#### 1.4.1 查询语言

> 由于SQL被广泛的应用在数据仓库中,因此,专门针对Hive的特性设计了类SQL的查询语言HQL.熟悉SQL开发的开发者可以很方便的使用Hive进行开发

#### 1.4.2 数据存储位置

> Hive 是建立在Hadoop之上的,所有Hive的数据都是存储在HDFS中的.而数据库则可以将数据保存在块设备或者本地文件系统中

#### 1.4.3 数据更新

> 由于Hive是针对数据仓库应用设计的,而数据仓库的内容是读多写少的.因此,Hive中不建议对数据的改写,所有的数据都是在加载的时候确定好的.而数据库中的数据通常是需要经常进行修改的,因此可以使用INSERT INTO...VALUES添加数据,使用UPDATE...SET修改数据

#### 1.4.4 索引

> Hive在加载数据的过程中不会对数据进行任何处理,甚至不会对数据进行扫描,因此也没有对数据中的某些Key建立索引.Hive要访问数据中满足条件的特定值时,需要暴力扫描整个数据,因此访问延迟较高.由于MapReduce的引入,Hive可以并行访问数据,因此即使没有索引,对于大数据量的访问,Hive仍然可以体现出优势.数据库中,通常会针对一个或者几个列建立索引,因此对于少量的特定条件的数据的访问,数据库可以有很高的效率,较低的延迟.由于数据的访问延迟较高,决定了Hive不适合在线数据查询

#### 1.4.5 执行

> Hive中大多数查询的执行是通过Hadoop提供的MapReduce来实现的.而数据库通常有自己的执行引擎

#### 1.4.6 执行延迟

> Hive在查询数据的时候,由于没有索引,需要扫描整个表,因此延迟较高.另外一个导致Hive执行延迟高的因素是MapReduce框架.由于MapReduce本身具有较高的延迟,因此在利用MapReduce执行Hive查询时,也会有较高的延迟.相对的,数据库的执行延迟较低.当然,这个低是有条件的,即数据规模较小,当数据规模大到超过数据库的处理能力的时候,Hive的并行计算显然能体现出优势

#### 1.4.7 可扩展性

> 由于Hive是建立在Hadoop之上的,因此Hive的可扩展性是和Hadoop的可扩展性是一致的(世界上最大的Hadoop集群在Yahoo!,2009年的规模在4000台节点左右).而数据库由于ACID语义的严格限制,扩展行非常有限.目前最先进的并行数据库Oracle在理论上的扩展能力也只有100台左右

#### 1.4.8 数据规模

> 由于Hive建立在集群上并可以利用MapReduce进行并行计算,因此可以支持很大规模的数据;对应的,数据库可以支持的数据规模较小

## 第二章 Hive安装
### 2.1 Hive安装地址

1. Hive官网地址:http://hive.apache.org/
2. 文档查看地址:https://cwiki.apache.org/confluence/display/Hive/GettingStarted
3. 下载地址:http://archive.apache.org/dist/hive/
4. github地址:https://github.com/apache/hive 

### 2.2 Hive安装部署

1. Hive安装及配置

> - 把apache-hive-1.2.1-bin.tar.gz上传到linux的/opt/software目录下
> - 解压apache-hive-1.2.1-bin.tar.gz到/opt/module/目录下面
> - 修改apache-hive-1.2.1-bin.tar.gz的名称为hive

```shell script
[root@hadoop100 software]# pwd
/opt/software
[root@hadoop100 software]# ls
apache-hive-1.2.1-bin.tar.gz  hadoop-2.7.2.tar.gz  jdk-8u144-linux-x64.tar.gz  mysql-libs.zip  zookeeper-3.4.10.tar.gz
[root@hadoop100 software]# tar -zxvf apache-hive-1.2.1-bin.tar.gz -C /opt/module/
[root@hadoop100 software]# cd ../software/
[root@hadoop100 software]# mv apache-hive-1.2.1-bin/ hive
```

> - 修改/opt/module/hive/conf目录下的hive-env.sh.template名称为hive-env.sh
> - 配置hive-env.sh文件
>   - 配置HADOOP_HOME路径
>   - 配置HIVE_CONF_DIR路径

```shell script
[root@hadoop100 hive]# cd conf/
[root@hadoop100 conf]# ll
总用量 188
-rw-rw-r--. 1 root root   1139 4月  30 2015 beeline-log4j.properties.template
-rw-rw-r--. 1 root root 168431 6月  19 2015 hive-default.xml.template
-rw-rw-r--. 1 root root   2378 4月  30 2015 hive-env.sh.template
-rw-rw-r--. 1 root root   2662 4月  30 2015 hive-exec-log4j.properties.template
-rw-rw-r--. 1 root root   3050 4月  30 2015 hive-log4j.properties.template
-rw-rw-r--. 1 root root   1593 4月  30 2015 ivysettings.xml
[root@hadoop100 conf]# mv hive-env.sh.template hive-env.sh
[root@hadoop100 conf]# vi hive-env.sh 
export HADOOP_HOME=/opt/module/hadoop-2.7.2
export HIVE_CONF_DIR=/opt/module/hive/conf
"hive-env.sh" 56L, 2465C written
[root@hadoop100 conf]# 
```

2. Hadoop集群配置

> - 必须启动hdfs和yarn
> - 在HDFS上创建/tmp和/user/hive/warehouse两个目录并修改他们的同组权限可写

```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/start-dfs.sh
[root@hadoop101 hadoop-2.7.2]# sbin/start-yarn.sh
[root@hadoop101 hadoop-2.7.2]#
[root@hadoop102 hadoop-2.7.2]# bin/hadoop fs -mkdir /tmp
[root@hadoop102 hadoop-2.7.2]# bin/hadoop fs -mkdir -p /user/hive/warehouse
[root@hadoop102 hadoop-2.7.2]# bin/hadoop fs -chmod g+w /tmp
[root@hadoop102 hadoop-2.7.2]# bin/hadoop fs -chmod g+w /user/hive/warehouse
[root@hadoop102 hadoop-2.7.2]# 

```

3. Hive基本操作

> - 启动hive
> - 查看数据库
> - 打开默认数据库
> - 显示默认数据库中的表
> - 创建表
> - 显示数据库中又几张表
> - 查看表结构
> - 表中插入数据
> - 查询表中数据
> - 退出hive

```shell script
[root@hadoop100 hive]# bin/hive
Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-1.2.1.jar!/hive-log4j.properties
hive> show databases;
hive> use default;
hive> show tables;
hive> create table student(id int, name string);
hive> show tables;
hive> desc student;                                 
hive> insert into student values(1000,"ss");
hive> select * from student;
hive> quit
```

### 2.3 将本地文件导入Hive案例

> 需求:将本地/opt/module/datas/student.txt这个目录下的数据导入到hive的student(id int, name string)表中

1. 数据准备

> - 在/opt/module/datas这个目录下准备数据,注意以tab键间隔

```shell script
[root@hadoop100 hive]# mkdir -p /opt/module/datas && cd /opt/module/datas && touch student.txt
[root@hadoop100 datas]# vi student.txt 
1001    zhangsan
1002    lishi
1003    zhaoliu
"student.txt" 3L, 38C written
[root@hadoop100 datas]# 
```

2. Hive实际操作

> - 启动hive
> - 显示数据库
> - 使用default数据库
> - 删除已创建的student表
> - 创建student表,,并声明文件分隔符'\t'
> - 加载/opt/module/datas/student.txt 文件到student数据库表中
> - Hive查询结果

```shell script
[root@hadoop100 hive]# bin/hive
hive> show databases;
hive> use default;
hive> show tables;
hive> drop table student;
hive> create table student(id int, name string) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';
hive> load data local inpath '/opt/module/datas/student.txt' into table student;
hive>  select * from student;
OK
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.189 seconds, Fetched: 3 row(s)
hive> 
```

3. 遇到的问题

> 再打开一个客户端窗口启动hive，会产生java.sql.SQLException异常 <br>
> 原因是Metastore默认存储在自带的derby数据库中,推荐使用MySQL存储Metastore;

### 2.4 MySql安装

#### 2.4.1 安装包准备

> - 查看mysql是否安装,如果安装了,卸载mysql
> - 解压mysql-libs.zip文件到当前目录
> - 进入到mysql-libs文件夹下

```shell script
[root@hadoop100 software]# rpm -qa |grep mysql
[root@hadoop100 software]# rpm -e --nodeps mysql包名
[root@hadoop100 software]# unzip mysql-libs.zip
[root@hadoop100 mysql-libs]# ls
MySQL-client-5.6.24-1.el6.x86_64.rpm  MySQL-server-5.6.24-1.el6.x86_64.rpm
mysql-connector-java-5.1.27.tar.gz
[root@hadoop100 mysql-libs]# 
```

#### 2.4.2 安装MySql服务器

> - 安装mysql服务端
> - 查看产生的随机密码
> - 查看mysql状态
> - 启动mysql

```shell script
[root@hadoop100 mysql-libs]# rpm -ivh MySQL-server-5.6.24-1.el6.x86_64.rpm
[root@hadoop100 mysql-libs]# cat /root/.mysql_secret
# The random password set for the root user at Wed May 27 17:09:00 2020 (local time): B0g0iCUnvYWj7_gx

[root@hadoop100 mysql-libs]# service mysql status
 ERROR! MySQL is not running
[root@hadoop100 mysql-libs]# service mysql start
Starting MySQL.. SUCCESS! 
```

#### 2.4.3 安装MySql客户端

> - 安装mysql客户端
> - 链接mysql
> - 修改密码
> - 退出mysql

```shell script
[root@hadoop100 mysql-libs]# rpm -ivh MySQL-client-5.6.24-1.el6.x86_64.rpm
[root@hadoop100 mysql-libs]# mysql -uroot -pB0g0iCUnvYWj7_gx
Warning: Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 1
Server version: 5.6.24

Copyright (c) 2000, 2015, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> SET PASSWORD=PASSWORD('123456');
Query OK, 0 rows affected (0.00 sec)

mysql> exit;
Bye
[root@hadoop100 mysql-libs]# 
```

#### 2.4.4 MySql中user表中主机配置

> 配置只要是root用户+密码,在任何主机上都能登录MySQL数据库
> - 进入mysql
> - 显示数据库
> - 使用mysql数据库
> - 展示mysql数据库中的所有表
> - 展示user表的结构
> - 查询user表
> - 修改user表,把Host表内容修改为%
> - 删除root用户的其他host
> - 刷新并退出

```shell script
[root@hadoop100 mysql-libs]# mysql -uroot -p123456
Warning: Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 2
Server version: 5.6.24 MySQL Community Server (GPL)

Copyright (c) 2000, 2015, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| test               |
+--------------------+
4 rows in set (0.00 sec)

mysql> use mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables;
+---------------------------+
| Tables_in_mysql           |
+---------------------------+
| columns_priv              |
| db                        |
| event                     |
| func                      |
| general_log               |
| help_category             |
| help_keyword              |
| help_relation             |
| help_topic                |
| innodb_index_stats        |
| innodb_table_stats        |
| ndb_binlog_index          |
| plugin                    |
| proc                      |
| procs_priv                |
| proxies_priv              |
| servers                   |
| slave_master_info         |
| slave_relay_log_info      |
| slave_worker_info         |
| slow_log                  |
| tables_priv               |
| time_zone                 |
| time_zone_leap_second     |
| time_zone_name            |
| time_zone_transition      |
| time_zone_transition_type |
| user                      |
+---------------------------+
28 rows in set (0.00 sec)

mysql> desc user;
+------------------------+-----------------------------------+------+-----+-----------------------+-------+
| Field                  | Type                              | Null | Key | Default               | Extra |
+------------------------+-----------------------------------+------+-----+-----------------------+-------+
| Host                   | char(60)                          | NO   | PRI |                       |       |
| User                   | char(16)                          | NO   | PRI |                       |       |
| Password               | char(41)                          | NO   |     |                       |       |
| Select_priv            | enum('N','Y')                     | NO   |     | N                     |       |
| Insert_priv            | enum('N','Y')                     | NO   |     | N                     |       |
| Update_priv            | enum('N','Y')                     | NO   |     | N                     |       |
| Delete_priv            | enum('N','Y')                     | NO   |     | N                     |       |
| Create_priv            | enum('N','Y')                     | NO   |     | N                     |       |
| Drop_priv              | enum('N','Y')                     | NO   |     | N                     |       |
| Reload_priv            | enum('N','Y')                     | NO   |     | N                     |       |
| Shutdown_priv          | enum('N','Y')                     | NO   |     | N                     |       |
| Process_priv           | enum('N','Y')                     | NO   |     | N                     |       |
| File_priv              | enum('N','Y')                     | NO   |     | N                     |       |
| Grant_priv             | enum('N','Y')                     | NO   |     | N                     |       |
| References_priv        | enum('N','Y')                     | NO   |     | N                     |       |
| Index_priv             | enum('N','Y')                     | NO   |     | N                     |       |
| Alter_priv             | enum('N','Y')                     | NO   |     | N                     |       |
| Show_db_priv           | enum('N','Y')                     | NO   |     | N                     |       |
| Super_priv             | enum('N','Y')                     | NO   |     | N                     |       |
| Create_tmp_table_priv  | enum('N','Y')                     | NO   |     | N                     |       |
| Lock_tables_priv       | enum('N','Y')                     | NO   |     | N                     |       |
| Execute_priv           | enum('N','Y')                     | NO   |     | N                     |       |
| Repl_slave_priv        | enum('N','Y')                     | NO   |     | N                     |       |
| Repl_client_priv       | enum('N','Y')                     | NO   |     | N                     |       |
| Create_view_priv       | enum('N','Y')                     | NO   |     | N                     |       |
| Show_view_priv         | enum('N','Y')                     | NO   |     | N                     |       |
| Create_routine_priv    | enum('N','Y')                     | NO   |     | N                     |       |
| Alter_routine_priv     | enum('N','Y')                     | NO   |     | N                     |       |
| Create_user_priv       | enum('N','Y')                     | NO   |     | N                     |       |
| Event_priv             | enum('N','Y')                     | NO   |     | N                     |       |
| Trigger_priv           | enum('N','Y')                     | NO   |     | N                     |       |
| Create_tablespace_priv | enum('N','Y')                     | NO   |     | N                     |       |
| ssl_type               | enum('','ANY','X509','SPECIFIED') | NO   |     |                       |       |
| ssl_cipher             | blob                              | NO   |     | NULL                  |       |
| x509_issuer            | blob                              | NO   |     | NULL                  |       |
| x509_subject           | blob                              | NO   |     | NULL                  |       |
| max_questions          | int(11) unsigned                  | NO   |     | 0                     |       |
| max_updates            | int(11) unsigned                  | NO   |     | 0                     |       |
| max_connections        | int(11) unsigned                  | NO   |     | 0                     |       |
| max_user_connections   | int(11) unsigned                  | NO   |     | 0                     |       |
| plugin                 | char(64)                          | YES  |     | mysql_native_password |       |
| authentication_string  | text                              | YES  |     | NULL                  |       |
| password_expired       | enum('N','Y')                     | NO   |     | N                     |       |
+------------------------+-----------------------------------+------+-----+-----------------------+-------+
43 rows in set (0.00 sec)

mysql> select User, Host, Password from user;
+------+-----------+-------------------------------------------+
| User | Host      | Password                                  |
+------+-----------+-------------------------------------------+
| root | localhost | *6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9 |
| root | hadoop100 | *DC33C7DE36DD7C3AF22D09FCED61E255358080C1 |
| root | 127.0.0.1 | *DC33C7DE36DD7C3AF22D09FCED61E255358080C1 |
| root | ::1       | *DC33C7DE36DD7C3AF22D09FCED61E255358080C1 |
+------+-----------+-------------------------------------------+
4 rows in set (0.00 sec)

mysql> delete from user where Host='hadoop100';
Query OK, 1 row affected (0.00 sec)

mysql> delete from user where Host='127.0.0.1';
Query OK, 1 row affected (0.00 sec)

mysql> delete from user where Host='::1';
Query OK, 1 row affected (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)

mysql> quit;
Bye
[root@hadoop100 mysql-libs]# 

```

### 2.5 Hive元数据配置到MySql

#### 2.5.1 驱动拷贝

> 1. 在/opt/software/mysql-libs目录下解压mysql-connector-java-5.1.27.tar.gz驱动包
> 2. 拷贝/opt/software/mysql-libs/mysql-connector-java-5.1.27目录下的mysql-connector-java-5.1.27-bin.jar到/opt/module/hive/lib/

```shell script
[root@hadoop100 mysql-libs]# tar -zxvf mysql-connector-java-5.1.27.tar.gz
[root@hadoop100 mysql-connector-java-5.1.27]# cp mysql-connector-java-5.1.27-bin.jar  /opt/module/hive/lib/
```

#### 2.5.2 配置Metastore到MySql

> - 在/opt/module/hive/conf目录下创建一个hive-site.xml
> - 根据官方文档配置参数，拷贝数据到hive-site.xml文件中
> - 配置完毕后,如果启动hive异常,可以重新启动虚拟机.(重启后,别忘了启动hadoop集群)

```shell script
[root@hadoop100 conf]# touch hive-site.xml && vi hive-site.xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
        <property>
          <name>javax.jdo.option.ConnectionURL</name>
          <value>jdbc:mysql://hadoop100:3306/metastore?createDatabaseIfNotExist=true</value>
          <description>JDBC connect string for a JDBC metastore</description>
        </property>

        <property>
          <name>javax.jdo.option.ConnectionDriverName</name>
          <value>com.mysql.jdbc.Driver</value>
          <description>Driver class name for a JDBC metastore</description>
        </property>

        <property>
          <name>javax.jdo.option.ConnectionUserName</name>
          <value>root</value>
          <description>username to use against metastore database</description>
        </property>

        <property>
          <name>javax.jdo.option.ConnectionPassword</name>
          <value>123456</value>
          <description>password to use against metastore database</description>
        </property>
</configuration>
~
~
"hive-site.xml" 28L, 884C written
[root@hadoop100 conf]# 

```

#### 2.5.3 多窗口启动Hive测试

> - 先启动MySQL
> - 再次打开多个窗口,分别启动hive
> - 启动hive后,回到MySQL窗口查看数据库,显示增加了metastore数据库

```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/start-dfs.sh 
[root@hadoop101 hadoop-2.7.2]# sbin/start-yarn.sh

[root@hadoop100 hive]# bin/hive
Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-1.2.1.jar!/hive-log4j.properties
hive> 

[root@hadoop100 hive]# bin/hive

Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-1.2.1.jar!/hive-log4j.properties
hive> 

[root@hadoop100 hadoop-2.7.2]# mysql -uroot -p123456
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| metastore          |
| mysql              |
| performance_schema |
| test               |
+--------------------+
5 rows in set (0.01 sec)

mysql> 
```


### 2.6 HiveJDBC访问

#### 2.6.1 启动hiveserver2服务

```shell script
[root@hadoop100 hive]# bin/hiveserver2
```

#### 2.6.2 启动beeline

```shell script
[root@hadoop100 hive]#  bin/beeline
Beeline version 1.2.1 by Apache Hive
beeline>
```

#### 2.6.3 连接hiveserver2

```shell script
[root@hadoop100 hive]# bin/beeline 
Beeline version 1.2.1 by Apache Hive
beeline> !connect jdbc:hive2://hadoop100:10000
Connecting to jdbc:hive2://hadoop100:10000
Enter username for jdbc:hive2://hadoop100:10000: root
Enter password for jdbc:hive2://hadoop100:10000: 
Connected to: Apache Hive (version 1.2.1)
Driver: Hive JDBC (version 1.2.1)
Transaction isolation: TRANSACTION_REPEATABLE_READ
0: jdbc:hive2://hadoop100:10000> show databases;
+----------------+--+
| database_name  |
+----------------+--+
| default        |
+----------------+--+
1 row selected (0.419 seconds)
0: jdbc:hive2://hadoop100:10000> 

```

### 2.7 Hive常用交互命令

> - 交互命令

```shell script
[root@hadoop100 hive]# bin/hive -help
usage: hive
 -d,--define <key=value>          Variable subsitution to apply to hive
                                  commands. e.g. -d A=B or --define A=B
    --database <databasename>     Specify the database to use
 -e <quoted-query-string>         SQL from command line
 -f <filename>                    SQL from files
 -H,--help                        Print help information
    --hiveconf <property=value>   Use value for given property
    --hivevar <key=value>         Variable subsitution to apply to hive
                                  commands. e.g. --hivevar A=B
 -i <filename>                    Initialization SQL file
 -S,--silent                      Silent mode in interactive shell
 -v,--verbose                     Verbose mode (echo executed SQL to the
                                  console)
[root@hadoop100 hive]# 
```

> - "-e"不进入hive的交互窗口执行sql语句

```shell script
[root@hadoop100 hive]# bin/hive -e "select * from student";
Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-1.2.1.jar!/hive-log4j.properties
OK
1001    zhangsan
1002    lishi
1003    zhaoliu
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.997 seconds, Fetched: 6 row(s)
```

> - "-f"执行脚本中sql语句
>   - 在/opt/module/datas目录下创建hivef.sql文件
>   - 执行文件中的sql语句
>   - 执行文件中的sql语句并将结果写入文件中

```shell script
[root@hadoop100 hive]# cd ../datas/
[root@hadoop100 datas]# ls
student.txt
[root@hadoop100 datas]# touch hivef.sql && echo 'select *from student;' > hivef.sql && cat hivef.sql
select *from student;
[root@hadoop100 datas]# cd -
/opt/module/hive
[root@hadoop100 hive]# bin/hive -f /opt/module/datas/hivef.sql > /opt/module/datas/hive_result.txt 

Logging initialized using configuration in jar:file:/opt/module/hive/lib/hive-common-1.2.1.jar!/hive-log4j.properties
OK
Time taken: 0.991 seconds, Fetched: 6 row(s)
[root@hadoop100 hive]# cat /opt/module/datas/hive_result.txt 
1001    zhangsan
1002    lishi
1003    zhaoliu
1001    zhangsan
1002    lishi
1003    zhaoliu
[root@hadoop100 hive]# 
```

### 2.8 Hive其他命令操作

> - 退出hive窗口
>
```shell script
hive> quit;
hive> exit;
```

> - 在hive cli命令窗口中如何查看hdfs文件系统
> - 在hive cli命令窗口中如何查看本地文件系统

```shell script
hive> dfs -ls /;
Found 6 items
-rw-r--r--   3 root supergroup       1366 2020-05-04 00:07 /README.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:25 /kongming.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:25 /kongming1.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:25 /kongming2.txt
drwxrwxr-x   - root supergroup          0 2020-05-26 22:13 /tmp
drwxr-xr-x   - root supergroup          0 2020-05-26 22:08 /user
hive> ! ls /opt;
module
software
hive>
```

> - 查看在hive中输入的所有历史命令
>   - 进入到当前用户的根目录/root
>   - 查看.hivehistory文件

```shell script
[root@hadoop100 hive]# cd ~            
[root@hadoop100 ~]# cat .hivehistory 
```


### 2.9 Hive常见属性配置
#### 2.9.1 Hive数据仓库位置配置

> - Default数据仓库的最原始位置是在hdfs上的:/user/hive/warehouse路径下
> - 在仓库目录下,没有对默认的数据库default创建文件夹.如果某张表属于default数据库,直接在数据仓库目录下创建一个文件夹
> - 修改default数据仓库原始位置(将hive-default.xml.template如下配置信息拷贝到hive-site.xml文件中

```text
<property>
    <name>hive.metastore.warehouse.dir</name>
    <value>/user/hive/warehouse</value>
    <description>location of default database for the warehouse</description>
</property>

#加权限
bin/hdfs dfs -chmod g+w /user/hive/warehouse
```

#### 2.9.2 查询后信息显示配置

> - 在hive-site.xml文件中添加如下配置信息,就可以实现显示当前数据库,以及查询表的头信息配置

```text
<property>
	<name>hive.cli.print.header</name>
	<value>true</value>
</property>

<property>
	<name>hive.cli.print.current.db</name>
	<value>true</value>
</property>
```

> - 重新启动hive,对比配置前后差异

```shell script
hive (default)> select * from student;
OK
student.id      student.name
1001    zhangsan
1002    lishi
1003    zhaoliu
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.46 seconds, Fetched: 6 row(s)
hive (default)> 
```

#### 2.9.3 Hive运行日志信息配置

> - Hive的log默认存放在/tmp/root/hive.log目录下(当前用户名下)
> - 修改hive的log存放日志到/opt/module/hive/logs
>   - 修改/opt/module/hive/conf/hive-log4j.properties.template文件名称为hive-log4j.properties
>   - 在hive-log4j.properties文件中修改log存放位置

```shell script
[root@hadoop100 conf]# pwd
/opt/module/hive/conf
[root@hadoop100 conf]# mv hive-log4j.properties.template hive-log4j.properties
[root@hadoop100 conf]# vi hive-log4j.properties 
#hive.log.dir=${java.io.tmpdir}/${user.name}
hive.log.dir=/opt/module/hive/logs

"hive-log4j.properties" 89L, 3086C written
[root@hadoop100 conf]# 
```

#### 2.9.4 参数配置方式

> - 查看当前所有的配置信息

```shell script
[root@hadoop100 hive]# bin/hive

Logging initialized using configuration in file:/opt/module/hive/conf/hive-log4j.properties
hive (default)> set;

```

> - 参数的配置三种方式
>   - 配置文件方式
>       - 默认配置文件:hive-default.xml
>       - 用户自定义配置文件:hive-site.xml
>       - 注意:用户自定义配置会覆盖默认配置.另外,Hive也会读入Hadoop的配置,因为Hive是作为Hadoop的客户端启动的,Hive的配置会覆盖Hadoop的配置,配置文件的设定对本机启动的所有Hive进程都有效
>   - 命令行参数方式
>       - 启动Hive时,可以在命令行添加-hiveconf param=value来设定参数
>       - 注意:仅对本次hive启动有效

```shell script
[root@hadoop100 hive]# bin/hive -hiveconf mapred.reduce.tasks=10;
hive (default)> set mapred.reduce.tasks;
```

>   - 参数声明方式
        - 可以在HQL中使用SET关键字设定参数
>       - 注意:仅对本次hive启动有效。
```shell script
[root@hadoop100 hive]# bin/hive
hive (default)> set mapred.reduce.tasks=100;
hive (default)> set mapred.reduce.tasks;
```

## 第三章 Hive数据类型
### 3.1 基本数据类型

<table>
    <tr>
        <th>Hive数据类型</th>
        <th>Java数据类型</th>
        <th>长度</th>
        <th>例子</th>
    </tr>
    <tr>
        <th>TINYINT</th>
        <th>byte</th>
        <th>1byte有符号整数</th>
        <th>10</th>
    </tr>
    <tr>
        <th>SMALINT</th>
        <th>short</th>
        <th>2byte有符号整数</th>
        <th>10</th>
    </tr>
    <tr>
        <th>INT</th>
        <th>int</th>
        <th>4byte有符号整数</th>
        <th>10</th>
    </tr>
    <tr>
        <th>BIGINT</th>
        <th>long</th>
        <th>8byte有符号整数</th>
        <th>10</th>
    </tr>
    <tr>
        <th>BOOLEAN</th>
        <th>boolean</th>
        <th>布尔类型,true或者false</th>
        <th>TRUE/FALSE</th>
    </tr>
    <tr>
        <th>FLOAT</th>
        <th>float</th>
        <th>单精度浮点数</th>
        <th>3.14159</th>
    </tr>
    <tr>
        <th>DOUBLE</th>
        <th>double</th>
        <th>双精度浮点数</th>
        <th>3.14159</th>
    </tr>
    <tr>
        <th>STRING</th>
        <th>String</th>
        <th>字符系列,可以指定字符集,可以使用单引号或者双引号</th>
        <th>'now is the time' "for all good men"</th>
    </tr>
    <tr>
        <th>TIMESTAMP</th>
        <th></th>
        <th>时间类型</th>
        <th></th>
    </tr>
    <tr>
        <th>BINARY</th>
        <th></th>
        <th>字节数组</th>
        <th></th>
    </tr>
</table>

> 对于Hive的String类型相当于数据库的varchar类型,该类型是一个可变的字符串,不过它不能声明其中最多能存储多少个字符,理论上它可以存储2GB的字符数

### 3.2 集合数据类型

<table>
    <tr>
        <th>数据类型</th>
        <th>描述</th>
        <th>语法示例</th>
    </tr>
    <tr>
        <th>STRUCT</th>
        <th>和c语言中的struct类似,都可以通过"点"符号访问元素内容.例如,如果某个列的数据类型是STRUCT{first STRING,last STRING},那么第1个元素可以通过字段.first来引用</th>
        <th>struct()</th>
    </tr>
    <tr>
        <th>MAP</th>
        <th>MAP是一组键-值对元组集合,使用数组表示法可以访问数据.例如,如果某个列的数据类型是MAP,其中键->值对是'first'->'John'和'last'->'Doe',那么可以通过字段名['last']获取最后一个元素</th>
        <th>map()</th>
    </tr>
    <tr>
        <th>ARRAY</th>
        <th>数组是一组具有相同类型和名称的变量的集合.这些变量称为数组的元素,每个数组元素都有一个编号,编号从零开始.例如,数组值为['John','Doe'],那么第2个元素可以通过数组名[1]进行引用</th>
        <th>array()</th>
    </tr>
</table>

> Hive有三种复杂数据类型ARRAY,MAP和STRUCT
>   - ARRAY和MAP与Java中的Array和Map类似
>   - STRUCT与C语言中的Struct类似它封装了一个命名字段集合,复杂数据类型允许任意层次的嵌套

> 案例
> - 假设某表有如下一行,我们用JSON格式来表示其数据结构.在Hive下访问的格式如下,我们可以将friends作为数组,children作为map,地址作为结构Struct.

```json
{
    "name":"songsong",
    "friends":["bingbing", "lili"],
    "children":{
        "xiao song":18,
        "xiaoxiao song":19
    },
    "address":{
        "street":"hui long guan",
        "city":"beijing" 
    }
}
```

> - 基于上述数据结构,创建本地测试文件test

```text
songsong,bingbing_lili,xiao song:18_xiaoxiao song:19,hui long guan_beijing
yangyang,caicai_susu,xiao yang:18_xiaoxiao yang:19,chao yang_beijing
```

> - Hive上创建测试表test,导入文本数据到测试表

```hiveql
create table test (
    name string,
    friends array<string>,
    children map<string,string>,
    address struct<street:string,city:string>
)
row format delimited fields terminated by ","   -- 列分隔符
collection items terminated by "_"  -- MAP STRUCT 和 ARRAY 的分隔符(数据分割符号)
map keys terminated by ":"  -- MAP中的key与value的分隔符 
lines terminated by "\n";   -- 行分隔符

load data local inpath "/opt/module/datas/test.txt" into table test;
```

> - 访问三种集合列里的数据,以下分别是ARRAY,MAP,STRUCT的访问方式

```shell script
[root@hadoop100 hive]# 
[root@hadoop100 hive]# bin/hive

Logging initialized using configuration in file:/opt/module/hive/conf/hive-log4j.properties
hive (default)> drop table test;
OK
Time taken: 0.936 seconds
hive (default)> create table test (
              >     name string,
              >     friends array<string>,
              >     children map<string,string>,
              >     address struct<street:string,city:string>
              > )
              > row format delimited fields terminated by ","
              > collection items terminated by "_"
              > map keys terminated by ":"
              > lines terminated by "\n";
OK
Time taken: 0.263 seconds
hive (default)> load data local inpath "/opt/module/datas/test.txt" into table test;
Loading data to table default.test
Table default.test stats: [numFiles=1, totalSize=144]
OK
Time taken: 0.646 seconds
hive (default)> 
hive (default)> select * from test;
OK
test.name       test.friends    test.children   test.address
songsong        ["bingbing","lili"]     {"xiao song":"18","xiaoxiao song":"19"} {"street":"hui long guan","city":"beijing"}
yangyang        ["caicai","susu"]       {"xiao yang":"18","xiaoxiao yang":"19"} {"street":"chao yang","city":"beijing"}
Time taken: 0.923 seconds, Fetched: 2 row(s)
hive (default)> select name,friends[0],children['xiao song'],address.street from test;
OK
name    _c1     _c2     street
songsong        bingbing        18      hui long guan
yangyang        caicai  NULL    chao yang
Time taken: 0.073 seconds, Fetched: 2 row(s)
hive (default)> 
```

### 3.3 类型转化

>　Hive的原子数据类型是可以进行隐式转换的,类似于Java的类型转换.
>   - 例如某表达式使用INT类型,TINYINT会自动转换为INT类型,但是Hive不会进行反向转化.
>   - 例如某表达式使用TINYINT类型,INT不会自动转换为TINYINT类型,它会返回错误,除非使用CAST操作.

1. 隐式类型转换规则

> - 任何整数类型都可以隐式地转换为一个范围更广的类型,如TINYINT可以转换成INT,INT可以转换成BIGINT
> - 所有整数类型,FLOAT和STRING类型都可以隐式地转换成DOUBLE
> - TINYINT,SMALLINT,INT都可以转换为FLOAT
> - BOOLEAN类型不可以转换为任何其它的类型

2. 可以使用CAST操作显示进行数据类型转换

> - CAST('1' AS INT)将把字符串'1'转换成整数1.
> - 如果强制类型转换失败,如执行CAST('X' AS INT),表达式返回空值NULL

## 第四章 DDL数据定义
### 4.1 创建数据库

> 查看相关文件路径:http://hadoop100:50070

> - 创建一个数据库,数据库在HDFS上的默认存储路径是/user/hive/warehouse/*.db

```shell script
hive (default)> create database db_hive;
OK
Time taken: 0.084 seconds
```

> - 避免要创建的数据库已经存在错误,增加if not exists判断(标准写法)

```shell script
hive (default)> create database db_hive;
FAILED: Execution Error, return code 1 from org.apache.hadoop.hive.ql.exec.DDLTask. Database db_hive already exists
hive (default)> create database if not exists db_hive;
OK
Time taken: 0.079 seconds
hive (default)> 
hive (default)> show databases;
OK
database_name
db_hive
default
Time taken: 0.131 seconds, Fetched: 2 row(s)
hive (default)> 
```

> - 创建一个数据库,指定数据库在HDFS上存放的位置

```shell script
hive (default)> create database if not exists db_hive2 location '/db_hive2.db';
OK
Time taken: 0.179 seconds
```

### 4.2 查询数据库
#### 4.2.1 显示数据库

> 显示数据库,过滤显示数据库

```shell script
hive (default)> show databases;
OK
database_name
db_hive
db_hive2
default
Time taken: 0.634 seconds, Fetched: 3 row(s)
hive (default)> show databases like 'db_hive*';
OK
database_name
db_hive
db_hive2
Time taken: 0.017 seconds, Fetched: 2 row(s)
hive (default)> 
```

#### 4.2.2 查看数据库详情

> 显示数据库信息,显示数据库详细信息extended

```shell script
hive (default)> desc database db_hive;
OK
db_name comment location        owner_name      owner_type      parameters
db_hive         hdfs://hadoop100:9000/user/hive/warehouse/db_hive.db    root    USER
Time taken: 0.019 seconds, Fetched: 1 row(s)
hive (default)> desc database extended  db_hive ;
OK
db_name comment location        owner_name      owner_type      parameters
db_hive         hdfs://hadoop100:9000/user/hive/warehouse/db_hive.db    root    USER
Time taken: 0.023 seconds, Fetched: 1 row(s)
hive (default)> 
```

#### 4.3.3 切换当前数据库

```shell script
hive (default)> use db_hive;
```

#### 4.3 修改数据库

> 用户可以使用ALTER DATABASE命令为某个数据库的dbproperties设置键-值对属性值,来描述这个数据库的属性信息<br>
> 数据库的其他元数据信息都是不可更改的,包括数据库名和数据库所在的目录位置.

```shell script
hive (default)> desc database extended  db_hive ;
OK
db_name comment location        owner_name      owner_type      parameters
db_hive         hdfs://hadoop100:9000/user/hive/warehouse/db_hive.db    root    USER
Time taken: 0.023 seconds, Fetched: 1 row(s)
hive (default)> 
hive (default)> desc database extended  db_hive ;
OK
db_name comment location        owner_name      owner_type      parameters
db_hive         hdfs://hadoop100:9000/user/hive/warehouse/db_hive.db    root    USER    {createtime=20200608}
Time taken: 0.022 seconds, Fetched: 1 row(s)
hive (default)> 
```

#### 4.4 删除数据库

> - 删除空数据库
````shell script
hive (default)> show databases;
OK
database_name
db_hive
db_hive2
default
Time taken: 0.015 seconds, Fetched: 3 row(s)
hive (default)> drop database db_hive2;
OK
Time taken: 0.099 seconds
hive (default)> show databases;
OK
database_name
db_hive
default
Time taken: 0.012 seconds, Fetched: 2 row(s)
hive (default)> 
````

> - 如果删除的数据库不存在,最好采用if exists判断数据库是否存在

```shell script
hive (default)> drop database db_hive2;
FAILED: SemanticException [Error 10072]: Database does not exist: db_hive2
hive (default)> drop database if exists  db_hive2;
OK
Time taken: 0.011 seconds
hive (default)> 
```

> - 如果数据库不为空,可以采用cascade命令,强制删除

```shell script
hive (default)> create database db_hive2;
OK
Time taken: 0.113 seconds
hive (default)> use db_hive2;
OK
Time taken: 0.009 seconds
hive (db_hive2)> create table test(id int);
OK
Time taken: 0.169 seconds
hive (db_hive2)> drop database db_hive2;
FAILED: Execution Error, return code 1 from org.apache.hadoop.hive.ql.exec.DDLTask. InvalidOperationException(message:Database db_hive2 is not empty. One or more tables exist.)
hive (db_hive2)> drop database db_hive2 cascade;
OK
Time taken: 0.271 seconds
hive (db_hive2)> 
```

#### 4.5 创建表

> 建表语法

```text
CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name 
[(col_name data_type [COMMENT col_comment], ...)] 
[COMMENT table_comment] 
[PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)] 
[CLUSTERED BY (col_name, col_name, ...) 
[SORTED BY (col_name [ASC|DESC],...)] INTO num_buckets BUCKETS] 
[ROW FORMAT row_format] 
[STORED AS file_format] 
[LOCATION hdfs_path]
```

> 字段解释说明
> - CREATE TABLE 创建一个指定名字的表.
>   - 如果相同名字的表已经存在,则抛出异常,用户可以用 IF NOT EXISTS 选项来忽略这个异常.
> - EXTERNAL关键字可以让用户创建一个外部表,在建表的同时指定一个指向实际数据的路径(LOCATION)
>   - Hive创建内部表时,会将数据移动到数据仓库指向的路径;若创建外部表,仅记录数据所在的路径,不对数据的位置做任何改变
>   - 在删除表的时候,内部表的元数据和数据会被一起删除,而外部表只删除元数据,不删除数据
> - COMMENT 表和列添加注释
> - PARTITIONED BY创建分区表
> - CLUSTERED BY创建分桶表
> - SORTED BY不常用
> - ROW FORMAT
>   - DELIMITED 
>       - [FIELDS TERMINATED BY char]
>       - [COLLECTION ITEMS TERMINATED BY char]
>       - [MAP KEYS TERMINATED BY char] 
>       - [LINES TERMINATED BY char] 
>   - SERDE serde_name
>       - [WITH SERDEPROPERTIES (property_name=property_value, property_name=property_value, ...)]
>       - 用户在建表的时候可以自定义SerDe或者使用自带的SerDe,如果没有指定ROW FORMAT或者ROW FORMAT DELIMITED,将会使用自带的SerDe
>       - 在建表的时候,用户还需要为表指定列,用户在指定表的列的同时也会指定自定义的SerDe,Hive通过SerDe确定表的具体的列的数据
>       - SerDe是Serialize/Deserilize的简称,目的是用于序列化和反序列化
> - STORED AS指定存储文件类型
>   - SEQUENCEFILE(二进制序列文件) 如果数据需要压缩,使用 STORED AS SEQUENCEFILE
>   - TEXTFILE(文本),如果文件数据是纯文本,可以使用STORED AS TEXTFILE
>   - RCFILE(列式存储格式文件)
> - LOCATION:指定表在HDFS上的存储位置
> - LIKE允许用户复制现有的表结构,但是不复制数据.

##### 4.5.1 管理表

> 理论
> - 默认创建的表都是所谓的管理表,有时也被称为内部表.因为这种表,Hive会(或多或少地)控制着数据的生命周期.
> - Hive默认情况下会将这些表的数据存储在由配置项hive.metastore.warehouse.dir(例如/user/hive/warehouse)所定义的目录的子目录下,	
> - 当我们删除一个管理表时,Hive也会删除这个表中数据.管理表不适合和其他工具共享数据

> 案例

```shell script
hive (default)> select * from student;
OK
student.id      student.name
1001    zhangsan
1002    lishi
1003    zhaoliu
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.302 seconds, Fetched: 6 row(s)
hive (default)> 
```

> - 创建表

```hiveql
create table if not exists student2 (
id int,name string
)
row format delimited fields terminated by '\t'
stored as textfile 
location '/user/hive/warehouse/student2';
```

> - 根据查询结果创建表(查询的结果会添加到新创建的表中)

```shell script
hive (default)> create table if not exists student3 as select id, name from student;
hive (default)> select * from students;
FAILED: SemanticException [Error 10001]: Line 1:14 Table not found 'students'
hive (default)> select * from student3;
OK
student3.id     student3.name
1001    zhangsan
1002    lishi
1003    zhaoliu
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.056 seconds, Fetched: 6 row(s)
hive (default)> 
```

> - 根据已经存在的表结构创建表

```shell script
hive (default)> create table if not exists student4 like student;
OK
Time taken: 0.08 seconds
hive (default)> 
```

> - 查询表的类型

```shell script
hive (default)> desc formatted student3;
OK
col_name        data_type       comment
# col_name              data_type               comment             
                 
id                      int                                         
name                    string                                      
                 
# Detailed Table Information             
Database:               default                  
Owner:                  root                     
CreateTime:             Mon Jun 08 21:44:28 CST 2020     
LastAccessTime:         UNKNOWN                  
Protect Mode:           None                     
Retention:              0                        
Location:               hdfs://hadoop100:9000/user/hive/warehouse/student3       
Table Type:             MANAGED_TABLE            
Table Parameters:                
        COLUMN_STATS_ACCURATE   true                
        numFiles                1                   
        numRows                 6                   
        rawDataSize             70                  
        totalSize               76                  
        transient_lastDdlTime   1591623868          
                 
# Storage Information            
SerDe Library:          org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe       
InputFormat:            org.apache.hadoop.mapred.TextInputFormat         
OutputFormat:           org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat       
Compressed:             No                       
Num Buckets:            -1                       
Bucket Columns:         []                       
Sort Columns:           []                       
Storage Desc Params:             
        serialization.format    1                   
Time taken: 0.082 seconds, Fetched: 32 row(s)
hive (default)> 
```

##### 4.5.2 外部表

> 理论
> - 因为表是外部表,所以Hive并非认为其完全拥有这份数据.
> - 删除该表并不会删除掉这份数据,不过描述表的元数据信息会被删除掉

> 管理表和外部表的使用场景
> - 每天将收集到的网站日志定期流入HDFS文本文件.
>   - 在外部表(原始日志表)的基础上做大量的统计分析.
>   - 用到的中间表,结果表使用内部表存储,数据通过SELECT+INSERT进入内部表

> 案例
> - 创建部门和员工外部表,并向表中导入准备好的数据
>   - 准备好的数据data目录(dept.txt,emp.txt)
>   - 建表语句

```hiveql
create external table if not exists default.dept(
deptno int,
dname string,
loc int
)
row format delimited fields terminated by '\t';

create external table if not exists default.emp(
empno int,
ename string,
job string,
mgr int,
hiredate string, 
sal double, 
comm double,
deptno int)
row format delimited fields terminated by '\t';

```

>   - 查看创建的表

```shell script
[root@hadoop100 hadoop-2.7.2]# cd ../hive/
[root@hadoop100 hive]# bin/hive

Logging initialized using configuration in file:/opt/module/hive/conf/hive-log4j.properties
hive (default)> create external table if not exists default.dept(
              > deptno int,
              > dname string,
              > loc int
              > )
              > row format delimited fields terminated by '\t';
OK
Time taken: 0.945 seconds
hive (default)> create external table if not exists default.emp(
              > empno int,
              > ename string,
              > job string,
              > mgr int,
              > hiredate string, 
              > sal double, 
              > comm double,
              > deptno int)
              > row format delimited fields terminated by '\t';
OK
Time taken: 0.098 seconds
hive (default)> show tables;
OK
tab_name
dept
emp
student
student2
student3
student4
test
Time taken: 0.106 seconds, Fetched: 7 row(s)
hive (default)> 
```

>   - 向外部表中导入数据

```shell script
[root@hadoop100 hive]# bin/hive

Logging initialized using configuration in file:/opt/module/hive/conf/hive-log4j.properties
hive (default)> load data local inpath '/opt/module/datas/dept.txt' into table default.dept;
Loading data to table default.dept
Table default.dept stats: [numFiles=1, totalSize=68]
OK
Time taken: 1.434 seconds
hive (default)> load data local inpath '/opt/module/datas/emp.txt' into table default.emp;
Loading data to table default.emp
Table default.emp stats: [numFiles=1, totalSize=656]
OK
Time taken: 0.236 seconds
hive (default)> select * from emp;
OK
emp.empno       emp.ename       emp.job emp.mgr emp.hiredate    emp.sal emp.comm        emp.deptno
7369    SMITH   CLERK   7902    1980-12-17      800.0   NULL    20
7499    ALLEN   SALESMAN        7698    1981-2-20       1600.0  300.0   30
7521    WARD    SALESMAN        7698    1981-2-22       1250.0  500.0   30
7566    JONES   MANAGER 7839    1981-4-2        2975.0  NULL    20
7654    MARTIN  SALESMAN        7698    1981-9-28       1250.0  1400.0  30
7698    BLAKE   MANAGER 7839    1981-5-1        2850.0  NULL    30
7782    CLARK   MANAGER 7839    1981-6-9        2450.0  NULL    10
7788    SCOTT   ANALYST 7566    1987-4-19       3000.0  NULL    20
7839    KING    PRESIDENT       NULL    1981-11-17      5000.0  NULL    10
7844    TURNER  SALESMAN        7698    1981-9-8        1500.0  0.0     30
7876    ADAMS   CLERK   7788    1987-5-23       1100.0  NULL    20
7900    JAMES   CLERK   7698    1981-12-3       950.0   NULL    30
7902    FORD    ANALYST 7566    1981-12-3       3000.0  NULL    20
7934    MILLER  CLERK   7782    1982-1-23       1300.0  NULL    10
Time taken: 0.361 seconds, Fetched: 14 row(s)
hive (default)> select * from dept;
OK
dept.deptno     dept.dname      dept.loc
10      ACCOUNTING      1700
20      RESEARCH        1800
30      SALES   1900
40      OPERATIONS      1700
Time taken: 0.065 seconds, Fetched: 4 row(s)
hive (default)> 

```

>   - 查看表格式化数据

```shell script
hive (default)> desc formatted dept;
```

##### 4.5.3 管理表与外部表的互相转换

> - 查询表的类型


```shell script
hive (default)>  desc formatted student2;
OK
col_name        data_type       comment
# col_name              data_type               comment             
                 
id                      int                                         
name                    string                                      
                 
# Detailed Table Information             
Database:               default                  
Owner:                  root                     
CreateTime:             Mon Jun 08 21:43:07 CST 2020     
LastAccessTime:         UNKNOWN                  
Protect Mode:           None                     
Retention:              0                        
Location:               hdfs://hadoop100:9000/user/hive/warehouse/student2       
Table Type:             MANAGED_TABLE            
Table Parameters:                
        transient_lastDdlTime   1591623787          
                 
# Storage Information            
SerDe Library:          org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe       
InputFormat:            org.apache.hadoop.mapred.TextInputFormat         
OutputFormat:           org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat       
Compressed:             No                       
Num Buckets:            -1                       
Bucket Columns:         []                       
Sort Columns:           []                       
Storage Desc Params:             
        field.delim             \t                  
        serialization.format    \t                  
Time taken: 0.116 seconds, Fetched: 28 row(s)
hive (default)> 
```

> - 修改内部表student2为外部表
> - 查询表的类型

```shell script
Time taken: 0.116 seconds, Fetched: 28 row(s)
hive (default)> alter table student2 set tblproperties('EXTERNAL'='TRUE');
OK
Time taken: 0.077 seconds
hive (default)> 
hive (default)>  desc formatted student2;
OK
col_name        data_type       comment
# col_name              data_type               comment             
                 
id                      int                                         
name                    string                                      
                 
# Detailed Table Information             
Database:               default                  
Owner:                  root                     
CreateTime:             Mon Jun 08 21:43:07 CST 2020     
LastAccessTime:         UNKNOWN                  
Protect Mode:           None                     
Retention:              0                        
Location:               hdfs://hadoop100:9000/user/hive/warehouse/student2       
Table Type:             EXTERNAL_TABLE           
Table Parameters:                
        COLUMN_STATS_ACCURATE   false               
        EXTERNAL                TRUE                
        last_modified_by        root                
        last_modified_time      1591626106          
        numFiles                0                   
        numRows                 -1                  
        rawDataSize             -1                  
        totalSize               0                   
        transient_lastDdlTime   1591626106          
                 
# Storage Information            
SerDe Library:          org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe       
InputFormat:            org.apache.hadoop.mapred.TextInputFormat         
OutputFormat:           org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat       
Compressed:             No                       
Num Buckets:            -1                       
Bucket Columns:         []                       
Sort Columns:           []                       
Storage Desc Params:             
        field.delim             \t                  
        serialization.format    \t                  
Time taken: 0.061 seconds, Fetched: 36 row(s)
hive (default)> 
```

> - 修改外部表student2为内部表
> - 查询表的类型

```shell script
hive (default)> alter table student2 set tblproperties('EXTERNAL'='FALSE');
OK
Time taken: 0.081 seconds
hive (default)>  desc formatted student2;
OK
col_name        data_type       comment
# col_name              data_type               comment             
                 
id                      int                                         
name                    string                                      
                 
# Detailed Table Information             
Database:               default                  
Owner:                  root                     
CreateTime:             Mon Jun 08 21:43:07 CST 2020     
LastAccessTime:         UNKNOWN                  
Protect Mode:           None                     
Retention:              0                        
Location:               hdfs://hadoop100:9000/user/hive/warehouse/student2       
Table Type:             MANAGED_TABLE            
Table Parameters:                
        COLUMN_STATS_ACCURATE   false               
        EXTERNAL                FALSE               
        last_modified_by        root                
        last_modified_time      1591626157          
        numFiles                0                   
        numRows                 -1                  
        rawDataSize             -1                  
        totalSize               0                   
        transient_lastDdlTime   1591626157          
                 
# Storage Information            
SerDe Library:          org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe       
InputFormat:            org.apache.hadoop.mapred.TextInputFormat         
OutputFormat:           org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat       
Compressed:             No                       
Num Buckets:            -1                       
Bucket Columns:         []                       
Sort Columns:           []                       
Storage Desc Params:             
        field.delim             \t                  
        serialization.format    \t                  
Time taken: 0.049 seconds, Fetched: 36 row(s)
hive (default)> 
```

> 注意:('EXTERNAL'='TRUE')和('EXTERNAL'='FALSE')为固定写法,区分大小写

#### 4.6 分区表

> 分区表实际上就是对应一个HDFS文件系统上的独立的文件夹,该文件夹下是该分区所有的数据文件.
> - Hive中的分区就是分目录,把一个大的数据集根据业务需要分割成小的数据集.
> - 在查询时通过WHERE子句中的表达式选择查询所需要的指定的分区,这样的查询效率会提高很多

##### 4.6.1 分区表基本操作

> - 引入分区表(需要根据日期对日志进行管理)

```text
/user/hive/warehouse/log_partition/20170702/20170702.log
/user/hive/warehouse/log_partition/20170703/20170703.log
/user/hive/warehouse/log_partition/20170704/20170704.log
```

> - 创建分区表语法

```hiveql
create table if not exists dept_partition(
deptno int, dname string, loc string
)
partitioned by (month string)
row format delimited fields terminated by '\t';
```

> - 加载数据到分区表中

```shell script
hive (default)> create table if not exists dept_partition(
              > deptno int, dname string, loc string
              > )
              > partitioned by (month string)
              > row format delimited fields terminated by '\t';
OK
Time taken: 0.145 seconds
hive (default)> load data local inpath '/opt/module/datas/dept.txt' into table default.dept_partition partition(month='202001');
Loading data to table default.dept_partition partition (month=202001)
Partition default.dept_partition{month=202001} stats: [numFiles=1, numRows=0, totalSize=68, rawDataSize=0]
OK
Time taken: 0.349 seconds
hive (default)> load data local inpath '/opt/module/datas/dept.txt' into table default.dept_partition partition(month='202002');
Loading data to table default.dept_partition partition (month=202002)
Partition default.dept_partition{month=202002} stats: [numFiles=1, numRows=0, totalSize=68, rawDataSize=0]
OK
Time taken: 0.315 seconds
hive (default)> load data local inpath '/opt/module/datas/dept.txt' into table default.dept_partition partition(month='202003');
Loading data to table default.dept_partition partition (month=202003)
Partition default.dept_partition{month=202003} stats: [numFiles=1, numRows=0, totalSize=68, rawDataSize=0]
OK
Time taken: 0.268 seconds
hive (default)> 
```

> - 查询分区表中数据
>   - 单分区查询
>   - 多分区联合查询

```shell script
hive (default)> select *from dept_partition where month=202001;
dept_partition.deptno   dept_partition.dname    dept_partition.loc      dept_partition.month
10      ACCOUNTING      1700    202001
20      RESEARCH        1800    202001
30      SALES   1900    202001
40      OPERATIONS      1700    202001
hive (default)> select *from dept_partition where month=202001 union select * from dept_partition where month=202002;
_u2.deptno      _u2.dname       _u2.loc _u2.month
10      ACCOUNTING      1700    202001
10      ACCOUNTING      1700    202002
20      RESEARCH        1800    202001
20      RESEARCH        1800    202002
30      SALES   1900    202001
30      SALES   1900    202002
40      OPERATIONS      1700    202001
40      OPERATIONS      1700    202002
Time taken: 22.375 seconds, Fetched: 8 row(s)
hive (default)> 
```

> - 增加分区

```shell script
hive (default)> alter table dept_partition add partition(month='202004');
OK
Time taken: 0.163 seconds
hive (default)> alter table dept_partition add partition(month='202005') partition(month='202006');
OK
Time taken: 0.111 seconds
hive (default)> 
```

> - 删除分区

```shell script
hive (default)> alter table dept_partition drop partition (month='202004');
Dropped the partition month=202004
OK
Time taken: 0.285 seconds
hive (default)>
hive (default)> alter table dept_partition drop partition (month='202005'),partition(month='202006');
Dropped the partition month=202005
Dropped the partition month=202006
OK
Time taken: 0.138 seconds
hive (default)> 
```

> - 查看分区表有多少分区

```shell script
hive (default)> show partitions dept_partition;
OK
partition
month=202001
month=202002
month=202003
Time taken: 0.065 seconds, Fetched: 3 row(s)
hive (default)> 
```

> - 查看分区表结构

```shell script
hive (default)> desc formatted dept_partition;
OK
col_name        data_type       comment
# col_name              data_type               comment             
                 
deptno                  int                                         
dname                   string                                      
loc                     string                                      
                 
# Partition Information          
# col_name              data_type               comment             
                 
month                   string                                      
                 
# Detailed Table Information             
Database:               default                  
Owner:                  root                     
CreateTime:             Mon Jun 08 22:33:58 CST 2020     
LastAccessTime:         UNKNOWN                  
Protect Mode:           None                     
Retention:              0                        
Location:               hdfs://hadoop100:9000/user/hive/warehouse/dept_partition         
Table Type:             MANAGED_TABLE            
Table Parameters:                
        transient_lastDdlTime   1591626838          
                 
# Storage Information            
SerDe Library:          org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe       
InputFormat:            org.apache.hadoop.mapred.TextInputFormat         
OutputFormat:           org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat       
Compressed:             No                       
Num Buckets:            -1                       
Bucket Columns:         []                       
Sort Columns:           []                       
Storage Desc Params:             
        field.delim             \t                  
        serialization.format    \t                  
Time taken: 0.053 seconds, Fetched: 34 row(s)
hive (default)> 
```

##### 4.6.2 分区表注意事项

> - 创建二级分区

```hiveql
create table dept_partition2(
deptno int, dname string, loc string
)
partitioned by (month string, day string)
row format delimited fields terminated by '\t';
```

> - 正常的加载数据
>   - 加载数据到二级分区表
>   - 查询分区数据

```shell script
hive (default)> load data local inpath '/opt/module/datas/dept.txt' into table default.dept_partition2 partition(month='202001', day='13');
Loading data to table default.dept_partition2 partition (month=202001, day=13)
Partition default.dept_partition2{month=201709, day=13} stats: [numFiles=1, numRows=0, totalSize=68, rawDataSize=0]
OK
Time taken: 0.254 seconds
hive (default)> select * from dept_partition2 where month='201709' and day='13';
OK
dept_partition2.deptno  dept_partition2.dname   dept_partition2.loc     dept_partition2.month   dept_partition2.day
10      ACCOUNTING      1700    201709  13
20      RESEARCH        1800    201709  13
30      SALES   1900    201709  13
40      OPERATIONS      1700    201709  13
Time taken: 0.29 seconds, Fetched: 4 row(s)
hive (default)> 
```

> - 把数据直接上传到分区目录上,让分区表和数据产生关联的三种方式
>   - 方式一:上传数据后修复

```shell script
hive (default)> dfs -mkdir -p /user/hive/warehouse/dept_partition2/month=201709/day=12;
hive (default)> dfs -put /opt/module/datas/dept.txt  /user/hive/warehouse/dept_partition2/month=201709/day=12;
hive (default)> select * from dept_partition2 where month='201709' and day='12';
OK
dept_partition2.deptno  dept_partition2.dname   dept_partition2.loc     dept_partition2.month   dept_partition2.day
Time taken: 0.071 seconds
hive (default)> msck repair table dept_partition2;
OK
Partitions not in metastore:    dept_partition2:month=201709/day=12
Repair: Added partition to metastore dept_partition2:month=201709/day=12
Time taken: 0.116 seconds, Fetched: 2 row(s)
hive (default)> select * from dept_partition2 where month='201709' and day='12';
OK
dept_partition2.deptno  dept_partition2.dname   dept_partition2.loc     dept_partition2.month   dept_partition2.day
10      ACCOUNTING      1700    201709  12
20      RESEARCH        1800    201709  12
30      SALES   1900    201709  12
40      OPERATIONS      1700    201709  12
Time taken: 0.079 seconds, Fetched: 4 row(s)
hive (default)> 
```

>   - 方式二:上传数据后添加分区

```shell script
hive (default)> dfs -mkdir -p /user/hive/warehouse/dept_partition2/month=201709/day=11;
hive (default)> dfs -put /opt/module/datas/dept.txt  /user/hive/warehouse/dept_partition2/month=201709/day=11;
hive (default)> alter table dept_partition2 add partition(month='201709',day='11');
OK
Time taken: 0.08 seconds
hive (default)> select * from dept_partition2 where month='201709' and day='11';
OK
dept_partition2.deptno  dept_partition2.dname   dept_partition2.loc     dept_partition2.month   dept_partition2.day
10      ACCOUNTING      1700    201709  11
20      RESEARCH        1800    201709  11
30      SALES   1900    201709  11
40      OPERATIONS      1700    201709  11
Time taken: 0.098 seconds, Fetched: 4 row(s)
hive (default)> 
```

>   - 方式三:创建文件夹后load数据到分区

```shell script
hive (default)> dfs -mkdir -p /user/hive/warehouse/dept_partition2/month=201709/day=10;
hive (default)> load data local inpath '/opt/module/datas/dept.txt' into table dept_partition2 partition(month='201709',day='10');
Loading data to table default.dept_partition2 partition (month=201709, day=10)
Partition default.dept_partition2{month=201709, day=10} stats: [numFiles=1, numRows=0, totalSize=68, rawDataSize=0]
OK
Time taken: 0.267 seconds
hive (default)> select * from dept_partition2 where month='201709' and day='10';
OK
dept_partition2.deptno  dept_partition2.dname   dept_partition2.loc     dept_partition2.month   dept_partition2.day
10      ACCOUNTING      1700    201709  10
20      RESEARCH        1800    201709  10
30      SALES   1900    201709  10
40      OPERATIONS      1700    201709  10
Time taken: 0.08 seconds, Fetched: 4 row(s)
hive (default)> 
```

#### 4.7 修改表
##### 4.7.1 重命名表

> 语法:
> - ALTER TABLE table_name RENAME TO new_table_name

> 案例:
> - alter table dept_partition2 rename to dept_partition3;


##### 4.7.2 增加、修改和删除表分区
> 详见4.6.1分区表基本操作

##### 4.7.3 增加/修改/替换列信息

> 语法:
> - 更新列:ALTER TABLE table_name CHANGE [COLUMN] col_old_name col_new_name column_type [COMMENT col_comment] [FIRST|AFTER column_name]
> - 增加和替换列: ALTER TABLE table_name ADD|REPLACE COLUMNS (col_name data_type [COMMENT col_comment], ...) 
>   - ADD是代表新增一字段,字段位置在所有列后面(partition列前)
>   - REPLACE则是表示替换表中所有字段。

> 案例:
> - 查询表结构
> - 添加列
> - 更新列
> - 替换列

```hiveql
create table if not exists dept_partition(
deptno int, dname string, loc string
)
partitioned by (month string)
row format delimited fields terminated by '\t';

desc dept_partition;

alter table dept_partition add columns (deptdesc string);
alter table dept_partition change column deptdesc desc int;
alter table dept_partition replace columns (deptno string, dname string, loc string);

desc dept_partition;
```

#### 4.8 删除表

```hiveql
create table if not exists dept_partition(
deptno int, dname string, loc string
)
partitioned by (month string)
row format delimited fields terminated by '\t';

drop table dept_partition;
```

## 第五章 DML数据操作
### 5.1 数据导入
#### 5.1.1 向表中装载数据(load)

> 语法
> - load data [local] inpath '文件路径' [overwrite] into table tablename [partition (key1=value1,...)]
>   - load data:表示加载数据
>   - local:从本地加载数据到hive表,否则从HDFS加载数据到hive表
>   - inpath:表示数据加载路径
>   - overwrite:表示覆盖表中数据,否则表示追加
>   - into table:表示加载到那张表
>   - partition:表示上传到指定分区

> 案例
> - 创建一张表,先加载本地文件到hive,再加载HDFS到hive,通过覆盖加载数据到表中.

```hiveql
create table  student(id string,name string) row format delimited fields terminated by "\t";

load data local inpath '/opt/datas/student.txt' into table student;

dfs -put /opt/datas/student.txt  /user/atguigu/hive;
load data inpath '/user/atguigu/hive/student.txt' into table student;

load data local inpath '/opt/module/datas/student.txt' overwrite into table student;

```

#### 5.1.2 通过查询语句向表中插入数据(insert)

> 案例
> - 创建一张分区表,基本插入数据,基本模式插入(根据单张表查询结果),多插入模式(根据多张表查询结果)

```hiveql
create table  student_partition (id int,name string) partitioned by(month string) row format delimited fields terminated by "\t";
insert into table student_partition partition (month='201709') values (1,"wang wu");

insert overwrite table student_partition partition(month='201708') select id,name from student_partition where month='201709';

from student_partition  insert overwrite table student_partition partition (month='201707') select id,name where month='201709' insert overwrite table student_partition partition (month='201706') select id,name where month='201709';  

```

> 操作过程

```shell script
hive (default)> create table  student_partition (id int,name string) partitioned by(month string) row format delimited fields terminated by "\t";
OK
Time taken: 0.145 seconds
hive (default)> insert into table student_partition partition (month='201709') values (1,"wang wu");
hive (default)> select * from student_partition;
OK
student_partition.id    student_partition.name  student_partition.month
1       wang wu 201709
Time taken: 0.093 seconds, Fetched: 1 row(s)
hive (default)> insert overwrite table student_partition partition(month='201708') select id,name from student_partition where month='201709';
hive (default)> select * from student;
OK
student.id      student.name
1001    zhangsan
1002    lishi
1003    zhaoliu
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.06 seconds, Fetched: 6 row(s)
hive (default)> select *from student_partition;
OK
student_partition.id    student_partition.name  student_partition.month
1       wang wu 201708
1       wang wu 201709
Time taken: 0.059 seconds, Fetched: 2 row(s)
hive (default)> from student_partition  insert overwrite table student_partition partition (month='201707') select id,name where month='201709' insert overwrite table student_partition partition (month='201706') select id,name where month='201709' ;
hive (default)> select *from student_partition;
OK
student_partition.id    student_partition.name  student_partition.month
1       wang wu 201706
1       wang wu 201707
1       wang wu 201708
1       wang wu 201709
Time taken: 0.078 seconds, Fetched: 4 row(s)
hive (default)> 
```


#### 5.1.3 查询语句中创建表并加载数据(as select)

> 根据查询结果创建表(查询的结果会添加到新创建的表中)

```text

create table if not exists student3 as select id,name from student;

```

#### 5.1.4 创建表时通过Location指定加载数据路径

> 案例
> - 创建表并指定在hdfs上的位置,上传数据到hdfs并查询

```hiveql

create table if not exists student6 (id int,name string) row format delimited fields terminated by "\t" location '/user/hive/warehouse/student6';

dfs -put /opt/module/datas/student.txt /user/hive/warehouse/student6;

select * from student6;

```

> 操作过程

```shell script
hive (default)> create table if not exists student6 (id int,name string) row format delimited fields terminated by "\t" location '/user/hive/warehouse/student6';
OK
Time taken: 0.048 seconds
hive (default)> dfs -put /opt/module/datas/student.txt /user/hive/warehouse/student6;
hive (default)> select * from student6;
OK
student6.id     student6.name
1001    zhangsan
1002    lishi
1003    zhaoliu
Time taken: 0.051 seconds, Fetched: 3 row(s)
hive (default)> 
```


#### 5.1.5 Import数据到指定Hive表中

> 注意: 必须先用export导出后,再将数据导入.

```hiveql

import table student2 partition (month='201709') from '/user/hive/warehouse/export/student';

```

### 5.2 数据导出
#### 5.2.1 Insert导出

> 案例
> - 将查询的结果导出到本地
> - 将查询的结果格式化导出到本地
> - 将查询的结果导出到HDFS上

```hiveql
create table if not exists student7 (id int,name string) row format delimited fields terminated by "\t";

insert overwrite local directory '/opt/module/datas/export/student' select * from student7;

insert overwrite local directory '/opt/module/datas/export/student' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'  select * from student7;

insert overwrite directory '/user/atguigu/student2' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' select * from student7;

```


#### 5.2.2 Hadoop命令导出到本地

```hiveql
dfs -get /user/hive/warehouse/student/month=201709/000000_0 /opt/module/datas/export/student3.txt;
```


#### 5.2.3 Hive Shell 命令导出

> 基本语法
> - hive -f/-e 执行语句或者脚本 > file

```shell script
[root@hadoop100 hive]# bin/hive -e 'select * from default.student;' > /opt/module/datas/export/student4.txt;
```

#### 5.2.4 Export导出到HDFS上

```text
export table default.student to '/user/hive/warehouse/export/student';
```

#### 5.2.5 Sqoop导出

> 有专门的学科讲.

### 5.3 清除表中数据(Truncate)

> truncate只能删除管理表,不能删除外部表中数据

```text
truncate table student;
```

## 第六章 查询

> 官方文档: https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Select

> 基本语句语法

```text
[WITH CommonTableExpression (,CommonTableExpression)*] (Note: Only available starting with Hive 0.13.0)
SELECT [ALL | DISTINCT] select_expr,select_expr,...
  FROM table_reference
  [WHERE where_condition]
  [GROUP BY col_list]
  [ORDER BY col_list]
  [CLUSTER BY col_list | [DISTRIBUTE BY col_list] [SORT BY col_list]]
  [LIMIT number]
```

### 6.1 基本查询(select...from)
#### 6.1.1 全表和特定列查询

> 注意事项
> - SQL 语言大小写不敏感
> - SQL 可以写在一行或者多行
> - 关键字不能被缩写也不能分行
> - 各子句一般要分行写
> - 使用缩进提高语句的可读性

> 1. 全表查询
> 2. 选择特定列查询

```text
hive (default)> select * from emp;
OK
emp.empno       emp.ename       emp.job emp.mgr emp.hiredate    emp.sal emp.comm        emp.deptno
7369    SMITH   CLERK   7902    1980-12-17      800.0   NULL    20
7499    ALLEN   SALESMAN        7698    1981-2-20       1600.0  300.0   30
7521    WARD    SALESMAN        7698    1981-2-22       1250.0  500.0   30
7566    JONES   MANAGER 7839    1981-4-2        2975.0  NULL    20
7654    MARTIN  SALESMAN        7698    1981-9-28       1250.0  1400.0  30
7698    BLAKE   MANAGER 7839    1981-5-1        2850.0  NULL    30
7782    CLARK   MANAGER 7839    1981-6-9        2450.0  NULL    10
7788    SCOTT   ANALYST 7566    1987-4-19       3000.0  NULL    20
7839    KING    PRESIDENT       NULL    1981-11-17      5000.0  NULL    10
7844    TURNER  SALESMAN        7698    1981-9-8        1500.0  0.0     30
7876    ADAMS   CLERK   7788    1987-5-23       1100.0  NULL    20
7900    JAMES   CLERK   7698    1981-12-3       950.0   NULL    30
7902    FORD    ANALYST 7566    1981-12-3       3000.0  NULL    20
7934    MILLER  CLERK   7782    1982-1-23       1300.0  NULL    10
Time taken: 1.121 seconds, Fetched: 14 row(s)
hive (default)> select empno,ename from emp;
OK
empno   ename
7369    SMITH
7499    ALLEN
7521    WARD
7566    JONES
7654    MARTIN
7698    BLAKE
7782    CLARK
7788    SCOTT
7839    KING
7844    TURNER
7876    ADAMS
7900    JAMES
7902    FORD
7934    MILLER
Time taken: 0.088 seconds, Fetched: 14 row(s)
hive (default)> 
```


#### 6.1.2 列别名

> 说明
> - 重命名一个列
> - 便于计算
> - 紧跟列名,也可以在列名和别名之间加入关键字'AS'

```text
hive (default)> select ename as name,deptno dn from emp;
OK
name    dn
SMITH   20
ALLEN   30
WARD    30
JONES   20
MARTIN  30
BLAKE   30
CLARK   10
SCOTT   20
KING    10
TURNER  30
ADAMS   20
JAMES   30
FORD    20
MILLER  10
Time taken: 0.074 seconds, Fetched: 14 row(s)
hive (default)> 
```

#### 6.1.3 算术运算符

<table>
    <tr>
        <th>运算符</th>
        <th>关键字</th>
    </tr>
    <tr>
        <th>A+B</th>
        <th>A和B相加</th>
    </tr>
    <tr>
        <th>A-B</th>
        <th>A减去B</th>
    </tr>
    <tr>
        <th>A*B</th>
        <th>A乘以B</th>
    </tr>
    <tr>
        <th>A/B</th>
        <th>A除以B</th>
    </tr>
    <tr>
        <th>A%B</th>
        <th>A对B取余</th>
    </tr>
    <tr>
        <th>A&B</th>
        <th>A和B按位取与</th>
    </tr>
    <tr>
        <th>A|B</th>
        <th>A和B按位取或</th>
    </tr>
    <tr>
        <th>A^B</th>
        <th>A和B按位取异或</th>
    </tr>
    <tr>
        <th>~A</th>
        <th>A按位取反</th>
    </tr>
</table>

```text
hive (default)> select sal,sal+1000 from emp;
OK
sal     _c1
800.0   1800.0
1600.0  2600.0
1250.0  2250.0
2975.0  3975.0
1250.0  2250.0
2850.0  3850.0
2450.0  3450.0
3000.0  4000.0
5000.0  6000.0
1500.0  2500.0
1100.0  2100.0
950.0   1950.0
3000.0  4000.0
1300.0  2300.0
Time taken: 0.074 seconds, Fetched: 14 row(s)
hive (default)> 
```

#### 6.1.4 常用函数

> 常用函数
> - 求总行数count
> - 求工资的最大值max
> - 求工资的最小值min
> - 求工资的总和sum
> - 求工资的平均值avg

#### 6.1.5 Limit语句

> LIMIT子句用于限制返回的行数

```text
hive (default)> select * from emp limit 2;
OK
emp.empno       emp.ename       emp.job emp.mgr emp.hiredate    emp.sal emp.comm        emp.deptno
7369    SMITH   CLERK   7902    1980-12-17      800.0   NULL    20
7499    ALLEN   SALESMAN        7698    1981-2-20       1600.0  300.0   30
Time taken: 0.057 seconds, Fetched: 2 row(s)
hive (default)> 
```

### 6.2 Where语句

> - 使用WHERE子句,将不满足条件的行过滤掉
> - WHERE子句紧随FROM子句

#### 6.2.1 比较运算符(between/in/is null)

> 表中描述了谓词操作符,这些操作符同样可以用于JOIN…ON和HAVING语句中

<table>
    <tr>
        <th>操作符</th>
        <th>支持的数据类型</th>
        <th>描述</th>
    </tr>
    <tr>
        <th>A=B</th>
        <th>基本数据类型</th>
        <th>如果A等于B则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>A<>B,A!=B</th>
        <th>基本数据类型</th>
        <th>A或者B为NULL则返回NULL;如果A不等于B,则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>A<=>B</th>
        <th>基本数据类型</th>
        <th>如果A和B都为NULL,则返回TRUE,其他的和等号(=)操作符的结果一致,如果任一为NULL则结果为NULL</th>
    </tr>
    <tr>
        <th>A&ltB</th>
        <th>基本数据类型</th>
        <th>A或者B为NULL,则返回NULL;如果A小于B,则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>A<=B</th>
        <th>基本数据类型</th>
        <th>A或者B为NULL,则返回NULL;如果A小于等于B,则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>A>B</th>
        <th>基本数据类型</th>
        <th>A或者B为NULL,则返回NULL;如果A大于B,则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>A>=B</th>
        <th>基本数据类型</th>
        <th>A或者B为NULL,则返回NULL;如果A大于等于B,则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>A [NOT] BETWEEN B AND C</th>
        <th>基本数据类型</th>
        <th>如果A,B或者C任一为NULL,则结果为NULL.如果A的值大于等于B而且小于或等于C,则结果为TRUE,反之为FALSE.如果使用NOT关键字则可达到相反的效果</th>
    </tr>
    <tr>
        <th>A IS NULL</th>
        <th>所有数据类型</th>
        <th>如果A等于NULL,则返回TRUE,反之返回FALSE</th>
    <tr>
        <th>A IS NOT NULL</th>
        <th>所有数据类型</th>
        <th>如果A不等于NULL,则返回TRUE,反之返回FALSE</th>
    </tr>
    <tr>
        <th>IN(数值1,,数值2)</th>
        <th>所有数据类型</th>
        <th>使用IN运算显示列表中的值</th>
    </tr>
    <tr>
        <th>A [NOT] LIKE B</th>
        <th>STRING 类型</th>
        <th>B是一个SQL下的简单正则表达式,如果A与其匹配的话,则返回TRUE;反之返回FALSE.B的表达式说明如下:'x%'表示A必须以字母'x'开头,'%x'表示A必须以字母'x'结尾,而'%x%'表示A包含有字母'x',可以位于开头,结尾或者字符串中间.如果使用NOT关键字则可达到相反的效果</th>
    </tr>
    <tr>
        <th>A RLIKE B, A REGEXP B</th>
        <th>STRING 类型</th>
        <th>B是一个正则表达式,如果A与其匹配,则返回TRUE;反之返回FALSE.匹配使用的是JDK中的正则表达式接口实现的,因为正则也依据其中的规则.例如,正则表达式必须和整个字符串A相匹配,而不是只需与其字符串匹配</th>
    </tr>
</table>

```text
hive (default)> select * from emp where sal between 1000 and 1600;
OK
emp.empno       emp.ename       emp.job emp.mgr emp.hiredate    emp.sal emp.comm        emp.deptno
7499    ALLEN   SALESMAN        7698    1981-2-20       1600.0  300.0   30
7521    WARD    SALESMAN        7698    1981-2-22       1250.0  500.0   30
7654    MARTIN  SALESMAN        7698    1981-9-28       1250.0  1400.0  30
7844    TURNER  SALESMAN        7698    1981-9-8        1500.0  0.0     30
7876    ADAMS   CLERK   7788    1987-5-23       1100.0  NULL    20
7934    MILLER  CLERK   7782    1982-1-23       1300.0  NULL    10
Time taken: 0.077 seconds, Fetched: 6 row(s)
hive (default)> 
hive (default)> select null=null;
OK
_c0
NULL
Time taken: 0.692 seconds, Fetched: 1 row(s)
hive (default)> select null<=> null;
OK
_c0
true
Time taken: 0.072 seconds, Fetched: 1 row(s)
hive (default)> 
```

#### 6.2.2 Like和RLike

> 说明
> - 使用LIKE运算选择类似的值
> - 选择条件可以包含字符或数字
>   - % 代表零个或多个字符(任意个字符)
>   - _ 代表一个字符
> - RLIKE子句是Hive中这个功能的一个扩展,其可以通过Java的正则表达式这个更强大的语言来指定匹配条件

```text
hive (default)> select 'ab' like 'a_';
OK
_c0
true
Time taken: 0.079 seconds, Fetched: 1 row(s)
hive (default)> select 'abc' like 'a_';
OK
_c0
false
Time taken: 0.076 seconds, Fetched: 1 row(s)
hive (default)> select 'abc' like 'a%';
OK
_c0
true
Time taken: 0.123 seconds, Fetched: 1 row(s)
hive (default)> select 'abc' rlike 'a*';
OK
_c0
true
Time taken: 0.08 seconds, Fetched: 1 row(s)
hive (default)> 
```


#### 6.2.3 逻辑运算符(and/or/not)    

<table>
    <tr>
        <th>操作符</th>
        <th>含义</th>
    </tr>
    <tr>
        <th>AND</th>
        <th>逻辑并</th>
    </tr>
    <tr>
        <th>OR</th>
        <th>逻辑或</th>
    </tr>
    <tr>
        <th>NOT</th>
        <th>逻辑否</th>
    </tr>
</table>


### 6.3 分组

> GROUP BY语句通常会和聚合函数一起使用,按照一个或者多个列队结果进行分组,然后对每个组执行聚合操作

#### 6.3.2 Having语句

> having与where不同点
> - where针对表中的列发挥作用,查询数据,having针对查询结果中的列发挥作用,筛选数据.
> - where后面不能写分组函数,而having后面可以使用分组函数
> - having只用于group by分组统计语句


### 6.4 Join语句

#### 6.4.1 等值Join

> Hive支持通常的SQL JOIN语句,但是只支持等值连接,不支持非等值连接

> 根据员工表和部门表中的部门编号相等,查询员工编号,员工名称和部门名称

```text
hive (default)> select e.empno, e.ename, d.deptno, d.dname from emp e join dept d on e.deptno = d.deptno;
e.empno e.ename d.deptno        d.dname
7369    SMITH   20      RESEARCH
7499    ALLEN   30      SALES
7521    WARD    30      SALES
7566    JONES   20      RESEARCH
7654    MARTIN  30      SALES
7698    BLAKE   30      SALES
7782    CLARK   10      ACCOUNTING
7788    SCOTT   20      RESEARCH
7839    KING    10      ACCOUNTING
7844    TURNER  30      SALES
7876    ADAMS   20      RESEARCH
7900    JAMES   30      SALES
7902    FORD    20      RESEARCH
7934    MILLER  10      ACCOUNTING
Time taken: 27.165 seconds, Fetched: 14 row(s)
hive (default)> 
```

#### 6.4.2 表的别名

> 说明
> - 使用别名可以简化查询
> - 使用表名前缀可以提高执行效率

#### 6.4.3 内连接

> 内连接:只有进行连接的两个表中都存在与连接条件相匹配的数据才会被保留下来

```text
hive (default)> select e.empno, e.ename, d.deptno from emp e join dept d on e.deptno = d.deptno;
```

#### 6.4.4 左外连接

> 左外连接:JOIN操作符左边表中符合WHERE子句的所有记录将会被返回

```text
hive (default)> select e.empno, e.ename, d.deptno from emp e left join dept d on e.deptno = d.deptno;
```

#### 6.4.5 右外连接

> 右外连接:JOIN操作符右边表中符合WHERE子句的所有记录将会被返回

```text
hive (default)> select e.empno, e.ename, d.deptno from emp e right join dept d on e.deptno = d.deptno;
```

#### 6.4.6 满外连接

> 满外连接:将会返回所有表中符合WHERE语句条件的所有记录.如果任一表的指定字段没有符合条件的值的话,那么就使用NULL值替代

```text
hive (default)> select e.empno, e.ename, d.deptno from emp e full join dept d on e.deptno = d.deptno;
```

#### 6.4.7 多表连接

> 连接n个表,至少需要n-1个连接条件.例如:连接三个表,至少需要两个连接条件

> - 创建表
> - 导入数据
> - 多表连接查询

```hiveql
create table if not exists location (
    loc int,
    loc_name string
)
row format delimited fields terminated by "\t";

load data local inpath '/opt/module/datas/location.txt' into table location;
```

```text
hive (default)> select e.ename, d.deptno, l.loc_name from emp e join dept d on d.deptno = e.deptno join location l on d.loc = l.loc;
```

#### 6.4.8 笛卡尔积

> 笛卡尔集会在下面条件下产生
> - 省略连接条件
> - 连接条件无效
> - 所有表中的所有行互相连接

```text
hive (default)> select empno,dname from emp,dept;
```

#### 6.4.9 连接谓词中不支持or

```text
hive (default)> select e.empno, e.ename, d.deptno from emp e join dept d on e.deptno = d.deptno or e.ename=d.dname;
FAILED: SemanticException [Error 10019]: Line 1:60 OR not supported in JOIN currently 'ename'
```

> 以上可以替换为union

```text
hive (default)> select e.empno, e.ename, d.deptno from emp e join dept d on e.deptno = d.deptno 
              > union 
              > select e.empno, e.ename, d.deptno from emp e join dept d on e.ename = d.dname;
```


### 6.5 排序

#### 6.5.1 全局排序

> order by:全局排序,一个reducer
> - 使用ORDER BY子句排序
>   - ASC(ascend):升序(默认)
>   - DESC(descend):降序
> - ORDER BY 子句在SELECT语句的结尾


> 案例
> - 查询员工信息按工资升序排列
> - 查询员工信息按工资降序排列

```text
hive (default)> select * from emp order by sal;
hive (default)> select * from emp order by sal desc;
```

#### 6.5.2 按照别名排序

> 按照员工薪水的2倍排序

```text
hive (default)> select ename, sal*2 2sal from emp order by 2sal;
```

#### 6.5.3 多个列排序

> 按照部门和工资升序排序

```text
hive (default)> select ename, deptno, sal from emp order by deptno,sal;
```

#### 6.5.4 每个MapReduce内部排序(sort by)

> sort by:每个reducer内部进行排序,对全局结果集来说不是排序
> - 只有1个reduce,那么和order by结果一样
> - 多个reduce是随机的保证每个reduce中有序.

> 案例:
> - 设置reduce个数
> - 查看设置reduce个数
> - 根据部门编号降序查看员工信息
> - 将查询结果导入到文件中(按照部门编号降序排序)

```text
hive (default)> set mapreduce.job.reduces=3;
hive (default)> set mapreduce.job.reduces;
hive (default)> select * from emp sort by empno desc;
hive (default)> insert overwrite local directory '/opt/module/datas/sortby-result' select * from emp sort by deptno desc;
```


#### 6.5.5 分区排序(distribute By)

> distribute by:类似MR中partition,进行分区,结合sort by使用<br>
> Hive要求distribute by语句要写在sort by语句之前

> 对于distribute by进行测试,一定要分配多reduce进行处理,否则无法看到distribute by的效果

> 案例:
> - 先按照部门编号分区，再按照员工编号降序排序

```text
hive (default)> set mapreduce.job.reduces=3;
hive (default)> insert overwrite local directory '/opt/module/datas/distribute-result' select * from emp distribute by deptno sort by empno desc;
```

#### 6.5.6 cluster by

> 当distribute by和sorts by字段相同时,可以使用cluster by方式<br>
> cluster by除了具有distribute by的功能外还兼具sort by的功能.但是排序只能是升序排序,不能指定排序规则为ASC或者DESC

```text
hive (default)> select * from emp cluster by deptno;
hive (default)> select * from emp distribute by deptno sort by deptno;
```

### 6.6 分桶及抽样查询
#### 6.6.1 分桶表数据存储

> 分通与分区
> - 分区针对的是数据的存储路径,分桶针对的是数据文件
> - 分区提供一个隔离数据和优化查询的便利方式
> - 分桶是将数据集分解成更容易管理的若干部分的另一个技术

> 先创建分桶表,通过直接导入数据文件的方式
> - 数据准备,student.txt
> - 创建分桶表
> - 查看表结构
> - 导入数据到分桶表中
> - 查看创建的分桶表中是否分成4个桶(通过http://hadoop100:50070/)

```hiveql
create table stu_buck(id int, name string)
clustered by(id) 
into 4 buckets
row format delimited fields terminated by '\t';

desc formatted stu_buck;

load data local inpath '/opt/module/datas/student.txt' into table stu_buck;
```

```shell script
hive (default)> create table stu_buck(id int, name string)
              > clustered by(id) 
              > into 4 buckets
              > row format delimited fields terminated by '\t';
OK
hive (default)> desc formatted stu_buck;
Num Buckets:            4                        
hive (default)> load data local inpath '/opt/module/datas/student.txt' into table stu_buck;
hive (default)>
hive (default)> select * from stu_buck;
OK
stu_buck.id     stu_buck.name
1001    ss1
1002    ss2
1003    ss3
1004    ss4
1005    ss5
1006    ss6
1007    ss7
1008    ss8
1009    ss9
1010    ss10
1011    ss11
1012    ss12
1013    ss13
1014    ss14
1015    ss15
1016    ss16
Time taken: 0.241 seconds, Fetched: 16 row(s)
hive (default)>
```

![查看分桶表文件](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0GMGGibYP8NX3tia6Obic6z7aKaibo9vFTVxRicFmgtbZ6D5VP3VoiabUWYjtiaZbaicbc16x9qQSNjSXnqg/0?wx_fmt=png)

> 创建分桶表时,数据通过子查询的方式导入
> - 先建一个普通的stu表
> - 向普通的stu表中导入数据
> - 清空stu_buck表中数据
> - 导入数据到分桶表,通过子查询的方式
> - 需要设置一个属性(否则还是一个分桶)
> - 查询分桶的数据

```hiveql
create table stu(id int, name string)
row format delimited fields terminated by '\t';

load data local inpath '/opt/module/datas/student.txt' into table stu;

set hive.enforce.bucketing=true;
set mapreduce.job.reduces=-1; -- 默认

```

```shell script
hive (default)> create table stu(id int, name string)
              > row format delimited fields terminated by '\t';
OK
Time taken: 0.066 seconds
hive (default)> load data local inpath '/opt/module/datas/student.txt' into table stu;
Loading data to table default.stu
Table default.stu stats: [numFiles=1, totalSize=165]
OK
Time taken: 0.245 seconds
hive (default)> select * from stu;
OK
stu.id  stu.name
1001    ss1
1002    ss2
1003    ss3
1004    ss4
1005    ss5
1006    ss6
1007    ss7
1008    ss8
1009    ss9
1010    ss10
1011    ss11
1012    ss12
1013    ss13
1014    ss14
1015    ss15
1016    ss16
Time taken: 0.068 seconds, Fetched: 16 row(s)
hive (default)> 
hive (default)> set hive.enforce.bucketing=true;
hive (default)> insert into table stu_buck
              > select id, name from stu;
hive (default)> select * from stu_buck;
OK
stu_buck.id     stu_buck.name
1016    ss16
1012    ss12
1008    ss8
1004    ss4
1009    ss9
1005    ss5
1001    ss1
1013    ss13
1010    ss10
1002    ss2
1006    ss6
1014    ss14
1003    ss3
1011    ss11
1007    ss7
1015    ss15
Time taken: 0.038 seconds, Fetched: 16 row(s)
hive (default)> 
```

![分桶表](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0GMGGibYP8NX3tia6Obic6z7ar96Mp6Cud90xNbMhmWu4MQicZ0fsYkbNc5wfoLNlGzibYC4WzZkvcbgA/0?wx_fmt=png)


#### 6.6.2 分桶抽样查询

> 对于非常大的数据集,有时用户需要使用的是一个具有代表性的查询结果而不是全部结果,Hive可以通过对表进行抽样来满足这个需求

> 查询抽样的数据
> - tablesample是抽样语句,语法:TABLESAMPLE(BUCKET x OUT OF y)
>   - y必须是table总bucket数的倍数或者因子.
>   - hive根据y的大小,决定抽样的比例.
>   - x表示从哪个bucket开始抽取,如果需要取多个分区,以后的分区号为当前分区号加上y
>   - x的值必须小于等于y的值 
 
> 例如
> - table总bucket数为4,当y=2时,抽取(4/2=)2个bucket的数据,当y=8时,抽取(4/8=)1/2个bucket的数据 
> - table总bucket数为4,tablesample(bucket 1 out of 2),表示总共抽取4/2=)2个bucket的数据,抽取第1(x)个和第3(x+y)个bucket的数据

> 从第一个桶开始抽取,抽取(4/4)1个数据

```shell script
hive (default)> select * from stu_buck tablesample(bucket 1 out of 4 on id);
OK
stu_buck.id     stu_buck.name
1016    ss16
1012    ss12
1008    ss8
1004    ss4
Time taken: 0.165 seconds, Fetched: 4 row(s)
hive (default)> 
hive (default)> select * from stu_buck tablesample(bucket 1 out of 8 on id);
OK
stu_buck.id     stu_buck.name
1016    ss16
1008    ss8
Time taken: 0.05 seconds, Fetched: 2 row(s)
hive (default)> 
```


### 6.7 其他常用查询函数
#### 6.7.1 空字段赋值

> NVL:给值为NULL的数据赋值,它的格式是NVL(string1,replace_with)
> - 如果string1为NULL,则NVL函数返回replace_with的值,否则返回string1的值.
> - 如果两个参数都为NULL,则返回NULL

> 员工表
> - 如果员工的comm为NULL,则用-1代替
> - 如果员工的comm为NULL,则用领导id代替
```shell script
hive (default)> select nvl(comm,-1) from emp;
hive (default)> select nvl(comm,mgr) from emp;
```

#### 6.7.2 CASE WHEN

> 案例
> - 数据准备,emp_sex.txt
> - 求出不同部门男女各多少人,结果如下

<table>
    <tr>
        <th>dept_id</th>
        <th>sex</th>
        <th>num</th>
    </tr>
    <tr>
        <th>A</th>
        <th>2</th>
        <th>1</th>
    </tr>
    <tr>
        <th>B</th>
        <th>1</th>
        <th>2</th>
    </tr>
</table>

```hiveql
create table emp_sex(
name string,
dept_id string,
sex string
)
row format delimited fields terminated by "\t";

load data local inpath '/opt/module/datas/emp_sex.txt' into table emp_sex;

select 
  dept_id,
  sum(case sex when '男' then 1 else 0 end) male_count,
  sum(case sex when '女' then 1 else 0 end) female_count
from 
  emp_sex
group by
  dept_id;
```

#### 6.7.3 行转列

> CONCAT(string A/col, string B/col…):返回输入字符串连接后的结果,支持任意个输入字符串

> CONCAT_WS(separator, str1, str2,...):它是一个特殊形式的CONCAT()
> - 第一个参数剩余参数间的分隔符,分隔符可以是与剩余参数一样的字符串.如果分隔符是NULL,返回值也将为NULL.
> - 这个函数会跳过分隔符参数后的任何 NULL 和空字符串.分隔符将被加到被连接的字符串之间

> COLLECT_SET(col):函数只接受基本数据类型,它的主要作用是将某字段的值进行去重汇总,产生array类型字段

> 案例
> - 数据准备,person_info.txt
> - 把星座和血型一样的人归类到一起,结果如下

<table>
    <tr>
        <th>星座|班级</th>
        <th>姓名</th>
    </tr>
    <tr>
        <th>射手座|A</th>
        <th>大海|凤姐</th>
    </tr>
    <tr>
        <th>白羊座|A</th>
        <th>孙悟空|猪八戒</th>
    </tr>
    <tr>
        <th>白羊座|B</th>
        <th>宋宋</th>
    </tr>
</table>

```hiveql
create table person_info(
name string,constellation string,blood_type string
)
row format delimited fields terminated by "\t";

load data local inpath '/opt/module/datas/person_info.txt' into table person_info;

select 
    t.base,concat_ws('|',collect_set(t.name)) 
from 
    (select 
        name,concat(constellation,'|',blood_type) as base 
    from person_info
    ) t
group by t.base;
```

#### 6.7.3 列转行

> EXPLODE(col):将hive一列中复杂的array或者map结构拆分成多行

> LATERAL VIEW
> - 用法:LATERAL VIEW udtf(expression) tableAlias AS columnAlias
> - 解释:用于和split, explode等UDTF一起使用,它能够将一列数据拆成多行数据,在此基础上可以对拆分后的数据进行聚合

> 案例
> - 数据准备,movie_info.txt
> - 将电影分类中的数组数据展开,结果如下

```hiveql
create table movie_info(
movie string,
category array<string>    
) 
row format delimited fields terminated by "\t" 
collection items terminated by ",";

load data local inpath "/opt/module/datas/movie_info.txt" into table movie_info;

select 
    movie,category_name 
from 
    movie_info 
lateral view explode(category) table_temp as category_name;
```

#### 6.7.4 窗口函数

> 相关函数说明
> - OVER():指定分析函数工作的数据窗口大小,这个数据窗口大小可能会随着行的变而变化
> - CURRENT ROW:当前行
> - n PRECEDING:往前n行数据
> - n FOLLOWING:往后n行数据
> - UNBOUNDED:起点,UNBOUNDED PRECEDING 表示从前面的起点,UNBOUNDED FOLLOWING表示到后面的终点
> - LAG(col,n):往前第n行数据
> - LEAD(col,n):往后第n行数据
> - NTILE(n):把有序分区中的行分发到指定数据的组中,各个组有编号,编号从1开始,对于每一行,NTILE返回此行所属的组的编号

> 案例
> - 数据准备business.txt

```text
name,orderdate,cost
jack,2017-01-01,10
tony,2017-01-02,15
jack,2017-02-03,23
tony,2017-01-04,29
jack,2017-01-05,46
jack,2017-04-06,42
tony,2017-01-07,50
jack,2017-01-08,55
mart,2017-04-08,62
mart,2017-04-09,68
neil,2017-05-10,12
mart,2017-04-11,75
neil,2017-06-12,80
mart,2017-04-13,94
```

> - 需求
>   - 查询在2017年4月份购买过的顾客及总人数
>   - 查询顾客的购买明细及月购买总额
>   - 上述的场景,要将cost按照日期进行累加
>   - 查询顾客上次的购买时间
>   - 查询前20%时间的订单信息

```hiveql
create table business(
name string, 
orderdate string,
cost int
) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';

load data local inpath "/opt/module/datas/business.txt" into table business;

-- 查询在2017年4月份购买过的顾客及总人数
select name,count(*) over() from business where orderdate like '2017-04%' group by name;
select name,count(*) over () from business where substring(orderdate,1,7) = '2017-04' group by name;

-- 查询顾客的购买明细及月购买总额
select name,orderdate,cost,sum(cost) over(partition by substr(orderdate,1,7)) from business;
select name,orderdate,cost,sum(cost) over(partition by month(orderdate)) from business;

-- 上述的场景,要将cost按照日期进行累加
select name,orderdate,cost,sum(cost) over(partition by name order by orderdate) from business;

select name,orderdate,cost, 
sum(cost) over() as sample1,--所有行相加 
sum(cost) over(partition by name) as sample2,--按name分组,组内数据相加 
sum(cost) over(partition by name order by orderdate) as sample3,--按name分组,组内数据累加 
sum(cost) over(partition by name order by orderdate rows between UNBOUNDED PRECEDING and current row ) as sample4 ,--和sample3一样,由起点到当前行的聚合 
sum(cost) over(partition by name order by orderdate rows between 1 PRECEDING and current row) as sample5, --当前行和前面一行做聚合 
sum(cost) over(partition by name order by orderdate rows between 1 PRECEDING AND 1 FOLLOWING ) as sample6,--当前行和前边一行及后面一行 
sum(cost) over(partition by name order by orderdate rows between current row and UNBOUNDED FOLLOWING ) as sample7 --当前行及后面所有行 
from business;

-- 查看顾客上次的购买时间
select name,orderdate,cost,lag(orderdate,1,'1900-01-01') over (partition by name order by orderdate)from business;
select name,orderdate,cost,lag(orderdate,1,'1900-01-01') over(partition by name order by orderdate ) as time1, lag(orderdate,2) over (partition by name order by orderdate) as time2 from business;

-- 查询前20%时间的订单信息
select name,orderdate,ntile(5) over(order by orderdate) as sorted from business;
select * from (select name,orderdate,ntile(5) over(order by orderdate) as sorted from business) t where sorted=1;
```

```shell script
hive (default)> select name,orderdate,cost, 
              > sum(cost) over() as sample1,--所有行相加 
              > sum(cost) over(partition by name) as sample2,--按name分组,组内数据相加 
              > sum(cost) over(partition by name order by orderdate) as sample3,--按name分组,组内数据累加 
              > sum(cost) over(partition by name order by orderdate rows between UNBOUNDED PRECEDING and current row ) as sample4 ,--和sample3一样,由起点到当前行的聚合 
              > sum(cost) over(partition by name order by orderdate rows between 1 PRECEDING and current row) as sample5, --当前行和前面一行做聚合 
              > sum(cost) over(partition by name order by orderdate rows between 1 PRECEDING AND 1 FOLLOWING ) as sample6,--当前行和前边一行及后面一行 
              > sum(cost) over(partition by name order by orderdate rows between current row and UNBOUNDED FOLLOWING ) as sample7 --当前行及后面所有行 
              > from business;
name    orderdate       cost    sample1 sample2 sample3 sample4 sample5 sample6 sample7
jack    2017-01-01      10      661     176     10      10      10      56      176
jack    2017-01-05      46      661     176     56      56      56      111     166
jack    2017-01-08      55      661     176     111     111     101     124     120
jack    2017-02-03      23      661     176     134     134     78      120     65
jack    2017-04-06      42      661     176     176     176     65      65      42
mart    2017-04-08      62      661     299     62      62      62      130     299
mart    2017-04-09      68      661     299     130     130     130     205     237
mart    2017-04-11      75      661     299     205     205     143     237     169
mart    2017-04-13      94      661     299     299     299     169     169     94
neil    2017-05-10      12      661     92      12      12      12      92      92
neil    2017-06-12      80      661     92      92      92      92      92      80
tony    2017-01-02      15      661     94      15      15      15      44      94
tony    2017-01-04      29      661     94      44      44      44      94      79
tony    2017-01-07      50      661     94      94      94      79      79      50
Time taken: 51.434 seconds, Fetched: 14 row(s)
hive (default)> 
```



#### 6.7.5 Rank

> 函数说明
> - RANK() 排序相同时会重复,总数不会变
> - DENSE_RANK() 排序相同时会重复,总数会减少
> - ROW_NUMBER() 会根据顺序计算

> 案例
> - 准备数据,score.txt
> - 计算每门学科成绩排名

```hiveql
create table score(
name string,
subject string, 
score int) 
row format delimited fields terminated by "\t";
load data local inpath '/opt/module/datas/score.txt' into table score;

```

```shell script
hive (default)> select name,subject,score,
              > rank() over(partition by subject order by score desc) rp,
              > dense_rank() over(partition by subject order by score desc) drp,
              > row_number() over(partition by subject order by score desc) rmp
              > from score; 
name    subject score   rp      drp     rmp
孙悟空  数学    95      1       1       1
宋宋    数学    86      2       2       2
婷婷    数学    85      3       3       3
大海    数学    56      4       4       4
宋宋    英语    84      1       1       1
大海    英语    84      1       1       2
婷婷    英语    78      3       2       3
孙悟空  英语    68      4       3       4
大海    语文    94      1       1       1
孙悟空  语文    87      2       2       2
婷婷    语文    65      3       3       3
宋宋    语文    64      4       4       4
hive (default)> 
```

## Hive练习

### 习题一

> 原始数据(action.txt)
```text
userId	visitDate	visitCount
u01	2017/1/21	5
u02	2017/1/23	6
u03	2017/1/22	8
u04	2017/1/20	3
u01	2017/1/23	6
u01	2017/2/21	8
u02	2017/1/23	6
u01	2017/2/22	4
```

> 使用SQL统计出每个用户的累积访问次数

```text
用户id	月份	小计	累积
u01	2017-01	11	11
u01	2017-02	12	23
u02	2017-01	12	12
u03	2017-01	8	8
u04	2017-01	3	3
```

> SQL

```hiveql
create table action(
userId string,
visitDate string,
visitCount int
) row format delimited fields terminated by "\t";

load data local inpath '/opt/module/datas/action.txt' into table action;

select userId,ym,tn,sum(tn) over(partition by userId order by tn) dn
from (
select 
userId,date_format(regexp_replace(visitDate,'/','-'),'yyyy-MM') ym,sum(visitCount) tn 
from action group by userId,date_format(regexp_replace(visitDate,'/','-'),'yyyy-MM')
)t;

```

### 习题二

> 原始数据(visit.txt)
> - 每个顾客访问任何一个店铺的任何一个商品时都会产生一条访问日志,访问日志存储的表名为visit,访客的用户id为user_id,被访问的店铺名称为shop
```text
user_id shop
u1	a
u2	b
u1	b
u1	a
u3	c
u4	b
u1	a
u2	c
u5	b
u4	b
u6	c
u2	c
u1	b
u2	a
u2	a
u3	a
u5	a
u5	a
u5	a
```

> 使用SQL完成统计
> - 每个店铺的UV(访客数)
> - 每个店铺访问次数top3的访客信息,输出店铺名称,访客id,访问次数

```hiveql
create table visit(
user_id string,
shop string
) row format delimited fields terminated by '\t';

load data local inpath '/opt/module/datas/visit.txt' into table visit;

-- 每个店铺UV
select shop,count(*) from visit group by shop;
select shop,user_id,count(*) ct from visit group by shop,user_id;

-- 每个店铺访问次数top3的访客信息

select t1.shop,t1.user_id,t1.ct
from
(
    select 
    shop,user_id,ct,row_number() over(partition by shop order by ct desc) rn
    from 
    (select shop,user_id,count(*) ct from visit group by shop,user_id) t 
) t1 
where t1.rn <4; 
```


## 第七章 函数
### 7.1 系统内置函数

> - 查看系统自带的函数
> - 显示自带的函数的用法
> - 详细显示自带的函数的用法

```hiveql
show functions;
desc function upper; -- 查看upper函数用法
desc function extended upper; --查看upper函数详细用法
```

### 7.2 自定义函数

> 自定义函数
> - Hive自带了一些函数,比如:max/min等,但是数量有限,自己可以通过自定义UDF来方便的扩展
> - 当Hive提供的内置函数无法满足你的业务处理需要时,此时就可以考虑使用用户自定义函数(UDF:user-defined function)
> - 根据用户自定义函数类别分为以下三种
>   - UDF(User-Defined-Function),一进一出
>   - UDAF(User-Defined Aggregation Function),聚集函数，多进一出,类似count/mix/max
>   - UDTF(User-Defined Table-Generating Functions),一进多出,例如:lateral view explore()

> 官方文档地址
> - https://cwiki.apache.org/confluence/display/Hive/HivePlugins

> 编程步骤
> - 继承org.apache.hadoop.hive.ql.UDF
> - 需要实现evaluate函数;evaluate函数支持重载
> - 在hive的命令行窗口创建函数
>   - 添加jar:add jar linux_jar_path
>   - 创建function:create [temporary] function [dbname.]function_name AS class_name;
> - 在hive的命令行窗口删除函数
>   - Drop [temporary] function [if exists] [dbname.]function_name;

> 注意事项
> -UDF必须要有返回类型,可以返回null,但是返回类型不能为void;

### 7.3 自定义UDF函数

> 自定义UDF函数
> - 创建一个Maven工程Hive
> - 导入依赖
> - 创建一个类
> - 打成jar包上传到服务器,/opt/module/jars/udf.jar
> - 将jar包添加到hive的classpath
> - 创建临时函数与开发好的java class关联
> - 即可在hql中使用自定义的函数strip


```xml
<!-- 导入hive依赖,https://mvnrepository.com/artifact/org.apache.hive/hive-exec -->
<dependencies>
	<dependency>
		<groupId>org.apache.hive</groupId>
		<artifactId>hive-exec</artifactId>
		<version>1.2.1</version>
	</dependency>
</dependencies>
```




