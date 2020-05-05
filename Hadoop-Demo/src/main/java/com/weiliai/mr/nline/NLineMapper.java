package com.weiliai.mr.nline;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: NLine Mapper实现类
 */
public class NLineMapper extends Mapper<LongWritable, Text,Text, IntWritable> {


    @Override
    protected void map(LongWritable key, Text value, Context context){
        //1. 每行读取,按照空格切分
        //2. 循环输出
        Stream.of(value.toString().split(" ")).forEach(word -> {
            try {
                context.write(new Text(word),new IntWritable(1));
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
