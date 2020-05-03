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

### 1.4 HDFS文件块大小
> HDFS文件块大小

![块大小1](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxI3VfqT53yiblIxLDUw38USFAbQbcdqxBfdOicefEKqn85MIlIsoTzcv1g/0?wx_fmt=png)
![块大小2](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q0W0ibC4aKsIHekbhW3aKLxIqOx5sBYykTyDYGvMwHjsj1Y7vJFsLcicoqYAwekWCqgfUOqg87rVkGQ/0?wx_fmt=png)

## 第二章 HDFS的Shell操作

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

## 第三章 HDFS客户端操作
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
#### 4.1 剖析文件写入
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

#### 4.2 网络拓扑-节点距离计算
> 在HDFS写数据的过程中,NameNode会选择举例待上传最近距离的DataNode接收数据.<br>
> 节点距离:两个节点到达最近的共同祖先的距离总和.

![网络拓扑](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojF07dp7PbTasEficnR8TaA8ooXuu42orr1R9NbMoSlbI1dRq6HELl7jlg/0?wx_fmt=png)

> 例如,假设有数据中心d1机架r1中的节点n1.该节点可以表示为d1/r1/n1,利用这种标记,这里给出四种距离描述.

![节点距离](https://mmbiz.qpic.cn/mmbiz_png/bHb4F3h61q1iabbKdhOlI5QdsICMxmojF0bU0wLGnDUfSKgmxtms8ict6RhtXh91uSwicz5QN19uA622xFIrNNiapg/0?wx_fmt=png)

#### 4.3 机架感知(副本存储节点选择)


