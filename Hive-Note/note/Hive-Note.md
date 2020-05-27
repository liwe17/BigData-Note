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


#### 2.5.2 配置Metastore到MySql


#### 2.5.3 多窗口启动Hive测试


### 2.6 HiveJDBC访问


### 2.7 Hive常用交互命令


### 2.8 Hive其他命令操作



### 2.9 Hive常见属性配置
