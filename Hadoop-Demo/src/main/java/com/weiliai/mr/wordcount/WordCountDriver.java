package com.weiliai.mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.compress.GzipCodec;
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

        args = new String[]{"D:/wcinput","D:/wcoutput"};

        //1. 获取Jop,使用默认配置
        Configuration configuration = new Configuration();
//        configuration.setBoolean("mapreduce.map.output.compress",true);
//        configuration.setClass("mapreduce.map.output.compress.codec",BZip2Codec.class,CompressionCodec.class);
        final Job job = Job.getInstance(configuration);
        //2. 设置jar加载路径以及配置
        job.setJarByClass(WordCountDriver.class);
        //job.setCombinerClass(WordCountCombiner.class);
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

        //设置reduce端输出压缩开启
        FileOutputFormat.setCompressOutput(job,true);
        //设置压缩方式
        FileOutputFormat.setOutputCompressorClass(job,BZip2Codec.class);
//        FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
//        FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

        //7. 提交任务
//        job.submit();
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
