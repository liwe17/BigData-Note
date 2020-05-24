package com.weiliai.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Doug Li
 * @Date 2020/5/24
 * @Describe: 服务器向zookeeper注册代码
 */
public class DistributeServer {

    private static String CONNECT_ADDR = "hadoop100:2181,hadoop101:2181,hadoop102:2181";

    private static int SESSION_TIMEOUT = 2000;

    private ZooKeeper zkClient;

    private String parentNode = "/servers";


    public static void main(String[] args) throws Exception {

        args=new String[]{"hadoop100"};

        //1. 获取zk连接
        final DistributeServer server = new DistributeServer();
        //2. 利用zk连接注册服务器信息
        server.registerServer(args[0]);
        //3. 启动业务功能
        server.business(args[0]);
    }


    public void getConnect() throws Exception{
        zkClient = new ZooKeeper(CONNECT_ADDR,SESSION_TIMEOUT,event -> {});
    }

    public void registerServer(String hostname) throws Exception{
        String create = zkClient.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+" is online "+create);
    }

    public void business(String hostname) throws Exception{
        System.out.println(hostname+" is working ...");

        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }

}
