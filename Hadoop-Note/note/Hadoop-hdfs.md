# Hadoop之HDFS
## 第一章 HDFS的概述
### 1.1 HDFS产生的背景与定义
1. HDFS产生的背景<br>
> 随着数据量越来越大,一个操作系统无法存储所有数据,那么就分配到更多的操作系统管理的磁盘中,但是不方便维护,因此需要一种系统管理管理多台机器上的文件,这就是分布式文件管理系统,其中HDFS就是分布式文件管理系统中的一种.

2. HDFS的定义
> HDFS(Hadoop Distributed File System),它是一个文件系统,用于存储文件,通过目录树定位文件;其次,它是分布式的,由很多服务器联合起来实现其功能,集群中的服务器有各自的角色.

> HDFS的使用场景:适合一次写入,多次读出的场景,且不支持文件的修改,适合用来做数据分析,并适合用来做网盘应用.

### 1.2 HDFS的优缺点
> HDFS的优点

![优点](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIJj6jAm8VuvevhxiaGmlGy2nfg9upVh0UDsib7ejCnbvYkvq4hmANAYog/0?wx_fmt=png)

> HDFS的缺点

![缺点](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIVXWjeYT3W3MfDuLAxBH33DRNCc4gxwz0CmYn1IXnh8xzju1kwl6ibUg/0?wx_fmt=png)

### 1.3 HDFS的组成架构
> HDFS的组成架构

![架构1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIgBhoWg1ZhjfibtkpDJjNsVZ6essHbW8UjmuxSUXWsFF4vloVYstVC8g/0?wx_fmt=png)
![架构2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIjnu3zOtp2fzl8VqnPsrAhlicNm47by9C6bWtcBhSP37xbORicbxYkyfg/0?wx_fmt=png)

### 1.4 HDFS文件块大小(面试重点)
> HDFS文件块大小

![块大小1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxI3VfqT53yiblIxLDUw38USFAbQbcdqxBfdOicefEKqn85MIlIsoTzcv1g/0?wx_fmt=png)
![块大小2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIqOx5sBYykTyDYGvMwHjsj1Y7vJFsLcicoqYAwekWCqgfUOqg87rVkGQ/0?wx_fmt=png)

## 第二章 HDFS的Shell操作(开发重点)

1. 基本语法
```shell script
bin/hadoop fs 具体命令 OR bin/hdfs dfs 具体命令

dfs是fs的实现类
```

2. 命令大全

```shell script
[root@hadoop102 hadoop-2.7.2]# bin/hadoop fs 
Usage: hadoop fs [generic options]
        [-appendToFile <localsrc> ... <dst>]
        [-cat [-ignoreCrc] <src> ...]
        [-checksum <src> ...]
        [-chgrp [-R] GROUP PATH...]
        [-chmod [-R] <MODE[,MODE]... | OCTALMODE> PATH...]
        [-chown [-R] [OWNER][:[GROUP]] PATH...]
        [-copyFromLocal [-f] [-p] [-l] <localsrc> ... <dst>]
        [-copyToLocal [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
        [-count [-q] [-h] <path> ...]
        [-cp [-f] [-p | -p[topax]] <src> ... <dst>]
        [-createSnapshot <snapshotDir> [<snapshotName>]]
        [-deleteSnapshot <snapshotDir> <snapshotName>]
        [-df [-h] [<path> ...]]
        [-du [-s] [-h] <path> ...]
        [-expunge]
        [-find <path> ... <expression> ...]
        [-get [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
        [-getfacl [-R] <path>]
        [-getfattr [-R] {-n name | -d} [-e en] <path>]
        [-getmerge [-nl] <src> <localdst>]
        [-help [cmd ...]]
        [-ls [-d] [-h] [-R] [<path> ...]]
        [-mkdir [-p] <path> ...]
        [-moveFromLocal <localsrc> ... <dst>]
        [-moveToLocal <src> <localdst>]
        [-mv <src> ... <dst>]
        [-put [-f] [-p] [-l] <localsrc> ... <dst>]
        [-renameSnapshot <snapshotDir> <oldName> <newName>]
        [-rm [-f] [-r|-R] [-skipTrash] <src> ...]
        [-rmdir [--ignore-fail-on-non-empty] <dir> ...]
        [-setfacl [-R] [{-b|-k} {-m|-x <acl_spec>} <path>]|[--set <acl_spec> <path>]]
        [-setfattr {-n name [-v value] | -x name} <path>]
        [-setrep [-R] [-w] <rep> <path> ...]
        [-stat [format] <path> ...]
        [-tail [-f] <file>]
        [-test -[defsz] <path>]
        [-text [-ignoreCrc] <src> ...]
        [-touchz <path> ...]
        [-truncate [-w] <length> <path> ...]
        [-usage [cmd ...]]

Generic options supported are
-conf <configuration file>     specify an application configuration file
-D <property=value>            use value for given property
-fs <local|namenode:port>      specify a namenode
-jt <local|resourcemanager:port>    specify a ResourceManager
-files <comma separated list of files>    specify comma separated files to be copied to the map reduce cluster
-libjars <comma separated list of jars>    specify comma separated jar files to include in the classpath.
-archives <comma separated list of archives>    specify comma separated archives to be unarchived on the compute machines.

The general command line syntax is
bin/hadoop command [genericOptions] [commandOptions]

[root@hadoop102 hadoop-2.7.2]# 
```

3. 常用命令
> 启动Hadoop集群(方便测试)

```shell script
[root@hadoop102 hadoop-2.7.2]# sbin/start-dfs.sh && sbin/start-yarn.sh
```
> -help:输出这个命令参数

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -help rm
-rm [-f] [-r|-R] [-skipTrash] <src> ... :
  Delete all files that match the specified file pattern. Equivalent to the Unix
  command "rm <src>"
                                                                                 
  -skipTrash  option bypasses trash, if enabled, and immediately deletes <src>   
  -f          If the file does not exist, do not display a diagnostic message or 
              modify the exit status to reflect an error.                        
  -[rR]       Recursively deletes directories                                    
[root@hadoop102 hadoop-2.7.2]# 
```

> -ls:显示目录信息

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /
Found 1 items
drwxr-xr-x   - root supergroup          0 2020-05-02 12:52 /user
[root@hadoop102 hadoop-2.7.2]# 
```

