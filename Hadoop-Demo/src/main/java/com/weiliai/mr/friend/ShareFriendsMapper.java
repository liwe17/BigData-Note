package com.weiliai.mr.friend;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.*;

/**
 * @Author: Doug Li
 * @Date 2020/5/23
 * @Describe: Mapper实现类
 */
public class ShareFriendsMapper extends Mapper<LongWritable, Text,Text,Text> {

    private Map<String,List<String>> friends = new HashMap<>();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //1. 切割A:B,C,D,F,E,O-->A B,C,D,F,E,O
        String line = value.toString();
        if(StringUtils.isEmpty(line)){
            return;
        }
        String[] fields = line.split(":");
        //2. 存入集合map中
        friends.put(fields[0], Arrays.asList(fields[1].split(",")));
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //1. 外层遍历Map friends得到k1,v1
        //2. 内层遍历Map friends找出k2,v2
        //3. 由于A-B和B-A属于同一种,当互为AB互为好友会出现,因此需要进行去重处理
        //3.1 定义一个set,每次存放k1-k2的时候,先判断k2-k1是否存在,不存在则直接放入k1-k2
        //4. 然后v1和v2取交集
        Set<String> set = new HashSet<>(); //存A-B/A-C等
        friends.forEach((k1,v1)-> {
            friends.forEach((k2, v2) -> {
                if (!Objects.equals(k1, k2) && !set.contains(String.join("-", k2, k1))) {
                    set.add(String.join("-", k1, k2));
                    try {
                        @SuppressWarnings("rawtypes")
                        Collection intersection = CollectionUtils.intersection(friends.get(k1), friends.get(k2));
                        if (CollectionUtils.isNotEmpty(intersection)) {
                            context.write(new Text(String.join("-", k1, k2)), new Text(String.join(",", intersection)));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
