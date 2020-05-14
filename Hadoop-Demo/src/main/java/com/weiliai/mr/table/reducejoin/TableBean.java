package com.weiliai.mr.table.reducejoin;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author: Doug Li
 * @Date 2020/5/14
 * @Describe: 商品和订合并后的Bean类
 */
public class TableBean implements Writable {


    private String orderId;

    private String pid;

    private String pname;

    private int amount;

    private String flag;

    public TableBean() {
    }

    public TableBean(String orderId, String pid, String pname, int amount, String flag) {
        this.orderId = orderId;
        this.pid = pid;
        this.pname = pname;
        this.amount = amount;
        this.flag = flag;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(pid);
        out.writeUTF(pname);
        out.writeUTF(flag);
        out.writeInt(amount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.pid = in.readUTF();
        this.pname = in.readUTF();
        this.flag = in.readUTF();
        this.amount = in.readInt();
    }

    @Override
    public String toString() {
        return orderId + "\t" + pname + "\t" + amount + "\t";
    }
}
