package com.weiliai.mr.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/21
 * @Describe: Mapper实现类
 */
public class TwoIndexMapper extends Mapper<LongWritable, Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //1. 切割
        String[] fields = value.toString().split("--");
        //2. 输出
        context.write(new Text(fields[0]),new Text(fields[1]));
    }
}
