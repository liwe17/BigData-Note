package com.weiliai.mr.nline;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: NLine 驱动类
 */
public class NLineDriver {


    public static void main(String[] args) throws Exception{
        //1. 获取job实例
        Job job = Job.getInstance();
        //2. 设置jar class
        job.setJarByClass(NLineDriver.class);
        //使用每个切片处理记录行数
        job.setInputFormatClass(NLineInputFormat.class);
        NLineInputFormat.setNumLinesPerSplit(job,3);
        //3. 关联Mapper和Reducer
        job.setMapperClass(NLineMapper.class);
        job.setReducerClass(NLineReducer.class);
        //4. 设置mapper输出k.v
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5. 设置最终输入输出kv
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6. 设置输入输出数据路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交job
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
