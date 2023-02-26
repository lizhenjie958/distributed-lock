package com.jie;

import com.jie.bean.Stock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StockMain{

    // 加单机锁
    public static final Lock lock = new ReentrantLock();

    /**
     * 单机锁
     */
    static class StockThread implements Runnable{

        public void run() {
            // 上锁
            lock.lock();
            boolean b = new Stock().reduceStock();
            // 解锁
            lock.unlock();
            if(b){
                System.out.println(Thread.currentThread().getName() + "减少库存成功");
            }else{
                System.err.println(Thread.currentThread().getName() + "减少库存失败");
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new StockThread(),"线程1").start();
        new Thread(new StockThread(),"线程2").start();
    }
}