> -mkdir:在HDFS上创建目录

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -mkdir -p /sanguo/shuguo
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /
Found 2 items
drwxr-xr-x   - root supergroup          0 2020-05-02 19:00 /sanguo
drwxr-xr-x   - root supergroup          0 2020-05-02 12:52 /user
[root@hadoop102 hadoop-2.7.2]# 
```
> -moveFromLocal:从本地剪切粘贴到HDFS

```shell script
[root@hadoop102 hadoop-2.7.2]# touch kongming.txt
[root@hadoop102 hadoop-2.7.2]# hadoop fs -moveFromLocal ./kongming.txt /sanguo/shuguo
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /sanguo/shuguo/
Found 1 items
-rw-r--r--   3 root supergroup          0 2020-05-02 19:01 /sanguo/shuguo/kongming.txt
[root@hadoop102 hadoop-2.7.2]# ls |grep kong*
[root@hadoop102 hadoop-2.7.2]# 
```
> -appendToFile:追加一个文件到已经存在的文件末尾

```shell script
[root@hadoop102 hadoop-2.7.2]# touch liubei.txt
[root@hadoop102 hadoop-2.7.2]# echo 'san gu mao lu' >liubei.txt
[root@hadoop102 hadoop-2.7.2]# cat liubei.txt 
san gu mao lu
[root@hadoop102 hadoop-2.7.2]# hadoop fs -appendToFile liubei.txt /sanguo/shuguo/kongming.txt
[root@hadoop102 hadoop-2.7.2]# 
```
> -cat:显示文件内容

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -cat /sanguo/shuguo/kongming.txt
san gu mao lu
[root@hadoop102 hadoop-2.7.2]# 
```

> -chgrp,-chmod,-chown:Linux用法一样,修改文件所属权限
>
```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -chmod 666 /sanguo/shuguo/kongming.txt
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /sanguo/shuguo/kongming.txt
-rw-rw-rw-   3 root supergroup         14 2020-05-02 19:03 /sanguo/shuguo/kongming.txt
[root@hadoop102 hadoop-2.7.2]# 
```

> -copyFromLocal:从本低文件系统中拷贝文件到HDFS路径去

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -copyFromLocal README.txt /
[root@hadoop102 hadoop-2.7.2]# ls |grep READ*
README.txt
[root@hadoop102 hadoop-2.7.2]# 
```

> -copyToLocal:从HDFS拷贝到本地

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -copyToLocal /sanguo/shuguo/kongming.txt
[root@hadoop102 hadoop-2.7.2]# ls |grep kong*
kongming.txt
[root@hadoop102 hadoop-2.7.2]# 
```

> -cp:从HDFS的一个路径拷贝到HDFS的另一个路径

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -cp /sanguo/shuguo/kongming.txt /zhuge.txt
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /
Found 4 items
-rw-r--r--   3 root supergroup       1366 2020-05-02 19:07 /README.txt
drwxr-xr-x   - root supergroup          0 2020-05-02 19:00 /sanguo
drwxr-xr-x   - root supergroup          0 2020-05-02 12:52 /user
-rw-r--r--   3 root supergroup         14 2020-05-02 19:09 /zhuge.txt
[root@hadoop102 hadoop-2.7.2]# 
```

> -mv:在HDFS目录中移动文件

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -mv /zhuge.txt /sanguo/shuguo/
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /
Found 3 items
-rw-r--r--   3 root supergroup       1366 2020-05-02 19:07 /README.txt
drwxr-xr-x   - root supergroup          0 2020-05-02 19:00 /sanguo
drwxr-xr-x   - root supergroup          0 2020-05-02 12:52 /user
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /sanguo/shuguo
Found 2 items
-rw-rw-rw-   3 root supergroup         14 2020-05-02 19:03 /sanguo/shuguo/kongming.txt
-rw-r--r--   3 root supergroup         14 2020-05-02 19:09 /sanguo/shuguo/zhuge.txt
[root@hadoop102 hadoop-2.7.2]#
```

> -get:等用语copyToLocal,就是从HDFS下载文件到本地

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -get /sanguo/shuguo/kongming.txt ./
```

> -getmerge:合并下载多个文件,比如HDFS的目录 /user/atguigu/test下有多个文件:log1,log2...

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -mkdir -p /user/atguigu/test
[root@hadoop102 hadoop-2.7.2]# touch log1 && echo 'log1' >> log1
[root@hadoop102 hadoop-2.7.2]# touch log2 && echo 'log2' >> log2
[root@hadoop102 hadoop-2.7.2]# touch log3 && echo 'log3' >> log3
[root@hadoop102 hadoop-2.7.2]# hadoop fs -moveFromLocal log1 log2 log3 /user/atguigu/test
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /user/atguigu/test
Found 3 items
-rw-r--r--   3 root supergroup          5 2020-05-02 19:36 /user/atguigu/test/log1
-rw-r--r--   3 root supergroup          5 2020-05-02 19:36 /user/atguigu/test/log2
-rw-r--r--   3 root supergroup          5 2020-05-02 19:36 /user/atguigu/test/log3
[root@hadoop102 hadoop-2.7.2]# hadoop fs -getmerge /user/atguigu/test/* ./log.txt
[root@hadoop102 hadoop-2.7.2]# cat log.txt 
log1
log2
log3
[root@hadoop102 hadoop-2.7.2]# 
```

> -put:等同于copyFromLocal

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -put log.txt /user/atguigu/test
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /user/atguigu/test
Found 4 items
-rw-r--r--   3 root supergroup         15 2020-05-02 19:39 /user/atguigu/test/log.txt
-rw-r--r--   3 root supergroup          5 2020-05-02 19:36 /user/atguigu/test/log1
-rw-r--r--   3 root supergroup          5 2020-05-02 19:36 /user/atguigu/test/log2
-rw-r--r--   3 root supergroup          5 2020-05-02 19:36 /user/atguigu/test/log3
[root@hadoop102 hadoop-2.7.2]# 
```

> -tail:显示一个文件的末尾

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -tail /user/atguigu/test/log.txt
log1
log2
log3
[root@hadoop102 hadoop-2.7.2]# 
```

> -rm:删除文件或文件夹

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -rm  /user/atguigu/test/log.txt
20/05/02 19:41:25 INFO fs.TrashPolicyDefault: Namenode trash configuration: Deletion interval = 0 minutes, Emptier interval = 0 minutes.
Deleted /user/atguigu/test/log.txt
[root@hadoop102 hadoop-2.7.2]# 
```

> -rmdir:删除空目录

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -mkdir /test
[root@hadoop102 hadoop-2.7.2]# hadoop fs -rmdir /test
```

> -du统计文件夹的大小信息

```shell script
[root@hadoop102 hadoop-2.7.2]# hadoop fs -du -s -h /user/atguigu/test
15  /user/atguigu/test
[root@hadoop102 hadoop-2.7.2]# hadoop fs -du -h /user/atguigu/test
5  /user/atguigu/test/log1
5  /user/atguigu/test/log2
5  /user/atguigu/test/log3
[root@hadoop102 hadoop-2.7.2]# 
```

> -setrep:设置HDFS中文件的副本数量

```shell script
[root@hadoop102 hadoop]# hadoop fs -setrep 10 /sanguo/shuguo/kongming.txt
Replication 10 set: /sanguo/shuguo/kongming.txt
[root@hadoop102 hadoop]# 
```
> 这里设置的副本数只是记录在NameNode的元数据中,是否真的会有这么多副本,还要看DataNode的数量,目前只有三台设备,也就是最多有3个副本,只有节点增加到10台时,副本才能达到10.<br>
> 控制台:http://hadoop100:50070/explorer.html

