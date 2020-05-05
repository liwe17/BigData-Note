package com.weiliai.mr.kv;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: KV 驱动类
 */
public class KVTextDriver {


    public static void main(String[] args) throws Exception{
        //1. 获取job实例
        Job job = Job.getInstance();
        //2. 设置jar Class,以及附加属性
        job.setJarByClass(KVTextDriver.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.getConfiguration().set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR,"");
        //3. 关联mapper和reducer
        job.setMapperClass(KVTextMapper.class);
        job.setReducerClass(KVTextReducer.class);
        //4. 设置mapper输出k,v
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5. 设置最终输出k,v
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6 .设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
