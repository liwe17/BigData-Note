package com.weiliai.mr.order;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/10
 * @Describe: Driver类
 */
public class OrderSortDriver {

    public static void main(String[] args) throws Exception{

        args = new String[]{"D:\\fcinput1","D:\\fcoutput2"};
        //1. 获取job实例
        Job job = Job.getInstance();
        //2. 设置jar加载路径,以及配置
        job.setJarByClass(OrderSortDriver.class);
        job.setGroupingComparatorClass(OrderSortGroupingComparator.class);
        //3. 关联mapper和reducer
        job.setMapperClass(OrderSortMapper.class);
        job.setReducerClass(OrderSortReducer.class);
        //4. 设置Mapper输入输出类
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        //5. 设置最终输入输出类
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        //6. 设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
