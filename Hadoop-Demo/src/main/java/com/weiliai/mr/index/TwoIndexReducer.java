package com.weiliai.mr.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/21
 * @Describe: Reducer实现类
 */
public class TwoIndexReducer extends Reducer<Text,Text,Text,Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // atguigu a.txt 3
        // atguigu b.txt 2
        // atguigu c.txt 2

        // atguigu c.txt-->2 b.txt-->2 a.txt-->3

        //1. 拼接参数
        StringBuilder sb = new StringBuilder();

        values.forEach(e->sb.append(e.toString().replace("\t","-->")).append("\t"));

        //2. 输出
        context.write(key,new Text(sb.toString()));
    }
}
