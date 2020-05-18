package com.weiliai.mr.weblog.simple;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/18
 * @Describe: Mapper实现类
 */
public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        if (!parseLog(value.toString(), context)) {
            return;
        }

        context.write(value, NullWritable.get());

    }

    //判断日志是否合法
    private boolean parseLog(String line, Context context) {

        String[] fields = line.split(" ");

        if (fields.length > 11) {
            context.getCounter("map", "true").increment(1);
            return true;
        }
        context.getCounter("map", "true").increment(1);
        return false;
    }
}
