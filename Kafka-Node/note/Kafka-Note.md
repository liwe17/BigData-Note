# 大数据技术之 kafka
## 第一章 kafka概述
### 1.1 定义

kafka是一个分布式的基于发布/订阅模式的消息队列(message queue),主要应用于大数据实时处理领域.

### 1.2 消息队列
#### 1.2.1 传统消息队列的应用场景

![mq传统应用场景之异步处理](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegiczicqxyzo1psgaocgaq4jibrl7uibteny9rfrnjv25idfdubic8jiwibe42w/0?wx_fmt=png)

使用消息队列的好处
- 解耦
  - 允许独立的扩展或修改两边的处理过程,只要确保它们遵守同样的接口约束.
- 可恢复性
  - 系统的一部分组件失效时,不会影响到整个系统.消息队列降低了进程间的耦合度,所以即使一个处理消息的进程挂掉,加入队列中的消息仍然可以在系统恢复后被处理.
- 缓冲
  - 有助于控制和优化数据流经过系统的速度,解决生产消息和消费消息处理速度不一致的情况.
- 灵活性&峰值处理能力
  - 在访问量剧增的情况下,应用仍然需要继续发挥作用,但是这样的突发流量并不常见.如果为已能处理这类峰值访问为标准投入资源随时待命无疑是巨大的浪费.使用消息队列能够使关键组件顶住突发访问的压力,而不会因为突发的超负荷的请求而完全崩溃.
- 异步通信
  - 很多时候,用户不想也不需要立即处理消息,消息队列提供了异步处理机制,允许用户把一个消息放入队列,但并不立即处理它.想向队列中放入多少信息就放入多少,然后需要的时候再去处理它们.

#### 1.2.2 消息队列的两种模式

1. 点对点模式(一对一,消费者主动拉取数据,消息收到后清除)

消息生产者生产消息发送到queue中,然后消息消费者从queue中取出并且消费消息.消息被消费以后,queue中不再有存储,所以消息消费者不可能消费到已经消费的消息.queue支持存在多个消费者,但是对一个消息而言,只会有一个消费者可以消费.

![点对点模式](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegicbjjkk1rmgfbufpzjsuzsje7nhf9muxqfty7j0d0ljzedqbpw82odsq/0?wx_fmt=png)

2. 发布/订阅模式(一对多,消费者消费数据之后不会清除消息)

消息生产者(发布)将消息发布到topic中,同时有多个消息消费者(订阅)消费该消息.和点对点不同,发布到topic的消费会被所有订阅者消费.

![发布/订阅模式](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegicpkvqbrpvcqik4xomw7a1eiakmotmlrs6mfeyyfvrjba8ibialiabau4h2q/0?wx_fmt=png) 

### 1.3 kafka基础架构

![kafka架构](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegic1f8cssv30t1yqp6yicningicui98ayonagoojzqpbvibqpuzlu0hc5kea/0?wx_fmt=png)

1. producer:消息生产者,就是向kafka broker发消息的客户端.
2. consumer:消息消费者,向kafka broker取消息的客户端.
3. consumer group(cg):消费者组,由多个consumer组成.消费者组内每个消费者负责消费不同分区的数据,一个分区只能由一个组内消费者消费;消费者之间互不影响.所有的消费者都属于某个消费组,即消费者组是逻辑上的一个订阅者.
4. broker:一台kafka服务器就是一个broker,一个集群由多个broker组成.一个broker可以容纳多个topic.
5. topic:可以理解为一个队列,生产者和消费者面向的都是一个topic
6. partition:为了实现扩展性,一个非常大的topic可以分布到多个broker(即服务器)上,一个topic可以分为多个partition,每个partition是一个有序的队列.
7. replica:副本,为保证集群中的某个节点发生故障时,该节点上的partition数据不丢失且kafka仍然能够工作,kafka提供了副本机制,一个topic的每个分区都有若干副本,一个leader和若干个follower.
8. leader:每个分区多个副本的'主',生茶者发送数据的对象,以及消费者消费数据的对象都是leader.
9. follower:每个分区多个副本中的'从',实时从leader中同步数据,保持和leader数据的同步,leader发生故障时,某个follower会成为新的follower.

