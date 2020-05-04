package com.weiliai.mr.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/4
 * @Describe: 流量统计的reducer类
 */
public class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean> {

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long upFlows = 0L;
        long downFlows = 0L;
        for (FlowBean value : values) {
            upFlows+=value.getUpFlow();
            downFlows+=value.getDownFlow();
        }
        context.write(key,new FlowBean(upFlows,downFlows,(upFlows+downFlows)));
    }
}
