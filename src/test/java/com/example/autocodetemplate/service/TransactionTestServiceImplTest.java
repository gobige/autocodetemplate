package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.UserBodyInfo;
import com.example.autocodetemplate.po.MiPushPO;
import com.example.autocodetemplate.thirdparty.MiPushService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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
@Sql("classpath:data.sql")
public class TransactionTestServiceImplTest {
    @Resource
    private TransactionTestService transactionTestService;


    @Test // 测试pageHer分页
    public void testpageQuery() {
        transactionTestService.pageQuery(1,3);
    }

    @Test // 测试三种不同sql定义
    public void testQueryIdByMethod() {
        transactionTestService.queryById(1);
    }

    @Test // 测试事务传播性 TODO 未符合预期
    public void testGetssqResult() {
        try {
            transactionTestService.modifyTransaction();

        } catch (Exception e) {
            log.debug("Transaction  Test  result");
            Collection<UserBodyInfo> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }

    }

    @Test // 测试spring声明式事务 基于aop原理 带来的事务失效
    public void testAOPEffectTransaction() {
        try {
            transactionTestService.AOPEffectTransactionEffect();

        } catch (Exception e) {
            log.debug(" Transaction Effect  result");
            Collection<UserBodyInfo> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }

        try {
            transactionTestService.AOPEffectTransactionAddAopServiceEffect();

        } catch (Exception e) {
            log.debug(" Transaction add service Effect  result");
            Collection<UserBodyInfo> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }

        try {
            transactionTestService.AOPEffectTransactionNoEffect();

        } catch (Exception e) {
            log.debug(" Transaction NoEffect  result");
            Collection<UserBodyInfo> transactionTests = transactionTestService.queryAll();
            transactionTests.stream().forEach(obj -> log.info(obj.toString()));
        }


    }
}