## 第二章 kafka快速入门
### 2.1 安装部署
#### 2.1.1 jar包下载

> http://kafka.apache.org/downloads.html

#### 2.1.2 部署

1. 解压安装包
2. 修改解压后的文件名称
3. 在/opt/module/kafka目录下创建logs文件夹
4. 修改配置文件
5. 配置环境变量
6. 启动服务
7. 关闭服务
```shell script
tar -zxvf kafka_2.11-0.11.0.0.tgz -c /opt/module

cd /opt/module && mv kafka_2.11-0.11.0.0/ kafka

cd /opt/module/kafka && mkdir logs

cd /opt/module/kafka/config && vi server.properties

vi /etc/profile
source /etc/profile

cd /opt/module/kafka/ && bin/kafka-server-start.sh -daemon config/server.properties

cd /opt/module/kafka/ && bin/kafka-server-stop.sh
```

```properties
# server.properties文件内容
#broker 的全局唯一编号，不能重复
broker.id=0
#删除 topic 功能使能
delete.topic.enable=true
#处理网络请求的线程数量
num.network.threads=3
#用来处理磁盘 io 的现成数量
num.io.threads=8
#发送套接字的缓冲区大小
socket.send.buffer.bytes=102400
#接收套接字的缓冲区大小
socket.receive.buffer.bytes=102400
#请求套接字的缓冲区大小
socket.request.max.bytes=104857600
#kafka 运行日志存放的路径
log.dirs=/opt/module/kafka/logs
#topic 在当前 broker 上的分区个数
num.partitions=1
#用来恢复和清理 data 下数据的线程数量
num.recovery.threads.per.data.dir=1
#segment 文件保留的最长时间，超时将被删除
log.retention.hours=168
#配置连接 zookeeper 地址,多个地址逗号分割
zookeeper.connect=localhost:2181

# 解决报错:error while fetching metadata with correlation id
# 解决报错:connection to node -1 could not be established
listeners=plaintext://localhost:9092
advertised.listeners=plaintext://localhost:9092
```

```shell script
# profile文件
# kafka_home
export kafka_home=/opt/module/kafka
export path=$path:$kafka_home/bin
```

### 2.2 kafka命令行操作

1. 查看当前服务中所有topic
2. 创建topic
3. 删除topic
4. 发送消息
5. 消费消息
6. 查看某个topic的详情
7. 修改分区数

```text
选项说明:
--topic 定义topic名
--replication-factor 定义副本数
--partitions 定义分区数
--from-beginning 会把所有的数据都读取出来
```

```shell script
cd /opt/module/kafka/ 
bin/kafka-topics.sh --zookeeper localhost:2181 --list

bin/kafka-topics.sh --zookeeper localhost:2181 --create --replication-factor 1 --partitions 3 --topic first

bin/kakka-topics.sh --zookeeper localhost:2181 --delete --topic first

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic first
>hello world
>test li test li

bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic first

bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic first

bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic first --partitions 6
```

## 第三章 kafka架构深入
### 3.1 kafka工作流程及文件存储机制

![kafka工作流程](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegichkurc0o3fiavqzqc0ykefraw8i1mdbgqfghhjafduqxsgnehhnp3ca/0?wx_fmt=png)

- kafka中消息是以topic进行分类的,生产者生产消息,消费者消费消息,都是面向topic的
- topic是逻辑上的概念,而partition是物理上的概念,每个partition对应一个log文件,该log文件中存储就是producer生产的数据.
- producer生产的数据会被不断追加到该log文件末端,且每条数据都有自己的offset.消费者组中的每个消费者,都会实时记录自己消费到了哪个offset,以便出错恢复时,从上次位置继续消费.

