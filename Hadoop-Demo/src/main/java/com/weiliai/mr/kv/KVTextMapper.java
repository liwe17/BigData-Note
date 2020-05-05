package com.weiliai.mr.kv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: KV Mapper实现类
 */
public class KVTextMapper extends Mapper<Text,Text,Text, IntWritable> {


    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

        //1. 读取每一行,无需切割,直接设置
        context.write(key,new IntWritable(1));
    }
}
