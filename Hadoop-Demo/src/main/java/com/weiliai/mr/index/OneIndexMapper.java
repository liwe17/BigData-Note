package com.weiliai.mr.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/21
 * @Describe: Mapper实现类
 */
public class OneIndexMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private String fileName;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取文件名称
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        fileName = fileSplit.getPath().getName();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 切割每行内容
        String[] fields = value.toString().split(" ");
        //2. 遍历输出
        for (String field : fields) {
            context.write(new Text(String.join("",field,"--",fileName)),new IntWritable(1));
        }

    }
}