![kafka文件存储机制](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegic8ufqjtxdjnhynudswakbwmvlcf9j0mbic1gqjkk6vic7bubkdcnlfvrw/0?wx_fmt=png)

由于生产者生产的消息会不断追加到log文件末尾,为防止log文件过大导致数据定位效率低下,kafka采取了分片和索引机制.
- 每个partition分为多个segment,每个segment对应两个文件-'.index'文件和'.log'文件
- index和log文件位于一个文件夹下,该文件夹的命名规则为:topic+分区序号
  - 例如first有三个分区,则其对应的文件夹为first0,first1,first3

![index文件和log文件详解](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegicniaxbkstbzpicrttlp3eln9qn8hah2akd1h1e0rv04e63mwbzedu5ia8w/0?wx_fmt=png)

```text
00000000000000000000.index
00000000000000000000.log
00000000000000170410.index
00000000000000170410.log
00000000000000239430.index
00000000000000239430.log
```

- index和log文件以当前segment的第一条消息的offset命名.
- '.index'文件存储大量的索引信息,'.log'文件存储大量的数据,索引文件中的元数据指向对应数据文件中message的物理偏移地址.

### 3.2 kafka生产者
#### 3.2.1 分区策略
1. 分区的原因
- 方便在集群中扩展,每个partition可以通过调整以适应它所在的机器,而一个topic又可以有多个partition组成,因此整个集群就可以适应任意大小的数据了.
- 可以提高并发,因为可以以partition为单位读写.

2. 分区的原则

![producerrecord对象](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q0s0qz7iadbhfvht75libaegicjh86ibhngjjptbik0m2ujqqh0wnmdsxbzjq7smtjib84v6fonpe1mexq/0?wx_fmt=png)

我们需要将producer发送的数据封装成一个producerrecord对象.
- 指明partition的情况下,直接将指明的值作为partition值.
- 没有指明partition值但有key的情况下,将key的hash值与topic的partition数进行取余得到partition值.
- 既没有partition值又没有key值的情况下,第一次调用时随机生成一个整数(后面每次调用在这个整数上自增),将这个值与topic可用的partition总数取余得到partition值,也就是常说round-robin算法.

#### 3.2.2 数据可靠性保证

为了保证producer发送的数据,能可靠的发送到指定topic,topic的每个partition收到producer发送的数据后,都需要向producer发送ack(acknowledgement 确认收到),如果producer收到ack,就会进行下一轮发送,否则会重新发送.

![数据可靠性](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q13nookmmmlzfhttrbcs8qpluxwp8dtqqgcerrdtbeicdociadoqt6lsl6jsusqkcpkuorx2wmqp2yw/0?wx_fmt=png)

1. 副本数据同步策略

<table>
    <tr>
        <td>方案</td>
        <td>优点</td>
        <td>缺点</td>
    </tr>
    <tr>
        <td>半数以上完成同步,发送ack</td>
        <td>延迟低</td>
        <td>选举新的leader时,容忍n台节点故障,需要2n+1个副本</td>
    </tr>
    <tr>
        <td>全部同步完成,发送ack</td>
        <td>选举新的leader时,容忍n台节点故障,需要n+1个副本</td>
        <td>延迟高</td>
    </tr> 
</table>

kafka选择了第二种方案,原因如下:
- 同样为了容忍n台节点的故障,第一种方案需要2n+1个副本,而第二种方案只需要n+1个副本,而kafka的每个分区都有大量的数据,第一种方案会造成大量数据的冗余.
- 虽然第二种方案的网络延迟会比较高,但网络延迟对 kafka 的影响较小

2. isr

