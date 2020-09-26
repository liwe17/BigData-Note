# 大数据技术之 Kafka
## 第一章 Kafka概述
### 1.1 定义

Kafka是一个分布式的基于发布/订阅模式的消息队列(Message Queue),主要应用于大数据实时处理领域.

### 1.2 消息队列
#### 1.2.1 传统消息队列的应用场景

![MQ传统应用场景之异步处理](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegiczicQXyZo1PsgaOcgAq4jibRl7uibTEnY9rfrNJV25IdFdUBic8jIwibe42w/0?wx_fmt=png)

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

消息生产者生产消息发送到Queue中,然后消息消费者从Queue中取出并且消费消息.消息被消费以后,queue中不再有存储,所以消息消费者不可能消费到已经消费的消息.Queue支持存在多个消费者,但是对一个消息而言,只会有一个消费者可以消费.

![点对点模式](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegicbjjkk1RmGfbuFpZJsuzSjE7nHf9muXqFty7j0d0ljzEDqBPW82ODsQ/0?wx_fmt=png)

2. 发布/订阅模式(一对多,消费者消费数据之后不会清除消息)

消息生产者(发布)将消息发布到topic中,同时有多个消息消费者(订阅)消费该消息.和点对点不同,发布到topic的消费会被所有订阅者消费.

![发布/订阅模式](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegicpkvqbrPVCqIk4XOMW7a1eiaKmotmlRs6MfEyyFVRjba8ibiaLiabAu4H2Q/0?wx_fmt=png) 

### 1.3 Kafka基础架构

![Kafka架构](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegic1f8CsSv30T1yqp6YicNINGicUI98AyoNaGoOJzQPBvibQPuZlu0Hc5KeA/0?wx_fmt=png)

1. Producer:消息生产者,就是向kafka broker发消息的客户端.
2. Consumer:消息消费者,向kafka broker取消息的客户端.
3. Consumer Group(CG):消费者组,由多个consumer组成.消费者组内每个消费者负责消费不同分区的数据,一个分区只能由一个组内消费者消费;消费者之间互不影响.所有的消费者都属于某个消费组,即消费者组是逻辑上的一个订阅者.
4. Broker:一台kafka服务器就是一个broker,一个集群由多个broker组成.一个broker可以容纳多个topic.
5. Topic:可以理解为一个队列,生产者和消费者面向的都是一个topic
6. Partition:为了实现扩展性,一个非常大的topic可以分布到多个broker(即服务器)上,一个topic可以分为多个partition,每个partition是一个有序的队列.
7. Replica:副本,为保证集群中的某个节点发生故障时,该节点上的partition数据不丢失且kafka仍然能够工作,kafka提供了副本机制,一个topic的每个分区都有若干副本,一个leader和若干个follower.
8. leader:每个分区多个副本的'主',生茶者发送数据的对象,以及消费者消费数据的对象都是leader.
9. follower:每个分区多个副本中的'从',实时从leader中同步数据,保持和leader数据的同步,leader发生故障时,某个follower会成为新的follower.

## 第二章 Kafka快速入门
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
tar -zxvf kafka_2.11-0.11.0.0.tgz -C /opt/module

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
#用来处理磁盘 IO 的现成数量
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
#配置连接 Zookeeper 地址,多个地址逗号分割
zookeeper.connect=localhost:2181

