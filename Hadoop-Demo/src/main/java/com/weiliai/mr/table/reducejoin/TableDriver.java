package com.weiliai.mr.table.reducejoin;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: Driver驱动类
 */
public class TableDriver {

    public static void main(String[] args) throws Exception{

        args = new String[] {"d:/input3", "d:/output3"};

        //1. 获取job实例
        final Job job = Job.getInstance();
        //2. 设置jobClass以及配置
        job.setJarByClass(TableDriver.class);
        //3. 关联Mapper和Reducer
        job.setMapperClass(TableMapper.class);
        job.setReducerClass(TableReducer.class);
        //4. 设置mapper输入输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TableBean.class);
        //5. 设置输入输出
        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);
        //6. 指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
