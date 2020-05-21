package com.weiliai.mr.index;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/21
 * @Describe: Driver实现类
 */
public class TwoIndexDriver {

    public static void main(String[] args) throws Exception{

        args = new String[]{"d:/input2","d:/output2"};

        //1. 获取job实例
        final Job job = Job.getInstance();

        //2. 设置Jar类和配置
        job.setJarByClass(TwoIndexDriver.class);

        //3. 关联Mapper和Reducer
        job.setMapperClass(TwoIndexMapper.class);
        job.setReducerClass(TwoIndexReducer.class);

        //4. 设置Mapper输入输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //5. 设置最终输入输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //6. 设置文件输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);

    }

}
