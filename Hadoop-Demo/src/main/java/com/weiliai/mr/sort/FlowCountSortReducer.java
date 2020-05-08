package com.weiliai.mr.sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/8
 * @Describe: Reducer实现类
 */
public class FlowCountSortReducer extends Reducer<FlowBean, Text,Text, FlowBean> {

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        values.forEach(e-> {
            try {
                context.write(e,key);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }
}
