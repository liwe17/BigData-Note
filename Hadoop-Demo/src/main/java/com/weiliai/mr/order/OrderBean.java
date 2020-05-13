package com.weiliai.mr.order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/10
 * @Describe: 订单自定义实体
 */
public class OrderBean implements WritableComparable<OrderBean> {

    private int orderId; //订单id号,注意:原始订单中000001将变为1

    private double price; //价格

    public OrderBean() {
    }

    public OrderBean(int orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public int compareTo(OrderBean bean) {
        //先按照id升序,id相同按照价格倒序
        if(this.orderId>bean.getOrderId()){
            return 1;
        }else if(this.orderId == bean.getOrderId()){
            //价格倒序
            return Double.compare(bean.getPrice(),this.getPrice());
        }else {
            return -1;
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(orderId);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readInt();
        this.price = in.readDouble();
    }

    @Override
    public String toString() {
        return orderId + "\t" + price;
    }
}