![控制台](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIib22GU9LSoSsPLN8AGiaQDZJlUZMxu0a5sPDjzIJMUNwk0b7ibVdTyjJw/0?wx_fmt=png)

## 第三章 HDFS客户端操作(开发重点)
### 3.1 HDFS客户端环境准备
> 1. 安装windows的Hadoop,配置环境(百度)
> 2. 创建maven工程(Hadoop-Demo),导入相关依赖与日志打印
> 3. 编写测试代码,执行程序

```java
public class HdfsClient {
    @Test
    public void testMkdir() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        //配置在集群上运行
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 创建目录
        fs.mkdirs(new Path("/1109/daxian/banzhang"));
        //3 关闭流
        fs.close();
    }
}
```
### 3.2 HDFS的API操作
#### 3.2.1 HDFS文件上传(参数优先级)
1. 编写源代码
```java
public class HdfsClient {
    /**
     * DFS文件上传-测试参数优先级
     */
    @Test
    public void testCopyFromLocalFile() throws Exception{
        //1 获取文件系统
        final Configuration configuration = new Configuration();
//        configuration.set("dfs.replication","2");
        //配置在集群上运行
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 创建目录
        fs.copyFromLocalFile(new Path("d:/banzhang.txt"),new Path("/banzhang3.txt"));
        //3 关闭流
        fs.close();
    }
}
```
2. 将hdfs-site.xml拷贝到项目的根目录下
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
	<property>
		<name>dfs.replication</name>
        <value>1</value>
	</property>
