package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.TransactionTest;

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
public interface TransactionTestService {

    Collection<TransactionTest> queryAll();

    void modifyTransaction();

    void modifyTransaction2();

    void AOPEffectTransactionEffect();

    void AOPEffectTransactionNoEffect();

    void AOPEffectTransactionAddAopServiceEffect();
}
