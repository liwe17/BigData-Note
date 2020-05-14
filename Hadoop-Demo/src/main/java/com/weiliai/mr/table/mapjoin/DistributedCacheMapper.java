package com.weiliai.mr.table.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: Mapper实现类
 */
public class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    private Map<String, String> pdMap = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //1. 获取缓存路径
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath();
        //2. 读取文件
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while (StringUtils.isNotEmpty(line = reader.readLine())) {
                String[] fields = line.split("\t");
                //3. 缓存数据到集合
                pdMap.put(fields[0], fields[1]);
            }
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 获取每行值,并进行切割
        String line = value.toString();
        String[] fields = line.split("\t");
        //2. 写出
        context.write(new Text(line+"\t"+pdMap.get(fields[1])),NullWritable.get());
    }
}
