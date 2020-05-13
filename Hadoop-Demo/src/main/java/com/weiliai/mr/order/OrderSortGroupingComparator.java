package com.weiliai.mr.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @Author: Doug Li
 * @Date 2020/5/10
 * @Describe: 分组排序
 */
public class OrderSortGroupingComparator extends WritableComparator {

    public OrderSortGroupingComparator() {
        super(OrderBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return Double.compare(((OrderBean) a).getOrderId(),((OrderBean) b).getOrderId());
    }

}
