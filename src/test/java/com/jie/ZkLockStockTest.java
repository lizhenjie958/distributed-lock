package com.jie;

import com.jie.bean.Stock;
import com.jie.lock.ZkLock;
import lombok.SneakyThrows;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class ZkLockStockTest extends BaseTest{

    @Resource
    private ZkLock zkLock;

    /**
     * 单机锁
     */
    class ZkStockThread extends Thread{

        @SneakyThrows
        public void run() {
                // 上锁
                zkLock.lock();
                boolean b = new Stock().reduceStock();
                zkLock.unlock();
                if(b){
                    System.out.println(Thread.currentThread().getName() + "减少库存成功");
                }else{
                    System.err.println(Thread.currentThread().getName() + "减少库存失败");
                }
        }
    }


    @Test
    public void zkLockTest() throws InterruptedException {
        new ZkStockThread().start();
        TimeUnit.SECONDS.sleep(20);
        new ZkStockThread().start();
    }




}
