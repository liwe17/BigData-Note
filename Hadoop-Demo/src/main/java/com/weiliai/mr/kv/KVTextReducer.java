package com.weiliai.mr.kv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: KV Reducer实现类
 */
public class KVTextReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        //1. 汇总统计
        int sum = 0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        //2. 输出
        context.write(key,new IntWritable(sum));
    }
}
