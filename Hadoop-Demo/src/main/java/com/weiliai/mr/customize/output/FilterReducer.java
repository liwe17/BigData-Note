package com.weiliai.mr.customize.output;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/13
 * @Describe: Reducer 实现类
 */
public class FilterReducer extends Reducer<Text, NullWritable,Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        // 输出
        context.write(new Text(key.toString()+"\r\n"),NullWritable.get());
    }
}
