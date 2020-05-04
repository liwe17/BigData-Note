package com.weiliai.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: Doug Li
 * @Date 2020/5/4
 * @Describe: 单词统计mapper类
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    /**
     * 1. 读取每一行数据
     * 2. 按照空格分割字符串
     * 3. 返回处理结果
     * @param key 偏移量,一行
     * @param value 每行的数据
     * @param context 返回的上下文
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) {
        Arrays.stream(value.toString().split(" ")).forEach(word ->{
            try {
                context.write(new Text(word),new IntWritable(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
