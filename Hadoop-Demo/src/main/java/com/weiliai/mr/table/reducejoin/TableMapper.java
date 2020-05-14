package com.weiliai.mr.table.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: Mapper实现类
 */
public class TableMapper extends Mapper<LongWritable,Text,Text,TableBean> {

    private String fileName;


    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //1. 获取输入文件切片
        FileSplit split = (FileSplit) context.getInputSplit();
        //2. 获取文件输入名称
        this.fileName = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 获取每一行内容
        String[] fields = value.toString().split("\t");
        //2. 按照不同的文件名操作
        if(fileName.startsWith("order")){
            //订单表处理
            context.write(new Text(fields[1]),new TableBean(fields[0],fields[1],"",Integer.parseInt(fields[2]),"order"));
        }else{
            //产品表处理
            context.write(new Text(fields[0]),new TableBean("",fields[0],fields[1],0,"pd"));
        }

    }
}
