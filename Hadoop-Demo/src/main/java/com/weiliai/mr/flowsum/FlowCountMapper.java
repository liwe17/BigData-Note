package com.weiliai.mr.flowsum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/4
 * @Describe: Flow的Mapper
 */
public class FlowCountMapper extends Mapper<LongWritable,Text,Text,FlowBean> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 获取每行内容,按照"\t"切割
        String[] fields = value.toString().split("\t");
        //2. 获取对应的值
        context.write(new Text(fields[1]),new FlowBean(Long.parseLong(fields[fields.length-3]),Long.parseLong(fields[fields.length-2]),0L));
    }
}
