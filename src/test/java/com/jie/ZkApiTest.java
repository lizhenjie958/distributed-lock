package com.jie;

import org.apache.zookeeper.*;

public class ZkApiTest {

    public static void main(String[] args) throws Exception {
        //  创建zookeeper连接
        ZooKeeper zooKeeper = new ZooKeeper("192.168.23.135:2181", 20000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了" + watchedEvent.getType() + "的事件");
            }
        });

        // 创建父节点
//        String path = zooKeeper.create("/com.jie", "niubi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println(path);

        // 创建子节点
//        String childrenPath = zooKeeper.create("/com.jie/children", "children".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println(childrenPath);

        // 获取节点的值
//        byte[] data = zooKeeper.getData("/com.jie", false, null);
//        System.out.println(new String(data));
//
//        List<String> children = zooKeeper.getChildren("/com.jie", false, null);
//        for (String child : children) {
//            System.out.println(child);
//        }


        // 设置节点的值
//        Stat stat = zooKeeper.setData("/com.jie", "updateValue".getBytes(), -1);
//        System.out.println(stat);

        // 判断节点是否存在
//        Stat exists = zooKeeper.exists("/com.jie/children", false);
//        System.out.println(exists);


        // 删除节点
        zooKeeper.delete("/jie/children",-1);
    }
}
