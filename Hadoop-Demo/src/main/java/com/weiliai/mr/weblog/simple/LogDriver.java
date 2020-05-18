package com.weiliai.mr.weblog.simple;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.xml.soap.Text;

/**
 * @Author: Doug Li
 * @Date 2020/5/18
 * @Describe: Driver实现类
 */
public class LogDriver {

    public static void main(String[] args) throws Exception{

        args = new String[] {"d:/input5", "d:/output5"};

        //1. 获取job实例
        Job job = Job.getInstance();
        //2. 设置jarClass和配置
        job.setJarByClass(LogDriver.class);
        job.setNumReduceTasks(0);
        //3. 关联Mapper
        job.setMapperClass(LogMapper.class);
        //4. 设置Mapper/reducer的输入输出
        //5. 设置最终的输入输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //6. 指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
