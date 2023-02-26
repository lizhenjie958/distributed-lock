package com.jie.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component
public class RedisLock implements Lock {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private static final String REDIS_LOCK_STOCK = "redis_lock_stock";


    public void lock() {
        while (true){
            Boolean isOk = redisTemplate.opsForValue().setIfAbsent("lockName", REDIS_LOCK_STOCK);
            if(isOk){
                return;
            }else{
                System.out.println("循环等待中......");
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

    public void unlock() {
        redisTemplate.delete("lockName");
    }

    public Condition newCondition() {
        return null;
    }
}