采用第二种方案之后,设想以下情景:leader收到数据,所有follower都开始同步数据,但有一个follower,因为某种故障,迟迟不能与leader进行同步,那leader就要一直等下去,直到它完成同步,才能发送ack,如何解决问题呢?
- leader维护了一个动态的 in-sync replica set (isr),意为和leader保持同步的follower集合.
- 当 isr中的follower完成数据的同步之后,leader就会给follower发送ack,如果follower长时间未向follower同步数据,则该follower被提出isr,该时间阀值由replica.lag.time.max.ms参数设定.
- leader发生故障之后,就会从isr中选举新的leader. 

3. ack应答机制

对于某些不太重要的数据,对数据的可靠性要求不是很高,能够容忍数据的少量丢失,所以没必要等isr中的follower全部接收成功,kafka提供了三种可靠性级别,用户根据对可靠性和延迟的要求进行权衡,acks参数配置
- 0: producer不等待broker的ack,这一操作提供了一个最低的延迟,broker接收到还没有写入磁盘就已经返回,当broker故障时有可能丢失数据.
- 1: producer等待broker的ack,partition的leader落盘成功后返回ack,如果在follower同步成功之前leader故障,那么将会丢失数据.
- all(-1):producer等待broker的ack,partition的全部leader落盘成功后返回ack,如果在follower同步成功之后,broker发送ack之前,leader故障,那么会造成数据重复.

![数据重复案例](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q13nookmmmlzfhttrbcs8qppvomlc3tm26htnf3tmln7el0kx3aia3h2cwjkhq0f3ibngxftm3oja2a/0?wx_fmt=png)

4. 故障处理

![日志文件中hw和leo](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q13nookmmmlzfhttrbcs8qpgp78d54ftss4xcrtlicw8qfjys0rfqvo7ichz7iblwe8wygwhqchyr0zw/0?wx_fmt=png)

- leo:指的是每个副本的最大offset.
- hw:指的是消费者能见到的最大offset,isr中最小的leo.

1. follower故障
- follower发生故障后会被临时踢出isr,待该follower恢复后,会读取本地磁盘记录的上次的hw,并将log文件高于hw的部分截取掉,从hw开始向leader进行同步.
- 等到follower的leo大于等于该partition的hw,即follower追上leader后,就可以重新加入isr.
2. leader故障
- leader发生故障之后,就会从isr中选出一个新的leader,为保证多个副本之间数据一致性,其余的follower都会先将各自的log文件高于hw的部分截掉,然后从leader中同步数据.
- 这只能保证副本之间的数据一致性,并不能保证数据不丢失或者不重复.

#### 3.2.3 exactly once语义

- 将服务器的ack级别设置为-1,可以保证producer到server之间不会丢失数据,即at least once语义,相对的,将服务器ack级别设置为0,可以保证生产者每条消息只会被发送一次,即at most once语义.
- at least once可以保证数据不丢失,但是不能保证数据不重复;相对的,at least once可以保证数据不重复,但是不能保证数据不丢失.
- 对于一些非常重要的信息,比如说交易数据,下游数据消费者要求数据既不重复也不丢失,即exactly once语义,在0.11版本以前的kafka,对此是无能为力的,只能保证数据不丢失,再在下游消费者对数据做全局去重.
- 0.11版本的kafka,引入了一项重大特性:幂等性,所谓的幂等性就是指producer不论向server发送多少次重复数据,server端都只会持久化一条.幂等性结合at least once语义,就构成了kafka的exactly once语义.
    - at least once + 幂等性 = exactly once
    - 启用幂等性,需要将producer的参数中enable.idompotence设置为true.
    - kafka的幂等性实现其实就是将原来下游需要做的去重放在了数据上游.
    - 开启幂等性的producer在初始化的时候会被分配一个pid,发往同一partition的消息会附带sequence number,broker 端会对<pid,partition,seqnumber>做缓存,当具有相同主键的消息提交时,broker只会持久化一条.
    - pid重启就会变化,同时不同的partition也具有不同主键,所以幂等性无法保证跨分区跨会话的exactly once.

