package com.jie.lock;

import com.jie.mapper.mapper.LockRecordMapper;
import com.jie.mapper.model.LockRecord;
import com.jie.mapper.model.LockRecordQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 数据库锁
 */
@Component
public class DbLock implements Lock {
    @Resource
    private LockRecordMapper lockRecordMapper;
    private static final String DB_LOCK_STOCK = "db_lock_stock";
    public void lock() {
        while (true){
            boolean b = this.tryLock();
            if(b){
                LockRecord lockRecord = new LockRecord();
                lockRecord.setLockName(DB_LOCK_STOCK);
                lockRecordMapper.insert(lockRecord);
                return;
            }else{
                System.out.println("等待中.......");
            }
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        LockRecordQuery lockRecordQuery = new LockRecordQuery();
        lockRecordQuery.createCriteria().andLockNameEqualTo(DB_LOCK_STOCK);
        return lockRecordMapper.countByExample(lockRecordQuery) == 0;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        LockRecordQuery lockRecordQuery = new LockRecordQuery();
        lockRecordQuery.createCriteria().andLockNameEqualTo(DB_LOCK_STOCK);
        lockRecordMapper.deleteByExample(lockRecordQuery);
    }

    public Condition newCondition() {
        return null;
    }
}
