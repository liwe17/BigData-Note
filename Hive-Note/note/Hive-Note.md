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



### 2.2 Hive安装部署


### 2.3 将本地文件导入Hive案例


### 2.4 MySql安装


### 2.5 Hive元数据配置到MySql



### 2.6 HiveJDBC访问


### 2.7 Hive常用交互命令


### 2.8 Hive其他命令操作



### 2.9 Hive常见属性配置
