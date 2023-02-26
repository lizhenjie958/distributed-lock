package com.jie;

import com.jie.mapper.mapper.LockRecordMapper;
import com.jie.mapper.model.LockRecord;
import com.jie.mapper.model.LockRecordQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {

    @Resource
    private LockRecordMapper lockRecordMapper;

    @Test
    public void test1(){
        List<LockRecord> lockRecords = lockRecordMapper.selectByExample(new LockRecordQuery());
        System.out.println(lockRecords);
    }
}
