package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.TransactionTest;
import com.example.autocodetemplate.po.MiPushPO;
import com.example.autocodetemplate.thirdparty.MiPushService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collection;

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
@Slf4j
public class TransactionTestServiceImplTest {
    @Resource
    private TransactionTestService transactionTestService;


    @Test
    public void testGetssqResult() {
        try {
            transactionTestService.modifyTransaction();

        } catch (Exception e) {
            log.debug("Transaction  Test  result");
            Collection<TransactionTest> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }

    }

    @Test
    public void testAOPEffectTransaction() {
        try {
            transactionTestService.AOPEffectTransactionEffect();

        } catch (Exception e) {
            log.debug(" Transaction Effect  result");
            Collection<TransactionTest> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }

        try {
            transactionTestService.AOPEffectTransactionAddAopServiceEffect();

        } catch (Exception e) {
            log.debug(" Transaction add service Effect  result");
            Collection<TransactionTest> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }

        try {
            transactionTestService.AOPEffectTransactionNoEffect();

        } catch (Exception e) {
            log.debug(" Transaction NoEffect  result");
            Collection<TransactionTest> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }


    }
}
