package com.weiliai.mr.customize.input;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: Mapper 实现类
 */
public class SequenceFileMapper extends Mapper<Text, BytesWritable, Text,BytesWritable> {

    @Override
    protected void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
        context.write(key,value);
    }
}
