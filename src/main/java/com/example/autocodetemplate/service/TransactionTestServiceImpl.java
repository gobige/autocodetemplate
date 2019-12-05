package com.example.autocodetemplate.service;

import com.example.autocodetemplate.dao.TransactionsTestDao;
import com.example.autocodetemplate.domain.TransactionTest;
import org.springframework.stereotype.Service;

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
@Service
public class TransactionTestServiceImpl implements TransactionTestService {

    @Resource
    private TransactionsTestDao transactionsTestDao;

    @Override
    public boolean modifyTransaction() {
        TransactionTest transactionTest = transactionsTestDao.queryTransactionTestById(1);
        if (transactionTest == null) {
            return false;
        }

        return true;
    }

}
