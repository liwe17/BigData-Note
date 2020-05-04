package com.weiliai.mr.flowsum;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/4
 * @Describe: 手机流量统计驱动类
 */
public class FlowCountDriver {

    public static void main(String[] args) throws Exception{

        //1. 获取Job实例,并设置启动jar
        final Job job = Job.getInstance();
        job.setJarByClass(FlowCountDriver.class);
        //2. 关联mapper和reduce
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        //3. 设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //4. 设置最终输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //5. 设置输入输出
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //6. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
