package com.weiliai.mr.customize.output;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/13
 * @Describe: RecordWriter实现类
 */
public class FilterRecordWriter extends RecordWriter<Text, NullWritable> {

    FSDataOutputStream fsAtGuiGuStream = null;
    FSDataOutputStream fsOtherStream = null;

    public FilterRecordWriter(TaskAttemptContext job) {
        //1. 获取文件系统
        FileSystem fs;
        try {
            fs = FileSystem.get(job.getConfiguration());

            //2. 创建输出文件路径
            Path atGuiGuPath = new Path("d:/atguigu.txt");
            Path otherPath = new Path("d:/other.txt");
            //3. 创建输入流
            fsAtGuiGuStream = fs.create(atGuiGuPath);
            fsOtherStream = fs.create(otherPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        //判断是否包含atguigu输出到不同的文件
        if(key.toString().contains("atguigu")){
            fsAtGuiGuStream.write(key.toString().getBytes());
//            fsAtGuiGuStream.write(key.getBytes());
        }else{
            fsOtherStream.write(key.toString().getBytes());
//            fsOtherStream.write(key.getBytes());
        }

    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        // 关闭资源
        IOUtils.closeStream(fsAtGuiGuStream);
        IOUtils.closeStream(fsOtherStream);
    }
}