### 3.3 kafka消费者
#### 3.3.1 消费方式

- consumer 采用pull(拉)模式从broker中读取数据.
    - 可以根据consumer的消费能力以适当的速率消费消息.
    - 如果kafka没有数据,消费者可能会陷入循环中,一直返回空数据,因此kafka的消费者在消费数据时会传入一个时长参数timeout,如果当前没有数据可供消费,consumer会等待一段时间之后再返回,这段时长即为timeout.
- push(推)模式很难适应消费速率不同的消费者.
    - 消息发送速率是由broker决定的,它的目标是尽可能以最快速度传递消息,因此这样很容易造成consumer来不及处理消息,典型的表现就是拒绝服务以及网络拥塞.

#### 3.3.2 分区

一个consumer group中有多个consumer,一个topic有多个partition,所以必然会涉及到partition的分配问题,即确定那个partition由哪个consumer来消费.

kafka有两种分配策略,一是roundrobin,一是range.

#### 3.3.3 offset的维护

由于consumer在消费过程中可能会出现断电宕机等故障,consumer恢复后,需要从故障前的位置的继续消费,所以consumer需要实时记录自己消费到了哪个offset,以便故障恢复后继续消费.

kafka0.9版本之前,consumer默认将offset保存在zookeeper中,从0.9版本开始,consumer默认将offset保存在kafka一个内置的topic中,该topic为__consumer_offsets.

![offset维护](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q13nookmmmlzfhttrbcs8qpv0boqkmxt2s4chs2apheselsxm6u98brj6ahmuryg3qpxs5htaoiadw/0?wx_fmt=png)

### 3.4 kafka 高效读写数据

1. 顺序读写磁盘
- kafka的producer生产数据,要写入到log文件中,写的过程是一直追加到文件末端,为顺序写.
- 官网有数据表明,同样的磁盘,顺序写能到600m/s,而随机写只有100k/s,这与磁盘的机械机构有关,顺序写之所以快,是因为其省去了大量磁头寻址的时间.

2. 零复制技术

![零拷贝](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q13nookmmmlzfhttrbcs8qpp3gfm4wzhigqiiceau8qsg1eeiadtsybj2y4f1qjmzibzfmbbdutupklw/0?wx_fmt=png)

### 3.5 zookeeper在kafka的作用

kafka集群中有一个broker会被选举为controller.
- 负责管理集群broker的上下线,所有topic的分区副本分配和leader选举等工作.
- controller的管理工作都是依赖于zookeeper的.

partition的leader选举过程

![选举过程](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q13nookmmmlzfhttrbcs8qppfagtn9heianmpayuauwzibogh4gbituhnfans7gpzwn5ypdkouzwbra/0?wx_fmt=png) 

### 3.6 kafka事务

kafka从0.11版本开始引入了事务支持.事务可以保证kafka在exactly once语义的基础上,生产和消费可以跨分区和会话,要么全部成功,要么全部失败.

#### 3.6.1 producer事务

为了实现跨分区跨会话的事务,需要引入一个全局唯一的transactionid,并将producer获得的pid和transactionid绑定,这样当producer重启后就可以通过正在进行的transactionid获得原来的pid.
- 为了管理transaction,kafka引入了一个新的组件transaction coordinator.
- producer就是通过和transaction coordinator交互获得transactionid对应的任务状态.
- transaction coordinator还负责将事务所有写入kafka的一个内部topic,这样即使整个服务重启,由于事务状态得到保存,进行中的事务状态可以得到恢复,从而继续进行.

#### 3.6.2 consumer事务

对于consumer而言,事务的保证就会相对较弱,尤其时无法保证commit的信息被精确消费.
- consumer可以通过offset访问任意信息.
- 不同的segment file生命周期不同.
- 同一事务的消息可能会出现重启后被删除的情况.

