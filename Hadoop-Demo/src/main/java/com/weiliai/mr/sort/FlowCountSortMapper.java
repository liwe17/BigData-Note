package com.weiliai.mr.sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: Doug Li
 * @Date 2020/5/8
 * @Describe: Mapper 实现类
 */
public class FlowCountSortMapper extends Mapper<LongWritable, Text,FlowBean,Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 获取一行
        String line = value.toString();
        //2. 切割
        String[] fields = line.split("\t");
        //3. 封装对象并输出
        context.write(new FlowBean(Long.parseLong(fields[1]),Long.parseLong(fields[2])),new Text(fields[0]));
    }
}
