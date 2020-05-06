package com.weiliai.mr.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @Author: Doug Li
 * @Date 2020/5/6
 * @Describe: 分区类
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {

        //获取key的前三位
        String preNum = text.toString().substring(0, 3);


        int partition;

        switch (preNum) {
            case "136":
                partition = 0;
                break;
            case "137":
                partition = 1;
                break;
            case "138":
                partition = 2;
                break;
            case "139":
                partition = 3;
                break;
            default:
                partition = 4;
        }
        
        return partition;
    }
}
