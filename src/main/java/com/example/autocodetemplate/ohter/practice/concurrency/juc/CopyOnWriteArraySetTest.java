package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArrayList
 */
@Slf4j
public class CopyOnWriteArraySetTest {

    private static CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    public static void main(String[] args) {

        Integer[] elements = new Integer[4];
        for (int i = 0; i < 4; i++) {
            elements[i] = i;
            log.info("不变数组hashcode{}", elements.hashCode());
        }
        boolean success;
        for (int i = 0; i < 10; i++) {
            success = copyOnWriteArraySet.add(i);
            if (success) {
                log.info("新增元素{}到【复制写入】数组,当前数组hashcode{}", i, copyOnWriteArraySet.toArray().hashCode());
            }
        }
    }
}
