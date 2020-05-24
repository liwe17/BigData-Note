package com.weiliai.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Doug Li
 * @Date 2020/5/24
 * @Describe: zookeeper API学习demo
 */
public class TestZookeeper {

    private static String CONNECT_ADDR = "hadoop100:2181,hadoop101:2181,hadoop102:2181";

    private static int SESSION_TIMEOUT = 2000;

    private ZooKeeper zkClient;

    //1. 创建Zookeeper客户端
    @Before
    public void init() throws Exception{
        zkClient = new ZooKeeper(CONNECT_ADDR, SESSION_TIMEOUT, event -> {
            //收到事件通知后的回调函数(用户的业务逻辑)
            System.out.println(event.getType()+"--"+event.getPath());
            //再次启动监听
            try {
                zkClient.getChildren("/", true).forEach(System.err::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    //2. 创建节点
    @Test
    public void createNode() throws Exception{
        final String path = zkClient.create("/atguigu2", "dagezuishuai".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }


    //3. 获取子节点,并监控节点变化
    @Test
    public void getChildren() throws Exception{
        List<String> children = zkClient.getChildren("/", true);
        children.forEach(System.out::println);
        //延时阻塞
        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }

    //4. 判断节点Znode是否岑在
    @Test
    public void exist() throws Exception{
        Stat stat = zkClient.exists("/eclipse", false);
        System.out.println(null == stat? "not exist" : "exist");
    }


}
