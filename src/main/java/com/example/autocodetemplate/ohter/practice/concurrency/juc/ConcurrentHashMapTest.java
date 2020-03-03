package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ConcurrentHashMapTest {

    public static final int putForCount = 100;

    private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(4, 0.75f, 5);

    public static void main(String[] args) {
        log.info("concurrentHashMap当前大小{}", concurrentHashMap.size());

    }

    public static void putFor() {
        for (int i = 0; i < putForCount; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int key = (int) (Math.random() * 10);
                    int value = (int) (Math.random() * 10);
                    concurrentHashMap.put(key, value);
                    log.info("concurrentHashMap put key【{}】，value【{}】", key, value);
                }
            });
        }
    }

    public static void getFor2() {
        for (int i = 0; i < putForCount; i++) {
            concurrentHashMap.put(i, i);
        }
    }


}