# 解决报错:Error while fetching metadata with correlation id
# 解决报错:Connection to node -1 could not be established
listeners=PLAINTEXT://localhost:9092
advertised.listeners=PLAINTEXT://localhost:9092
```

```shell script
# profile文件
# KAFKA_HOME
export KAFKA_HOME=/opt/module/kafka
export PATH=$PATH:$KAFKA_HOME/bin
```

### 2.2 Kafka命令行操作

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

## 第三章 Kafka架构深入
### 3.1 Kafka工作流程及文件存储机制

![kafka工作流程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegicHKuRc0O3fIAvQZqC0YKeFRaW8I1MdBgqfGhhjaFDUQxSGnehhnp3cA/0?wx_fmt=png)

- Kafka中消息是以topic进行分类的,生产者生产消息,消费者消费消息,都是面向topic的
- Topic是逻辑上的概念,而partition是物理上的概念,每个partition对应一个log文件,该log文件中存储就是producer生产的数据.
- Producer生产的数据会被不断追加到该log文件末端,且每条数据都有自己的offset.消费者组中的每个消费者,都会实时记录自己消费到了哪个offset,以便出错恢复时,从上次位置继续消费.

![Kafka文件存储机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegic8ufqjtxdjNHYnUdsWakbwmvLCF9j0Mbic1gQjkk6Vic7bUbkDcNLfvRw/0?wx_fmt=png)

由于生产者生产的消息会不断追加到log文件末尾,为防止log文件过大导致数据定位效率低下,Kafka采取了分片和索引机制.
- 每个partition分为多个segment,每个segment对应两个文件-'.index'文件和'.log'文件
- index和log文件位于一个文件夹下,该文件夹的命名规则为:topic+分区序号
  - 例如first有三个分区,则其对应的文件夹为first0,first1,first3

![index文件和log文件详解](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegicniaXbkStbzPicRttLp3eln9qN8haH2aKd1h1E0Rv04E63mwBZedu5ia8w/0?wx_fmt=png)

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

### 3.2 Kafka生产者
#### 3.2.1 分区策略
1. 分区的原因
- 方便在集群中扩展,每个partition可以通过调整以适应它所在的机器,而一个topic又可以有多个partition组成,因此整个集群就可以适应任意大小的数据了.
- 可以提高并发,因为可以以partition为单位读写.

2. 分区的原则

![ProducerRecord对象](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0S0qZ7iadbHfvHT75LibAegicjH86ibhnGjjPtBIK0m2ujqqh0WnMDSXBzjq7SmTJib84V6FONpe1mEXQ/0?wx_fmt=png)

我们需要将producer发送的数据封装成一个ProducerRecord对象.
- 指明partition的情况下,直接将指明的值作为partition值.
- 没有指明partition值但有key的情况下,将key的hash值与topic的partition数进行取余得到partition值.
- 既没有partition值又没有key值的情况下,第一次调用时随机生成一个整数(后面每次调用在这个整数上自增),将这个值与topic可用的partition总数取余得到partition值,也就是常说round-robin算法.

#### 3.2.2 数据可靠性保证

为了保证producer发送的数据,能可靠的发送到指定topic,topic的每个partition收到producer发送的数据后,都需要向producer发送ack(acknowledgement 确认收到),如果producer收到ack,就会进行下一轮发送,否则会重新发送.

![数据可靠性](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q13noOkMMMLZfhtTRBCs8qPlUxWP8DtqQgcerRdtbeicDociaDoQT6lSl6JSUsQKCpkuOrX2WMqP2yw/0?wx_fmt=png)

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
- 同样为了容忍n台节点的故障,第一种方案需要2n+1个副本,而第二种方案只需要n+1个副本,而Kafka的每个分区都有大量的数据,第一种方案会造成大量数据的冗余.
- 虽然第二种方案的网络延迟会比较高,但网络延迟对 Kafka 的影响较小

2. ISR

采用第二种方案之后,设想以下情景:leader收到数据,所有follower都开始同步数据,但有一个follower,因为某种故障,迟迟不能与leader进行同步,那leader就要一直等下去,直到它完成同步,才能发送ack,如何解决问题呢?
- Leader维护了一个动态的 in-sync replica set (ISR),意为和leader保持同步的follower集合.
- 当 ISR中的follower完成数据的同步之后,leader就会给follower发送ack,如果follower长时间未向follower同步数据,则该follower被提出ISR,该时间阀值由replica.lag.time.max.ms参数设定.
- Leader发生故障之后,就会从ISR中选举新的leader. 

3. ACK应答机制

对于某些不太重要的数据,对数据的可靠性要求不是很高,能够容忍数据的少量丢失,所以没必要等ISR中的follower全部接收成功,Kafka提供了三种可靠性级别,用户根据对可靠性和延迟的要求进行权衡,acks参数配置
- 0: producer不等待broker的ack,这一操作提供了一个最低的延迟,broker接收到还没有写入磁盘就已经返回,当broker故障时有可能丢失数据.
- 1: producer等待broker的ack,partition的leader落盘成功后返回ack,如果在follower同步成功之前leader故障,那么将会丢失数据.
- all(-1):producer等待broker的ack,partition的全部leader落盘成功后返回ack,如果在follower同步成功之后,broker发送ack之前,leader故障,那么会造成数据重复.

![数据重复案例](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q13noOkMMMLZfhtTRBCs8qPpvOMLC3tM26HTNf3Tmln7EL0KX3Aia3H2CwJKHQ0f3ibngXFTM3oJa2A/0?wx_fmt=png)

4. 故障处理