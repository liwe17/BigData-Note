package com.weiliai.mr.table.mapjoin;

import com.weiliai.mr.table.reducejoin.TableBean;
import com.weiliai.mr.table.reducejoin.TableDriver;
import com.weiliai.mr.table.reducejoin.TableMapper;
import com.weiliai.mr.table.reducejoin.TableReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: Driver驱动类
 */
public class DistributedCacheDriver {

    public static void main(String[] args) throws Exception{

        args = new String[] {"d:/input4", "d:/output4"};

        //1. 获取job实例
        final Job job = Job.getInstance();
        //2. 设置jobClass以及配置
        job.setJarByClass(DistributedCacheDriver.class);
        //加载缓存
        job.addCacheFile(new URI("file:///d:/pd.txt"));
        //Map端Join的逻辑不需要Reduce阶段,设置reduceTask数量为0
        job.setNumReduceTasks(0);
        //3. 关联Mapper
        job.setMapperClass(DistributedCacheMapper.class);
        //4. 设置mapper输入输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //5. 设置输入输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //6. 指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //7. 提交任务
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
