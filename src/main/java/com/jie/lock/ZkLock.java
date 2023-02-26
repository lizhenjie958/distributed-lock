package com.jie.lock;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 数据库锁
 */
@Component
public class ZkLock implements Lock {


    private static final String LOCK_PARH = "/lock";

    private static ZooKeeper zk = getZk();

    @SneakyThrows
    public void lock() {
        while (true){
            Stat exists = zk.exists(LOCK_PARH, false);
            System.out.println(JSONObject.toJSON(exists));
            if(exists == null){
                zk.create(LOCK_PARH,"locked".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                return;
            }else{
                System.out.println("等待中.......");
            }
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @SneakyThrows
    public void unlock() {
        zk.delete(LOCK_PARH,-1);
    }

    public Condition newCondition() {
        return null;
    }


    public static ZooKeeper getZk(){
        try {
            //  创建zookeeper连接
            ZooKeeper zooKeeper = new ZooKeeper("192.168.23.135:2181", 20000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("触发了" + watchedEvent.getType() + "的事件");
                }
            });
            return zooKeeper;
        } catch (IOException e) {
            return null;
        }
    }
}
