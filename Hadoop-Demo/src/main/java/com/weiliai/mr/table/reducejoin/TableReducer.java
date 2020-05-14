package com.weiliai.mr.table.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: Reducer实现类
 */
public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {


    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
        //1. 遍历values,得到每个tableBean
        // 格式如下:
        // 01 1001 1 order
        // 01 1001 1 order
        // 01 小米    pd
        // 02 ...
        List<TableBean> tableBeans = new ArrayList<>();
        //2. 记录每个order对象
        TableBean tableBean;
        //3. 记录每个pd对象
        TableBean pdBean = new TableBean();
        for (TableBean value : values) {
            if(value.getFlag().equals("order")){
                tableBean = new TableBean();
                try {
                    BeanUtils.copyProperties(tableBean,value);
                } catch (IllegalAccessException |InvocationTargetException e) {
                    e.printStackTrace();
                }
                tableBeans.add(tableBean);
            }else{
                //产品表
                try {
                    BeanUtils.copyProperties(pdBean,value);
                } catch (IllegalAccessException |InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //4. 设置产品名称并输出
        tableBeans.forEach(e-> {
            e.setPname(pdBean.getPname());
            try {
                context.write(e,NullWritable.get());
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
