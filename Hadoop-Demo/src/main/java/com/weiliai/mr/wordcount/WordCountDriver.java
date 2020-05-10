package com.weiliai.mr.wordcount;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/4
 * @Describe: 单词统计驱动类
 */
public class WordCountDriver {

    public static void main(String[] args) throws Exception{

        args = new String[]{"D:\\wcinput","D:\\wcoutput"};

        //1. 获取Jop,使用默认配置
        final Job job = Job.getInstance();
        //2. 设置jar加载路径以及配置
        job.setJarByClass(WordCountDriver.class);
        job.setCombinerClass(WordCountCombiner.class);
        //3. 设置map和reduce
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //4. 设置map的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5. 设置最终输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6. 设置输入和输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交任务
//        job.submit();
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
