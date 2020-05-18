package com.weiliai.mr.weblog.complex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/18
 * @Describe: Mapper实现类
 */
public class LogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //1. 解析日志是否合法
        LogBean logBean = parseLog(value.toString());
        //2. 输出
        if(!logBean.isValid()){
            return;
        }
        context.write(value,NullWritable.get());
    }

    private LogBean parseLog(String line) {

        LogBean logBean = new LogBean();
        String[] fields = line.split(" ");
        // 数据合法性校验
        if(fields.length < 12 || Integer.parseInt(fields[8]) >= 400){
            return logBean.setValid(false);
        }
        // 填充数据
        logBean.setRemoteAddr(fields[0]).setRemoteUser(fields[1]).setTimeLocal(fields[3].substring(1))
                .setRequest(fields[6]).setStatus(fields[8]).setBodyBytesSent(fields[9]).setHttpReferer(fields[10]);
        if(fields.length > 12){
            logBean.setHttpUserAgent(fields[11]+" "+fields[12]);
        }else{
            logBean.setHttpUserAgent(fields[11]);
        }

        return logBean;


    }
}
