package com.jie;

import com.jie.bean.Stock;
import com.jie.lock.DbLock;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class DbLockStockTest extends BaseTest {



    @Resource
    private DbLock dbLock;

    /**
     * 单机锁
     */
    class DbStockThread extends Thread{

        public void run() {
            // 上锁
            dbLock.lock();
            boolean b = new Stock().reduceStock();
            // 解锁
            dbLock.unlock();
            if(b){
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            }else{
                System.err.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }


    @Test
    public void dbLockTest() throws InterruptedException {
        new DbStockThread().start();
        TimeUnit.SECONDS.sleep(20);
        new DbStockThread().start();
    }
}