</configuration>
```

3. 参数优先级
> 参数优先级排序:客户端代码中设置的值>ClassPath下的用户自定义配置文件>服务器的默认配置

#### 3.2.2 HDFS文件下载
```java
public class HdfsClient {
    /**
     * 测试HDFS文件下载
     */
    @Test
    public void testCopyToLocalFile() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 执行操作
        //delSrc 是否删除源文件
        //src 源文件路径
        //dst 下载文件路径
        //useRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false,new Path("/banzhang.txt"),new Path("d:/banhua.txt"),true);
        //3 关闭资源
        fs.close();
    }
}
```

#### 3.2.3 HDFS文件夹删除
```java
public class HdfsClient {
    /**
     * 测试HDFS文件夹删除
     */
    @Test
    public void testDelete() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 执行操作
        fs.delete(new Path("/1109"),true);
        //3 关闭资源
        fs.close();
    }
}
```

#### 3.2.4 HDFS文件名更改
```java
public class HdfsClient {
    /**
     * 测试HDFS文件名更改
     */
    @Test
    public void testRename() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 执行操作
        fs.rename(new Path("/banzhang.txt"),new Path("/banhua.txt"));
        //3 关闭资源
        fs.close();
    }
}
```

#### 3.2.5 HDFS文件详情查看

```java
public class HdfsClient {
    /**
     * 测试HDFS文件详情查看
     * 查看文件名称,权限,长度,块信息
     */
    @Test
    public void testListFiles() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取文件详情
        final RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while(listFiles.hasNext()){
            LocatedFileStatus next = listFiles.next();
            //输出文件详情
            //名称
            System.out.println(next.getPath().getName());
            //长度
            System.out.println(next.getLen());
            //权限
            System.out.println(next.getPermission());
            //分组
            System.out.println(next.getGroup());
            //获取存储块信息
            BlockLocation[] locations = next.getBlockLocations();
            for (BlockLocation location : locations) {
                //获取存储的主机节点信息
                String[] hosts = location.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.err.println("=============分割线======================");
        }
        //3 关闭资源
        fs.close();
    }
}
```

#### 3.2.6 HDFS文件和文件夹判断

```java
public class HdfsClient {
    /**
     * HDFS文件或文件夹判断
     */
    @Test
    public void testListStatus() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取文件详情
        final FileStatus[] status = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : status) {
            if(fileStatus.isDirectory()){
                System.out.println("目录:"+fileStatus.getPath().getName());
                continue;
            }
            System.out.println("文件:"+fileStatus);
        }
        //3 关闭资源
        fs.close();
    }
}
```

### 3.3 HDFS的I/O流操作

> 采用IO流的方式实现数据的上传下载

#### 3.3.1 HDFS文件上传

> 将本地D盘上的banhua.txt文件上传到HDFS根目录

```java
public class HdfsClient {
    /**
     * HDFS文件上传
     */
    @Test
    public void putFileToHDFS() throws Exception{
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 创建输入流
        final FileInputStream fis = new FileInputStream(new File("d:/banhua.txt"));
        //3 获取输出流
        final FSDataOutputStream fos = fs.create(new Path("/banhua1.txt"));
        //4 流拷贝
        IOUtils.copyBytes(fis,fos,configuration);
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
}
```

#### 3.3.2 HDFS文件下载

> 从HDFS上下载banhua.txt文件到本地D盘

```java
public class HdfsClient {
    /**
     * HDFS文件下载
     */
    @Test
    public void getFileToHDFS() throws Exception{
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取输入流
        final FSDataInputStream fis = fs.open(new Path("/banhua1.txt"));
        //3 创建输出流
        final FileOutputStream fos = new FileOutputStream(new File("d:/banhua1.txt"));
        //4 流拷贝
        IOUtils.copyBytes(fis,fos,configuration);
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
}

```

#### 3.3.3 定位文件读取

> 分块读取HDFS上的大文件,比如跟目录下的/user/atguigu/input/hadoop-2.7.2.tar.gz
> - 下载第一块
> - 下载第二块
> - 合并文件
```java
public class HdfsClient {
    /**
     * HDFS文件下载第一块
     */
    @Test
    public void readFileSeek1() throws Exception{
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取输入流
        final FSDataInputStream fis = fs.open(new Path("/user/atguigu/input/hadoop-2.7.2.tar.gz"));
        //3 创建输出流
        final FileOutputStream fos = new FileOutputStream(new File("d:/hadoop-2.7.2.tar.gz.part1"));
        //4 流拷贝
        byte[] buf = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            fis.read(buf);
            fos.write(buf);
        }
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    /**
     * HDFS文件下载第二块
     */
    @Test
    public void readFileSeek2() throws Exception{
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取输入流
        final FSDataInputStream fis = fs.open(new Path("/user/atguigu/input/hadoop-2.7.2.tar.gz"));
        //3 创建输出流
        final FileOutputStream fos = new FileOutputStream(new File("d:/hadoop-2.7.2.tar.gz.part2"));
        //4 流拷贝
        fis.seek(1024 * 1024 * 128);
        IOUtils.copyBytes(fis,fos,configuration);
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
}
```
> windows cmd进入D盘,执行如下命令,对数据进行合并
```shell script
type hadoop-2.7.2.tar.gz.part2 >> hadoop-2.7.2.tar.gz.part1
```
合并完成后,将hadoop-2.7.2.tar.gz.part1重新命名为hadoop-2.7.2.tar.gz,解压发现该tar包非常完整.

## 第四章 HDFS的数据流(面试重点)
### 4.1 HDFS写数据流程
#### 4.1.1 剖析文件写入
> HDFS写数据流程

![流程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojFI94BgOZiblpJHeNtAhvcVPwiaGibbrkrGMwkAf2TaxHqvkvulfEoCEjrQ/0?wx_fmt=png)

> 流程分析

1. 客户端通过Distributed FileSystem模块向NameNode请求上传文件,NameNode检查目标文件是否已存在,父目录是否存在.
2. NameNode返回是否可以上传.
3. 客户端请求第一个Block上传到哪几个DataNode服务器上.
4. NameNode返回3个DataNode节点,分别为dn1,dn2,dn3.
5. 客户端通过FSDataOutputStream模块请求dn1上传数据,dn1收到请求会继续调用dn2,然后dn2调用dn3,将这个通信管道建立完成.
6. dn1,dn2,dn3逐级应答客户端.
7. 客户端开始往dn1上传第一个Block(先从磁盘读取数据放到一个本地内存缓存),以Packet为单位,dn1收到一个Packet就会传给dn2,dn2传到dn3;dn1每传一个Packet会放入一个应答队列等待应答.
8. 当一个Block传输完成之后,客户端再次请求NameNode上传第二个Block的服务器(重复3-7步).

#### 4.1.2 网络拓扑-节点距离计算
> 在HDFS写数据的过程中,NameNode会选择举例待上传最近距离的DataNode接收数据.<br>
> 节点距离:两个节点到达最近的共同祖先的距离总和.

![网络拓扑](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojF07dp7PbTasEficnR8TaA8ooXuu42orr1R9NbMoSlbI1dRq6HELl7jlg/0?wx_fmt=png)

> 例如,假设有数据中心d1机架r1中的节点n1.该节点可以表示为d1/r1/n1,利用这种标记,这里给出四种距离描述.

![节点距离](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojF0bU0wLGnDUfSKgmxtms8ict6RhtXh91uSwicz5QN19uA622xFIrNNiapg/0?wx_fmt=png)

#### 4.1.3 机架感知(副本存储节点选择)
1. 官方IP地址,机架感知说明
> http://hadoop.apache.org/docs/r2.7.2/hadoop-project-dist/hadoop-hdfs/HdfsDesign.html#Data_Replication

> For the common case, when the replication factor is three, HDFS’s placement policy is to put one replica on one node in the local rack, another on a different node in the local rack, and the last on a different node in a different rack.


2. Hadoop2.7.2副本节点选择

![节点选择](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojF1NFrRzXdGtSeWvs1MdDyTMumZxHOInWaSh3dmDnjrg9iaeBgtL2CZyw/0?wx_fmt=png)

### 4.2 HDFS读数据流程
> HDFS读数据流程

![读流程](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojFMA1grfpKKxic66Yz8TPsy7vNw4k5aMIfgv6EVyFQOGYx76JeZFJrMgA/0?wx_fmt=png)

1. 客户端通过Distributed FileSystem向NameNode请求下载文件,NameNode通过查询元数据,找到文件块所在的DataNode地址.
2. 挑选一台DataNode(就近原则,然后随机)服务器,请求读取数据.
3. DataNode开始传输数据给客户端(从磁盘里面读取数据输入流),以Packet为单位做校验.
4. 客户端以Packet为单位接收,现在本地缓存,然后写入目标文件.

## 第五章 NameNode和SecondaryNameNode(面试重点)
### 5.1 NN和2NN工作机制
> 思考:NameNode中的元数据是存储在哪里的?

> 首先,我们做个假设,如果存在NameNode节点的磁盘中,因为经常需要进行随机访问,还有响应客户请求,必然效率过低,因此元数据需要存放在内存中,但如果只存在内存中,一旦断电,元数据丢失,就整个集群无法工作了,因此产生在磁盘中备份元数据的FsImage.<br>
> 这样又会带来新问题,当内存中元数据更新时,如果同时更新FsImage,就会导致效率过低,但如果不更新,就会发生一致性问题,一旦NameNode节点断电,就会产生数据丢失.因此引入Edits文件(只进行追加,效率很高),每当元数据有更新或添加元数据时,修改内存中的元数据并追加到Edits中.这样,一旦NameNode断电,可以通过FsImage和Edits的合并,合成元数据.<br>
> 但是如果长时间添加数据到Edits中,会导致该文件数据过大,效率降低,而且一旦断电,恢复元数据需要的时间过长,因此需要定期进行FsImage和Edits合并,如果这个操作有NameNode完成,又会效率过低,因此,引入一个新的节点SecondaryNameNode,专门用于FsImage和Edits的合并.

> NN和2NN工作机制

![机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojFicqCk0vVrl6Bqv4U0IOBhicq1W1icwibOVMUn6UrGicssl7m67yT6eyPVpA/0?wx_fmt=png)

1. 第一阶段:NameNode启动
> - 第一次启动NameNode格式化后,创建FsImage和Edits文件,如果不是第一次启动,直接加载编辑日志和镜像文件到内存.
> - 客户端对元数据进行增删改的请求.
> - NameNode记录操作日志,更新滚动日志.
> - NameNode在内存中对数据进行增删改.

2. 第二阶段:Secondary NameNode工作
> - Secondary NameNode询问NameNode是否需要CheckPoint,直接返回NameNode是否检查结果.
> - Secondary NameNode请求执行CheckPoint.
> - NameNode滚动正在写的Edits日志.
> - 将滚动前的编辑日志和镜像文件拷贝到Secondary NameNode.
> - Secondary NameNode加载编辑日志和镜像文件内存,并合并.
> - 生成新的镜像文件FsImage.chkPoint.
> - 拷贝FsImage.chkPoint到NameNode.
> - NameNode将FsImage.chkPoint重新命名为FsImage.

> NN和2NN工作机制详解:

> FsImage:NameNode内存中元数据序列化后形成的文件.<br>
> Edits:记录客户端更新元数据信息的每一步操作(可通过Edits运算出元数据).<br>
> NameNode启动时,先滚动Edits并生成一个空的edits.inprogress,然后加载Edits和FsImage到内存中,此时NameNode内存就持有最新的元数据信息,Client开始对NameNode发送元数据的增删改的请求,这些请求就会被记录到edits.inprogress中(查询元数据的操作不会被记录到Edits中,因为查询不会更改元数据信息),如果此时NameNode挂掉,重启后会从Edits中读取元数据的信息,然后NameNode会在内存中执行元数据的增删改的操作.<br>
> 由于Edits中记录的操作会越来越多,Edits文件会越来越大,导致NameNode在启动加载Edits时会很慢,所以需要对Edits和FsImage进行合并(所谓合并,就是将Edits和FsImage加载到内存中,照着Edits中的操作一步一步执行,最终形成新的FsImage).<br>
> Secondary NameNode的作用就是帮助NameNode进行Edits和FsImage的合并工作.<br>
> Secondary NameNode首先会询问NameNode是否需要CheckPoint(触发CHeckPoint需要满足两个条件中任意一个,定时时间到或Edits数据写满了),直接带回NameNode是否检查结果.Secondary NameNode执行CheckPoint操作,首先会让NameNode滚动Edits并生成一个空的edits.inprogress,滚动Edits的目的是给Edits打个标记,以后所有新的操作都写入edits.inprogress,其他未合并Edits和FsImage会拷贝到Secondary NameNode的本地,然后将拷贝的Edits和FsImage加载到内存中进行合并,生成FsImage.ChkPoint,然后将FsImage.ChkPoint拷贝给NameNode重名为FsImage后替换原来的FsImage.<br>
> NameNode在启动时就只需要加载之前未合并的Edits和FsImage即可,因为合并过的Edits中的元数据信息已经被记录在FsImage中.

### 5.2 FsImage和Edits解析
1. 概念

![概念](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojFoOLm8gLjQVo8icNG89vibwR6pqegywpL9ictiaY5HwtTicJv1oRQY09SV4Q/0?wx_fmt=png)

2. oiv查看FsImage文件
> 查看oiv和oev命令

```shell script
[root@hadoop100 current]# hdfs 
  oiv                  apply the offline fsimage viewer to an fsimage
  oev                  apply the offline edits viewer to an edits file
[root@hadoop100 current]# 
```

> 基本语法

```shell script
hdfs oiv -p 文件类型 -i镜像文件 -o 转换后文件输出路径
```

> 案例实操

> - 格式化FsImage文件

```shell script
[root@hadoop100 current]# pwd
/opt/module/hadoop-2.7.2/data/tmp/dfs/name/current
[root@hadoop100 current]# hdfs oiv -p XML -i fsimage_0000000000000000140 -o /opt/module/hadoop-2.7.2/fsimage.xml
[root@hadoop100 current]# 
```

> - 导入idea的f.xml进行格式化

```xml
<xml>
<inode>
	<id>16386</id>
	<type>DIRECTORY</type>
	<name>user</name>
	<mtime>1512722284477</mtime>
	<permission>atguigu:supergroup:rwxr-xr-x</permission>
	<nsquota>-1</nsquota>
	<dsquota>-1</dsquota>
</inode>
<inode>
	<id>16389</id>
	<type>FILE</type>
	<name>wc.input</name>
	<replication>3</replication>
	<mtime>1512722322219</mtime>
	<atime>1512722321610</atime>
	<perferredBlockSize>134217728</perferredBlockSize>
	<permission>atguigu:supergroup:rw-r--r--</permission>
	<blocks>
		<block>
			<id>1073741825</id>
			<genstamp>1001</genstamp>
			<numBytes>59</numBytes>
		</block>
	</blocks>
</inode>
</xml>
```

> FsImage中没有记录块所对应的DataNode,为什么?<br>
> 在集群启动后,要求DataNode上报数据块信息,并间隔一段时间后再次上报.

3. oev查看Edits文件
> 基础语法

```shell script
hdfs oev -p 文件类型 -i编辑日志 -o 转换后文件输出路径
```

> 案例实操

> - 格式化Edits文件

```shell script
[root@hadoop100 current]# pwd
/opt/module/hadoop-2.7.2/data/tmp/dfs/name/current
[root@hadoop100 current]# hdfs oev -p XML -i edits_0000000000000000141-0000000000000000141 -o /opt/module/hadoop-2.7.2/edits.xml
[root@hadoop100 current]# 
```

> - 导入idea的e.xml进行格式化

```xml
<?xml version="1.0" encoding="UTF-8"?>
<EDITS>
  <EDITS_VERSION>-63</EDITS_VERSION>
  <RECORD>
    <OPCODE>OP_START_LOG_SEGMENT</OPCODE>
    <DATA>
      <TXID>141</TXID>
    </DATA>
  </RECORD>
</EDITS>
```

### 5.3 CheckPoint时间设置
> hdfs-default.xml中配置

1. 通常情况下,SecondaryNameNode每隔一小时执行一次

```xml
<configuration>
    <property>
        <name>dfs.namenode.checkpoint.period</name>
        <value>3600</value>
    </property>
</configuration>
```
2. 一分钟检查一次操作次数,当操作次数达到1百万,SecondaryNameNode执行一次

```xml
<configuration>
    <property>
        <name>dfs.namenode.checkpoint.txns</name>
        <value>1000000</value>
        <description>操作动作次数</description>
    </property>

