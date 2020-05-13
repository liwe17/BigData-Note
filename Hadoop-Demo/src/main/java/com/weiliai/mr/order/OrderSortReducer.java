package com.weiliai.mr.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/10
 * @Describe: reducer实现类
 */
public class OrderSortReducer extends Reducer<OrderBean, NullWritable,OrderBean, NullWritable> {

    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,NullWritable.get());
    }
}
