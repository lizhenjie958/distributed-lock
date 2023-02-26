package com.jie.bean;

import java.util.concurrent.TimeUnit;

public class Stock {
    // 库存数量
    public static int num = 1;

    public boolean reduceStock(){
        if(num > 0){
            // 模拟消耗时长，并发调试，大量出现超卖现象
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num--;
            return true;
        }else{
            return false;
        }
    }

}
