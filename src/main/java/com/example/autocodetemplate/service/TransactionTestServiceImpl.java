package com.example.autocodetemplate.service;

import com.example.autocodetemplate.dao.TransactionsTestDao;
import com.example.autocodetemplate.domain.TransactionTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@Slf4j
public class TransactionTestServiceImpl implements TransactionTestService {

    @Resource
    private TransactionsTestDao transactionsTestDao;
    @Resource
    private TransactionTestService transactionTestService;

    @Override
    public Collection<TransactionTest> queryAll() {
       return transactionsTestDao.queryAll();
    }

    /**
     *
     * @throws RuntimeException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyTransaction() throws RuntimeException{
        log.debug("before modifyTransaction result");
        Collection<TransactionTest> transactionTests = transactionsTestDao.queryAll();
        transactionTests.stream().forEach(obj -> log.info(obj.toString()));

        int upNum = transactionsTestDao.updateTransactionTest("upnum1", 22, 1);
        log.debug("update num {}", upNum);
        log.debug("after modifyTransaction result");
        Collection<TransactionTest> transactionTest2 = transactionsTestDao.queryAll();
        transactionTest2.stream().forEach(obj -> log.info(obj.toString()));

        try {
            transactionTestService.modifyTransaction2();
        } catch (Exception e) {
            log.debug("exception modifyTransaction2 result");
            Collection<TransactionTest> transactionTest3 = transactionsTestDao.queryAll();
            transactionTest3.stream().forEach(obj -> log.info(obj.toString()));
        }

        log.debug("ggggggggggggggggggggggggggggggggg");

        // 该异常发生时，没有使用事务情况下 t1和t2更新的结果都没有rollback
//        throw new RuntimeException("test modifyTransaction");
    }

    @Transactional
    @Override
    public void modifyTransaction2() {
        int upNum = transactionsTestDao.updateTransactionTest("upnum3", 11, 3);
        log.debug("update num {}", upNum);
        log.debug("after modifyTransaction2 result");
        Collection<TransactionTest> transactionTest2 = transactionsTestDao.queryAll();
        transactionTest2.stream().forEach(obj -> log.info(obj.toString()));

        // 只发生该异常发生时，没有使用事务情况下 t1 和t2 更新的结果都没有rollback
        // 只发生该异常发生时，使用默认事务情况下 t2 更新的结果都没有rollback
        throw new RuntimeException("test modifyTransaction2");
    }

    @Override
    @Transactional
    public void AOPEffectTransactionEffect() {
        int upNum = transactionsTestDao.updateTransactionTest("upnum3", 11, 3);

        throw new RuntimeException("test AOPEffectTransactionEffect");
    }

    @Override
    public void AOPEffectTransactionNoEffect() {
        // spring 事务利用AOP原理进行round的方法增强，增强方法调用内部方法时，必须通过引用XXservice.method方法调用才会是事务生效，否则不生效
        // 无 rollback
        AOPEffectTransactionEffect();
    }

    @Override
    public void AOPEffectTransactionAddAopServiceEffect() {
        // spring 事务利用AOP原理进行round的方法增强，增强方法调用内部方法时，必须通过引用XXservice.method方法调用才会是事务生效，否则不生效
        // 有 rollback
        transactionTestService.AOPEffectTransactionEffect();
    }

}
