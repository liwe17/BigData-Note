package com.weiliai.mr.top;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @Author: Doug Li
 * @Date 2020/5/21
 * @Describe: Mapper实现类
 */
public class TopNMapper extends Mapper<LongWritable,Text,FlowBean, NullWritable> {

    private TreeMap<FlowBean,NullWritable> treeMap = new TreeMap<>();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 切割
        String[] fields = value.toString().split("\t");

        //2. 放入TreeMap
        treeMap.put(new FlowBean(fields[0],Long.parseLong(fields[1]),Long.parseLong(fields[2]),Long.parseLong(fields[3])),NullWritable.get());

        //3. 数量大于10,删除
        if(treeMap.size()>10){
            //treeMap.remove(treeMap.firstKey());
            treeMap.remove(treeMap.lastKey());
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        treeMap.forEach((k,v)-> {
            try {
                context.write(k,v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
