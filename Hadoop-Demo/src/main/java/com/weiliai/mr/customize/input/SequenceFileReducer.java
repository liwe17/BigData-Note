package com.weiliai.mr.customize.input;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: Reducer实现类
 */
public class SequenceFileReducer extends Reducer<Text, BytesWritable, Text,BytesWritable> {


    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,values.iterator().next());
    }
}
