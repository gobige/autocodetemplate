package com.example.autocodetemplate.service;

import com.example.autocodetemplate.po.MiPushPO;
import com.example.autocodetemplate.thirdparty.MiPushService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTestServiceImplTest {
    @Resource
    private TransactionTestService transactionTestService;


    @Test
    public void testGetssqResult() {
        Assert.assertTrue("modify transaction failure!", transactionTestService.modifyTransaction()); ;
    }
}
