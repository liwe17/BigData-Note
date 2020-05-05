package com.weiliai.mr.customize;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/5
 * @Describe: 自定义驱动类
 */
public class SequenceFileDriver {

    public static void main(String[] args) throws Exception{

        //1. 获取job实例
        Job job = Job.getInstance();
        //2. 设置jar Class,以及附加属性
        job.setJarByClass(SequenceFileDriver.class);
        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        //3. 关联mapper和reducer
        job.setMapperClass(SequenceFileMapper.class);
        job.setReducerClass(SequenceFileReducer.class);
        //4. 设置mapper输出k,v
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        //5. 设置最终输出k,v
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //6 .设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
