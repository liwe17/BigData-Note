package com.weiliai.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

/**
 * @Author: Doug Li
 * @Date 2020/5/3
 * @Describe: HDFS客户端
 */
public class HdfsClient {

    /**
     * 测试创建文件夹
     */
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

    /**
     * HDFS文件上传-测试参数优先级
     */
    @Test
    public void testCopyFromLocalFile() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
//        configuration.set("dfs.replication","2");
        //配置在集群上运行
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 创建目录
        fs.copyFromLocalFile(new Path("d:/banzhang.txt"), new Path("/banzhang3.txt"));
        //3 关闭流
        fs.close();
    }

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
        fs.copyToLocalFile(false, new Path("/banzhang.txt"), new Path("d:/banhua.txt"), true);
        //3 关闭资源
        fs.close();
    }

    /**
     * 测试HDFS文件夹删除
     */
    @Test
    public void testDelete() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 执行操作
        fs.delete(new Path("/1109"), true);
        //3 关闭资源
        fs.close();
    }

    /**
     * 测试HDFS文件名更改
     */
    @Test
    public void testRename() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 执行操作
        fs.rename(new Path("/banzhang.txt"), new Path("/banhua.txt"));
        //3 关闭资源
        fs.close();
    }

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
        while (listFiles.hasNext()) {
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
            if (fileStatus.isDirectory()) {
                System.out.println("目录:" + fileStatus.getPath().getName());
                continue;
            }
            System.out.println("文件:" + fileStatus);
        }
        //3 关闭资源
        fs.close();
    }


    /**
     * HDFS文件上传
     */
    @Test
    public void putFileToHDFS() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 创建输入流
        final FileInputStream fis = new FileInputStream(new File("d:/banhua.txt"));
        //3 获取输出流
        final FSDataOutputStream fos = fs.create(new Path("/banhua1.txt"));
        //4 流拷贝
        IOUtils.copyBytes(fis, fos, configuration);
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }


    /**
     * HDFS文件下载
     */
    @Test
    public void getFileToHDFS() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取输入流
        final FSDataInputStream fis = fs.open(new Path("/banhua1.txt"));
        //3 创建输出流
        final FileOutputStream fos = new FileOutputStream(new File("d:/banhua1.txt"));
        //4 流拷贝
        IOUtils.copyBytes(fis, fos, configuration);
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    /**
     * HDFS文件下载第一块
     */
    @Test
    public void readFileSeek1() throws Exception {
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
    public void readFileSeek2() throws Exception {
        //1 获取文件系统
        final Configuration configuration = new Configuration();
        final FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), configuration, "root");
        //2 获取输入流
        final FSDataInputStream fis = fs.open(new Path("/user/atguigu/input/hadoop-2.7.2.tar.gz"));
        //3 创建输出流
        final FileOutputStream fos = new FileOutputStream(new File("d:/hadoop-2.7.2.tar.gz.part2"));
        //4 流拷贝,设置起始点
        fis.seek(1024 * 1024 * 128);
        IOUtils.copyBytes(fis, fos, configuration);
        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
}
