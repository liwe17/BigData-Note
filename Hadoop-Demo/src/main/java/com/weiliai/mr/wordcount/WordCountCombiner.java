package com.weiliai.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @Author: Doug Li
 * @Date 2020/5/10
 * @Describe: Reducer的合并实现类
 */
public class WordCountCombiner extends Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //1. 汇总
        int  sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        //2. 写出
        context.write(key,new IntWritable(sum));
    }
}