    <property>
        <name>dfs.namenode.checkpoint.check.period</name>
        <value>60</value>
        <description> 1分钟检查一次操作次数</description>
    </property >
</configuration>
```

### 5.4 NameNode故障处理

> NameNode故障后,可以采用如下两种方法恢复数据

> - 将SecondaryNameNode中数据拷贝到NameNode存储数据的目录

1. kill -9 NameNode进程

```shell script
[root@hadoop100 current]# jps
6881 NodeManager
7108 NameNode
7209 DataNode
7692 Jps
[root@hadoop100 current]# kill -9 7108
[root@hadoop100 current]# 
```

2. 删除NameNode存储的数据(/opt/module/hadoop-2.7.2/data/tmp/dfs/name/*)

```shell script
[root@hadoop100 hadoop-2.7.2]# rm -rf /opt/module/hadoop-2.7.2/data/tmp/dfs/name/*
[root@hadoop100 hadoop-2.7.2]# 
```

3. 拷贝SecondaryNameNode中的数据到原NameNode存储数据目录

```shell script
[root@hadoop102 namesecondary]# jps
7025 DataNode
7339 Jps
6876 NodeManager
7116 SecondaryNameNode
[root@hadoop102 namesecondary]# pwd
/opt/module/hadoop-2.7.2/data/tmp/dfs/namesecondary

[root@hadoop100 hadoop-2.7.2]# cd data/tmp/dfs/name/
[root@hadoop100 name]# scp -r root@hadoop102:/opt/module/hadoop-2.7.2/data/tmp/dfs/namesecondary/* ./   
[root@hadoop100 name]# 
```

4. 重新启动NameNode
```shell script
[root@hadoop100 name]# cd /opt/module/hadoop-2.7.2/
[root@hadoop100 hadoop-2.7.2]# sbin/hadoop-daemon.sh start namenode
starting namenode, logging to /opt/module/hadoop-2.7.2/logs/hadoop-root-namenode-hadoop100.out
[root@hadoop100 hadoop-2.7.2]# 
```



> - 使用-importCheckpoint选项启动NameNode守护进程,从而将SecondaryNameNode目录中
1. 修改hdfs-site.xml中的

```xml
<configuration>
    <property>
        <name>dfs.namenode.checkpoint.period</name>
        <value>120</value>
    </property>

