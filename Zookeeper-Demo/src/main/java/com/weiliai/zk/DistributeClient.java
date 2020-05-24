package com.weiliai.zk;

import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: Doug Li
 * @Date 2020/5/24
 * @Describe: 客户端代码
 */
public class DistributeClient {

    private static String CONNECT_ADDR = "hadoop100:2181,hadoop101:2181,hadoop102:2181";

    private static int SESSION_TIMEOUT = 2000;

    private ZooKeeper zkClient;

    private String parentNode = "/servers";

    public static void main(String[] args) throws Exception {

        //1. 获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnect();

        //2. 获取servers的子节点信息,从中获取服务器信息列表
        client.getServerList();

        //3. 业务进程启动
        client.business();
    }


    // 创建连接
    public void getConnect() throws Exception{
        zkClient = new ZooKeeper(CONNECT_ADDR,SESSION_TIMEOUT,event -> {
            //启动监听
            try {
                getServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 获取服务器列表
    public void getServerList() throws Exception{

        //1. 获取服务器子节点信息,并且对父节点进行监听
        List<String> children = zkClient.getChildren(parentNode, true);

        //2. 存储服务器信息列表
        children.stream().map(e-> {
            try {
                return new String((zkClient.getData(parentNode + "/" + e, false, null)));
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()).forEach(System.out::println);

    }

    // 业务功能
    public void business() throws Exception{
        System.out.println("client is working ...");
        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }

}
