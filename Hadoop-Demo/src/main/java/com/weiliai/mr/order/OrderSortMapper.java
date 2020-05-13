package com.weiliai.mr.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/10
 * @Describe: mapper实现类
 */
public class OrderSortMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 获取每行内容,拼接字段
        String[] fields = value.toString().split("\t");

        //2. 写入
        context.write(new OrderBean(Integer.parseInt(fields[0]),Double.parseDouble(fields[2])),NullWritable.get());
    }
}
