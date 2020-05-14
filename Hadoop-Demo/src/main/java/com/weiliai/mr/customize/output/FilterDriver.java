package com.weiliai.mr.customize.output;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: Driver实现类
 */
public class FilterDriver {

    public static void main(String[] args) throws Exception{

        args = new String[] {"d:/outformat", "d:/output2"};
        //1. 获取job实例
        final Job job = Job.getInstance();
        //2. 设置job启动类以及配置
        job.setJarByClass(FilterDriver.class);
        job.setOutputFormatClass(FilterOutputFormat.class);
        //3. 关联mapper和reducer
        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(FilterReducer.class);
        //4. 设置mapper输入输出类
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //5. 设置输出输入
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //6. 设置路径
        //虽然我们自定义了outputFormat,但是因为我们的outputFormat继承自fileOutputFormat,
        // 而fileOutputFormat要输出一个_SUCCESS文件，所以，在这还得指定一个输出目录
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交job
        System.exit(job.waitForCompletion(true)?0:1);

    }
}