    <property>
        <name>dfs.namenode.name.dir</name>
        <value>/opt/module/hadoop-2.7.2/data/tmp/dfs/name</value>
    </property>
</configuration>
```
2. kill -9 NameNode进程

```shell script
[root@hadoop100 current]# jps
6881 NodeManager
7108 NameNode
7209 DataNode
7692 Jps
[root@hadoop100 current]# kill -9 7108
[root@hadoop100 current]# 
```

3. 删除NameNode存储的数据(/opt/module/hadoop-2.7.2/data/tmp/dfs/name/*)

```shell script
[root@hadoop100 hadoop-2.7.2]# rm -rf /opt/module/hadoop-2.7.2/data/tmp/dfs/name/*
[root@hadoop100 hadoop-2.7.2]# 
```

4. 如果SecondaryNameNode不和NameNode在一个主机节点上,需要将SecondaryNameNode存储数据的目录拷贝到NameNode存储数据的平级目录,并删除in_use.lock文件

```shell script
[root@hadoop100 dfs]# ll
总用量 0
drwx------. 3 root root 40 5月   3 22:33 data
drwxr-xr-x. 3 root root 40 5月   3 23:19 name
[root@hadoop100 dfs]# scp -r root@hadoop102:/opt/module/hadoop-2.7.2/data/tmp/dfs/namesecondary/* ./   
[root@hadoop100 dfs]# 
[root@hadoop100 namesecondary]$ rm -rf in_use.lock
```

5. 导入检查点数据(等待一会ctrl+c结束掉,会自动拉起NameNode)

```shell script
[root@hadoop100 hadoop-2.7.2]# bin/hdfs namenode -importCheckpoint
```
6. 启动NameNode
```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/hadoop-daemon.sh start namenode
```

### 5.5 集群安全模式
1. 概述

![概述](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojFicwFA9JFbSFETblXDWMocHQ61XZfbpUQxCvRHib89nAE81KtC0Xwzyew/0?wx_fmt=png)

2. 基本语法

> 集群处于安全模式,不能执行重要操作(写操作),集群启动完成后,自动退出安全模式.
> - bin/hdfs dfsadmin -safemode get		(功能描述:查看安全模式状态)
> - bin/hdfs dfsadmin -safemode enter  	(功能描述:进入安全模式状态)
> - bin/hdfs dfsadmin -safemode leave	(功能描述:离开安全模式状态)
> - bin/hdfs dfsadmin -safemode wait	(功能描述:等待安全模式状态)

3. 案例

> 模拟等待安全模式
> - 查看当前模式

```shell script
[root@hadoop100 hadoop-2.7.2]# bin/hdfs dfsadmin -safemode get
Safe mode is OFF
[root@hadoop100 hadoop-2.7.2]# 
```

> - 先进入安全模式

```shell script
[root@hadoop100 hadoop-2.7.2]# bin/hdfs dfsadmin -safemode enter
Safe mode is ON
[root@hadoop100 hadoop-2.7.2]# 
```

> - 创建并执行下面脚本

```shell script
# 在/opt/module/hadoop-2.7.2 路径下,编辑一个脚本safemode.sh
[root@hadoop100 hadoop-2.7.2]# bin/hdfs dfsadmin -safemode enter
Safe mode is ON
[root@hadoop100 hadoop-2.7.2]# touch safemode.sh
[root@hadoop100 hadoop-2.7.2]# vi safemode.sh 
#!/bin/bash
hdfs dfsadmin -safemode wait
hdfs dfs -put /opt/module/hadoop-2.7.2/README.txt /
~
"safemode.sh" 3L, 93C written
[root@hadoop100 hadoop-2.7.2]# chmod +x safemode.sh 
[root@hadoop100 hadoop-2.7.2]# ./safemode.sh 
#等待中....
```

> - 再打开一个窗口,执行

```shell script
[root@hadoop100 hadoop-2.7.2]# bin/hdfs dfsadmin -safemode leave
Safe mode is OFF
[root@hadoop100 hadoop-2.7.2]# 
```

> - 观察

```shell script
[root@hadoop100 hadoop-2.7.2]# ./safemode.sh 

Safe mode is OFF
put: `/README.txt': File exists
[root@hadoop100 hadoop-2.7.2]# 
```

### 5.6 NameNode多目录配置
1. NameNode的本地目录可以配置成多个,且每个目录存放内容相同,增加了可靠性.
2. 具体配置如下

> - 在hdfs-site.xml文件中增加如下内容

```xml
<configuration>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:///${hadoop.tmp.dir}/dfs/name1,file:///${hadoop.tmp.dir}/dfs/name2</value>
    </property>
</configuration>
```

```shell script
[root@hadoop100 hadoop-2.7.2]# xsync etc/hadoop/
```

> - 停止集群,删除data和logs中所有数据

```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/stop-dfs.sh 
Stopping namenodes on [hadoop100]
hadoop100: stopping namenode
hadoop101: stopping datanode
hadoop100: stopping datanode
hadoop102: stopping datanode
Stopping secondary namenodes [hadoop102]
hadoop102: stopping secondarynamenode
[root@hadoop100 hadoop-2.7.2]# jps
8521 Jps

[root@hadoop101 hadoop-2.7.2]# sbin/stop-yarn.sh 
stopping yarn daemons
stopping resourcemanager
hadoop101: no nodemanager to stop
hadoop102: no nodemanager to stop
hadoop100: no nodemanager to stop
no proxyserver to stop
[root@hadoop101 hadoop-2.7.2]# jps

[root@hadoop100 hadoop-2.7.2]# rm -fr data/ logs/
[root@hadoop101 hadoop-2.7.2]# rm -fr data/ logs/
[root@hadoop102 hadoop-2.7.2]# rm -fr data/ logs/
```

> - 格式化集群并启动

```shell script
[root@hadoop100 hadoop-2.7.2]# bin/hdfs namenode -format
```

> - 查看结果

```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/start-dfs.sh 
[root@hadoop100 hadoop-2.7.2]# ll data/tmp/dfs/
总用量 0
drwx------. 3 root root 40 5月   4 00:00 data
drwxr-xr-x. 3 root root 40 5月   4 00:00 name1
drwxr-xr-x. 3 root root 40 5月   4 00:00 name2
[root@hadoop100 hadoop-2.7.2]# 
```

## 第六章 DataNode(面试开发重点)
### 6.1 DataNode工作机制
> DataNode工作机制

![工作机制](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicaotxfic9ItUAOjxR6VLia0cMSYG8aAsyLRI4jLCTMwQHBevxTkvrGYIA/0?wx_fmt=png)

> - 一个数据块在DataNode上以文件形式存储在磁盘上,包括两个文件,一个数据文件本身,一个元数据包括数据块的长度,块数据的校验和,以及时间戳.
> - DataNode启动后向NameNode注册,通过后,周期性(1h)的向NameNode上报所有的块信息.
> - 心跳是每3秒一次,心跳返回结果带有NameNode给该DataNode的命令如复制块数据到另一台机器,或删除某个数据库,如果超过10分钟没有收到某个DataNode的心跳,则认为该节点不可用.
> - 集群运行中可以安全加入和退出一些机器.

### 6.2 数据完整性

![数据完整性](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicWISBrSriaMiax0AYU0y3VllOkd456lAiaBdMzhWtRANXbZ3ytHv8Rciccw/0?wx_fmt=png)
 
> 思考:如果电脑磁盘里面存储的数据是控制高铁信号灯的红灯信号(1)和绿灯信号(0),但是存储该数据的磁盘坏了.一直显示是绿灯,是否很危险?同理DataNode节点上的数据损坏了,却没有发现,是否也很危险,那么如何解决呢?<br>
> 如下是DataNode节点保证数据完整性的方法
> - 当DataNode读取Block的时候,它会计算CheckSum.
> - 如果计算后的CheckSum,与Block创建时值不一样,说明Block已经损坏.
> - Client读取其他DataNode上的Block.
> - DataNode在其文件创建后周期验证CheckSum.

### 6.3 掉线时限参数设置

![时限](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBickIg8BvOByCicxmCZOdcZM7jrvT6muUF37l7vhBj6poCekE06LTBFwfA/0?wx_fmt=png)

> 需要注意的是hdfs-site.xml配置文件中的heartbeat.recheck.interval的单位为毫秒,dfs.heartbeat.interval的单位为秒.

```xml
<configuration>
    <property>
        <name>dfs.namenode.heartbeat.recheck-interval</name>
        <value>300000</value>
    </property>
    <property>
        <name>dfs.heartbeat.interval</name>
        <value>3</value>
    </property>
</configuration>
```

### 6.4 服役新数据节点
> 需求:随着业务增长,数据量越来越大,原有的数据节点容量已经不满足,需要在原有基础上动态添加新的数据节点.

1. 环境准备:

> - 在hadoop102主机上再克隆一台hadoop103主机
> - 修改IP地址和主机名称
> - 删除原来HDFS文件系统留存的文件(/opt/module/hadoop-2.7.2/data和log)
> - 重启服务器

2. 服役新节点具体步骤

> - 直接启动DataNode,即可关联到集群

```shell script
[root@hadoop103 hadoop-2.7.2]# sbin/hadoop-daemon.sh start datanode
[root@hadoop103 hadoop-2.7.2]# sbin/yarn-daemon.sh start nodemanager
```

> - 在hadoop103上上传文件

```shell script
[root@hadoop103 hadoop-2.7.2]# hadoop fs -put /opt/module/hadoop-2.7.2/LICENSE.txt /
```

> - 如果数据不均衡,可以用命令实现集群的再平衡

```shell script
[root@hadoop103 hadoop-2.7.2]#  sbin/start-balancer.sh
```

### 6.5 服役旧数据节点
#### 6.5.1 添加白名单
> 添加到白名单的主机节点,都允许访问NameNode,不在白名单的主机节点,都会被退出.

> 配置白名单的具体步骤
> - 在NameNode的/opt/module/hadoop-2.7.2/etc/hadoop目录下创建dfs.hosts文件

```shell script
[root@hadoop100 hadoop-2.7.2]# pwd
/opt/module/hadoop-2.7.2/etc/hadoop
[root@hadoop100 hadoop-2.7.2]# touch dfs.hosts
[root@hadoop100 hadoop-2.7.2]# vi dfs.hosts
#添加如下主机名称,不包含hadoop103
hadoop100
hadoop101
hadoop102
```

> - 在NameNode的hdfs-site.xml配置文件中增加dfs.hosts属性

```xml
<configuration>
    <property>
        <name>dfs.hosts</name>
        <value>/opt/module/hadoop-2.7.2/etc/hadoop/dfs.hosts</value>
    </property>
</configuration>
```

> - 配置文件分发

```shell script
[root@hadoop100 hadoop]# xsync hdfs-site.xml
```

> - 刷新NameNode,更新ResourceManager节点

```shell script
[root@hadoop100 hadoop-2.7.2]# hdfs dfsadmin -refreshNodes
[root@hadoop101 hadoop-2.7.2]# yarn rmadmin -refreshNodes
```

> - 在web浏览器上查看(http://hadoop100:50070/)
> - 如果数据不均衡,可以用命令实现集群的再平衡

```shell script
[root@hadoop100 hadoop-2.7.2]# ./start-balancer.sh
```

#### 6.5.2 黑名单退役
> 在黑名单上面的主机都会被强制退出

> - 在NameNode的/opt/module/hadoop-2.7.2/etc/hadoop目录下创建dfs.hosts.exclude文件

```shell script
[root@hadoop100 hadoop]# pwd
/opt/module/hadoop-2.7.2/etc/hadoop
[root@hadoop100 hadoop]# touch dfs.hosts.exclude
[root@hadoop100 hadoop]# vi dfs.hosts.exclude
#添加如下主机名称（要退役的节点）
hadoop103
```

> - 在NameNode的hdfs-site.xml配置文件中增加dfs.hosts.exclude属性

```xml
<configuration>
    <property>
        <name>dfs.hosts.exclude</name>
        <value>/opt/module/hadoop-2.7.2/etc/hadoop/dfs.hosts.exclude</value>
    </property>
</configuration>
```

> - 刷新NameNode,刷新ResourceManager

```shell script
[root@hadoop100 hadoop-2.7.2]# hdfs dfsadmin -refreshNodes
[root@hadoop101 hadoop-2.7.2]# yarn rmadmin -refreshNodes
```

> - 检查Web浏览器,退役节点的状态为decommission in progress(退役中),说明数据节点正在复制块到其他节点.
> - 等待退役节点状态为decommissioned(所有块已经复制完成)停止该节点及节点资源管理器.注意:如果副本数是3,服役的节点小于等于3,是不能退役成功的,需要修改副本数后才能退役
> - 停止退役节点服务

```shell script
[root@hadoop103 hadoop-2.7.2]# sbin/hadoop-daemon.sh stop datanode
[root@hadoop103 hadoop-2.7.2]# sbin/yarn-daemon.sh stop nodemanager
```

> - 如果数据不均衡,可以用命令实现集群的再平衡

```shell script
[root@hadoop100 hadoop-2.7.2]# ./start-balancer.sh
```

### 6.6 DataNode多目录配置
> DataNode也可以配置成多个目录,每个目录存储的数据不一样.即:数据不是副本
> - hdfs-site.xml

1. 修改配置文件

```xml
<configuration>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:///${hadoop.tmp.dir}/dfs/data1,file:///${hadoop.tmp.dir}/dfs/data2</value>
    </property>
</configuration>
```

2. 停止进程

```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/stop-dfs.sh 
[root@hadoop101 hadoop-2.7.2]# sbin/stop-yarn.sh 
```
3. 删除数据

```shell script
[root@hadoop100 hadoop-2.7.2]# rm -fr data/ logs/
[root@hadoop101 hadoop-2.7.2]# rm -fr data/ logs/
[root@hadoop102 hadoop-2.7.2]# rm -fr data/ logs/
```

4. 格式化NameNode节点

```shell script
[root@hadoop100 hadoop-2.7.2]# bin/hdfs namenode -format
```

5. 重启服务

```shell script
[root@hadoop100 hadoop-2.7.2]# sbin/start-dfs.sh 
[root@hadoop101 hadoop-2.7.2]# sbin/start-yarn.sh 
```

## 第七章 HDFS2.X新特性
### 7.1 集群间数据拷贝
1. scp实现两个远程主机之间的文件复制

> - scp -r hello.txt root@hadoop103:/hello.txt	 //推push
> - scp -r root@hadoop103:/hello.txt  hello.txt //拉pull
> - scp -r root@hadoop103:/hello.txt root@hadoop104:/ //通过本地主机中转实现两个远程主机的文件复制:如果在两个远程主机之间ssh没有配置的情况下可以使用该方式.

2. 采用distcp命令实现两个Hadoop集群之间的递归数据复制

```shell script
[root@hadoop100 hadoop-2.7.2]#  bin/hadoop distcp
hdfs://haoop102:9000/user/atguigu/hello.txt hdfs://hadoop103:9000/user/atguigu/hello.txt
```

### 7.2 小文件存档

![小文件存档](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicHBqtywkQticagsQdz3JS68HKPadB9xpRicmkARhFiazSRZibVojNKNgJ5A/0?wx_fmt=png)

> 操作实例
> - 需要启动YARN进程
> - 归档文件
> - 查看归档
> - 解归档文件

```shell script
# 启动yarn和准备文件
[root@hadoop101 hadoop-2.7.2]# sbin/start-yarn.sh 
[root@hadoop102 hadoop-2.7.2]# hadoop fs -mkdir -p /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# cp kongming.txt kongming1.txt 
[root@hadoop102 hadoop-2.7.2]# cp kongming.txt kongming2.txt 
[root@hadoop102 hadoop-2.7.2]# hadoop fs -put kongming.txt /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# hadoop fs -put kongming1.txt /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# hadoop fs -put kongming2.txt /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /user/atguigu/input
Found 3 items
-rw-r--r--   3 root supergroup         14 2020-05-04 16:18 /user/atguigu/input/kongming.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:19 /user/atguigu/input/kongming1.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:19 /user/atguigu/input/kongming2.txt
[root@hadoop102 hadoop-2.7.2]# 
# 归档文件
[root@hadoop102 hadoop-2.7.2]# hadoop archive -archiveName input.har -p /user/atguigu/input /user/atguigu/output
# 查看归档
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls /user/atguigu/output/input.har
Found 4 items
-rw-r--r--   3 root supergroup          0 2020-05-04 16:21 /user/atguigu/output/input.har/_SUCCESS
-rw-r--r--   5 root supergroup        296 2020-05-04 16:21 /user/atguigu/output/input.har/_index
-rw-r--r--   5 root supergroup         23 2020-05-04 16:21 /user/atguigu/output/input.har/_masterindex
-rw-r--r--   3 root supergroup         42 2020-05-04 16:21 /user/atguigu/output/input.har/part-0
[root@hadoop102 hadoop-2.7.2]# hadoop fs -ls har:///user/atguigu/output/input.har
-rw-r--r--   3 root supergroup         14 2020-05-04 16:18 har:///user/atguigu/output/input.har/kongming.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:19 har:///user/atguigu/output/input.har/kongming1.txt
-rw-r--r--   3 root supergroup         14 2020-05-04 16:19 har:///user/atguigu/output/input.har/kongming2.txt
[root@hadoop102 hadoop-2.7.2]# 
# 解归档文件
[root@hadoop102 hadoop-2.7.2]# hadoop fs -cp har:///user/atguigu/output/input.har/* /

```

### 7.3 回收站

1. 回收站参数设置及工作机制

![回收站](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicHOibwGZgJRNAGO4Ix7wN7D7czPDYkcDFPGDw3gB8RtMg35tgiaG6SFRQ/0?wx_fmt=png)

2. core-site.xml中启用回收站,设置访问垃圾回收站用户名称,配置垃圾回收时间为1分钟,分发配置文件

```xml
<configuration>
    <property>
        <name>fs.trash.interval</name>
        <value>1</value>
    </property>
    <property>
        <name>hadoop.http.staticuser.user</name>
        <value>root</value>
    </property>
</configuration>
```

```shell script
[root@hadoop102 hadoop]# xsync core-site.xml
```

3. 查看回收站(删除时候会看见路径)
4. 通过程序删除的文件不会经过回收站，需要调用moveToTrash()才进入回收站

> - Trash trash = New Trash(conf);
> - trash.moveToTrash(path);

5. 恢复回收站数据

> hadoop fs -mv 回收站文件路径  待恢复路径

```shell script
[root@hadoop102 hadoop]# hadoop fs -mv /user/atguigu/.Trash/Current/user/atguigu/input    /user/atguigu/input
```

6. 清空回收站(类似归档)

```shell script
[root@hadoop102 hadoop]# hadoop fs -expunge
```

### 7.4 快照管理

![快照](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q14FCiakTiccydib1EDVrdlSBicLq80RQbrylBhV3C7DdA18EicWad1HCicYOC5iaBmFcUhibak3lueoRUwPw/0?wx_fmt=png)

1. 开启/禁用指定目录的快照功能

```shell script
[root@hadoop102 hadoop-2.7.2]# hdfs dfsadmin -allowSnapshot /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# hdfs dfsadmin -disallowSnapshot /user/atguigu/input
```

2. 对目录创建快照

> 通过web访问hdfs://hadoop100:50070/user/atguigu/input/.snapshot/s…..// 快照和源文件使用相同数据

```shell script
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -createSnapshot /user/atguigu/input
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -ls /user/atguigu/input/.snapshot/
```

3. 指定名称创建快照

```shell script
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -createSnapshot /user/atguigu/input  miao170508
```

4. 重命名快照

```shell script
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -renameSnapshot /user/atguigu/input/  miao170508 atguigu170508
```

5. 列出当前用户所有可快照目录

```shell script
[root@hadoop102 hadoop-2.7.2]#  hdfs lsSnapshottableDir
```

6. 比较两个快照目录的不同之处

```shell script
[root@hadoop102 hadoop-2.7.2]# hdfs snapshotDiff /user/atguigu/input/  .  .snapshot/atguigu170508
```

7. 恢复快照

```shell script
[root@hadoop102 hadoop-2.7.2]# hdfs dfs -cp /user/atguigu/input/.snapshot/s20170708-134303.027 /user
```




  


