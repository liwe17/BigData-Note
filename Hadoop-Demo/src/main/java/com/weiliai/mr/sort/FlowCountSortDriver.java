package com.weiliai.mr.sort;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author: Doug Li
 * @Date 2020/5/8
 * @Describe: Driver实现类
 */
public class FlowCountSortDriver {

    public static void main(String[] args) throws Exception{

        args = new String[]{"D:\\fcinput2","D:\\fcoutput2"};

        //1. 获取job实例
        final Job job = Job.getInstance();
        //2. 设置jobClass
        job.setJarByClass(FlowCountSortDriver.class);
        // 加载自定义分区类
//        job.setPartitionerClass(ProvincePartitioner.class);
        // 设置ReduceTask个数
//        job.setNumReduceTasks(5);
        //3. 关联mapper和reducer
        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountSortReducer.class);
        //4. 设置mapper输入输出
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        //5. 设置最终输入输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //6. 设置job输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //7. 提交任务
        System.out.println(job.waitForCompletion(true)?0:1);
    }

}