## 第四章 kafka api
### 4.1 producer api
#### 4.1.1 消息发送流程

kafka的producer发送消息采用的是异步发送的方式,在消息发送的过程中,涉及到了两个线程-main线程和sender线程,以及一个线程共享变量-recordaccumulator. main线程将消息发送给recordaccumulator,sender线程中不断从recordaccumulator中拉去消息发送到kafka broker.

![kafkaProducer发送消息流程](https://mmbiz.qpic.cn/mmbiz_png/bhb4f3h61q1l4icp0ckpl5gtubh722zgowf9fwb8n46ezhw2cxkkllq0u5uvvoiaox6ygbt6v6diaalkt2grd4pmw/0?wx_fmt=png)

相关参数：
- batch.size:只有数据积累到batch.size之后,sender才会发送数据.
- linger.ms:如果数据迟迟未达到batch.size,sender等待linger.timer之后就会发送数据.

#### 4.1.2 异步发送API

环境搭建
- 导入maven依赖
```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>0.11.0.0</version>
</dependency>
```
- 编写代码
    - 涉及的类
        - KafkaProducer: 需要创建一个生产对象,用来发送数据
        - ProducerConfig: 获取所需要的一系列配置参数
        - ProducerRecord: 每条数据都要封装成一个ProducerRecord对象
- 代码
    - Kafka-Demo.com.weiliai.kafka.CustomProducer

### 4.2 Consumer API

Consumer消费数据时的可靠性是很容易保证的,因为数据在kafka中是持久化的,故不用担心数据丢失问题.

由于consumer在消费过程中可能出现断电宕机等故障,consumer恢复后,需要从故障前的位置继续消费,所以consumer需要实时记录自己消费到了哪个offset,以便故障恢复后继续消费.

所以offset的维护是consumer消费数据必须考虑的问题.

#### 4.2.1 自动提交offset

- 导入依赖
```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>0.11.0.0</version>
</dependency>
```
- 编写代码
    - 需要用到的类
        - KafkaConsumer: 需要创建一个消费者对象,用来消费数据
        - ConsumerConfig: 获取所需的一系列配置参数
        - ConsumerRecord: 每条数据封装成一个ConsumerRecord对象
        
- 自动提交offset相关参数:
    - enable.auto.commit: 是否开启自动提交offset功能
    - auto.commit.interval.ms:自动提交offset的时间间隔
- 代码
    - Kafka-Demo.com.weiliai.kafka.CustomConsumer

#### 4.2.2 手动提交offset

虽然自动提交offset十分简介便利,但由于其是基于时间提交的,开发人员难以把握offset提交的时机,因此kafka还提供了手动提交offset的API.

手动提交offset的方法有两种:
- commitSync(同步提交)
- commitAsync(异步提交)

相同点:都会将本次poll的一批数据最高的偏移量提交
不同点:commitSync阻塞当前线程,一直到提交成功,并且会自动失败重试(由于不可控因素导致,也会出现提交失败);而commitAsync没有失败重试机制,故有可能提交失败.

数据漏消费和重复消费分析
- 无论同步或异步提交offset都可能造成数据漏消费或重复消费.
- 先提交offset后消费,造成数据漏消费;先消费后提交offset,可能造成重复消费.

#### 4.2.3 自定义存储offset

Kafka 0.9版本之前,offset存储在zookeeper,0.9版本之后,默认将offset存储在kafka的一个内置topic中,除此之外,kafka还可以自定义存储offset.

offset的维护是相当繁琐的,因为需要考虑到消费者的Rebalanced.

当有新的消费者加入消费者组,已有消费者退出消费者族或者所订阅的主题的区分发生变化,就会触发到分区的重新分配,重新分配的过程叫做Rebalanced.

消费者发生Rebalanced之后,每个消费者消费的分区就会发生变化,因此消费者要首先获取到自己被重新分配的分区,并且定位到每个分区最近提交的offset位置继续消费.






