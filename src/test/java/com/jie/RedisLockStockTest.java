package com.jie;

import com.jie.bean.Stock;
import com.jie.lock.RedisLock;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class RedisLockStockTest extends BaseTest {



    @Resource
    private RedisLock redisLock;

    /**
     * 单机锁
     */
    class RedisStockThread extends Thread{

        public void run() {
            // 上锁
            redisLock.lock();
            boolean b = new Stock().reduceStock();
            // 解锁
            redisLock.unlock();
            if(b){
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            }else{
                System.err.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }


    @Test
    public void redisLockTest() throws InterruptedException {
        new RedisStockThread().start();
        TimeUnit.SECONDS.sleep(10);
        new RedisStockThread().start();
    }
}
