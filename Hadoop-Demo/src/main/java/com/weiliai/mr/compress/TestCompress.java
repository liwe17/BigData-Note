package com.weiliai.mr.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: Doug Li
 * @Date 2020/5/18
 * @Describe: 测试压缩
 */
public class TestCompress {

    public static void main(String[] args) throws Exception {

        compress("d:/hello.txt","org.apache.hadoop.io.compress.BZip2Codec");
        decompress("d:/hello.txt.bz2");

    }

    //1. 压缩
    private static void compress(String filename,String method) throws Exception{
        
        //1. 获取输入流
        FileInputStream fis = new FileInputStream(new File(filename));

        Class codecClass = Class.forName(method);

        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, new Configuration());

        //2. 获取输出流
        FileOutputStream fos = new FileOutputStream(new File(filename+codec.getDefaultExtension()));
        CompressionOutputStream cos = codec.createOutputStream(fos);

        //3. 流拷贝
        IOUtils.copyBytes(fis,cos,1024*1024*5,false);

        //4. 关闭资源
        cos.close();
        fos.close();
        fis.close();
    }

    //2. 解压缩
    private static void decompress(String filename) throws Exception{

        //1. 校验是否能解压缩
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());

        CompressionCodec codec = factory.getCodec(new Path(filename));

        if(null == codec){
            System.out.println("can not find codec for file "+ filename);
            return;
        }

        //2. 获取输入流
        CompressionInputStream cis = codec.createInputStream(new FileInputStream(new File(filename)));

        //3. 获取输出流
        FileOutputStream fos = new FileOutputStream(new File(filename + ".decoded"));

        //4. 流的对拷
        IOUtils.copyBytes(cis,fos,1024*1024*5,false);

        //5. 关闭资源
        cis.close();
        fos.close();
    }

}
