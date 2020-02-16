package com.example.autocodetemplate.ohter.practice.base;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ListTest {

    public static void main(String[] args) {
        insertFasterOfArrayList();
        insertFasterOfLinkedList();
    }

    /**
     * 对比插入谁更快  比linedk快，大于1000w数据时两者差不多
     */
    static void insertFasterOfArrayList() {
        // 扩容比不扩容慢？？
        List arraylist = new ArrayList();
        long startime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            arraylist.add(i);
        }

        long endTime = System.currentTimeMillis();
        log.info("ArrayList insert data cost:" + (endTime - startime));

    }

    /**
     * 对比插入谁更快
     */
    static void insertFasterOfLinkedList() {
        List linedList = new LinkedList();
        long startime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            linedList.add(i);
        }
        long endTime = System.currentTimeMillis();
        log.info("LinkedList insert data cost:" + (endTime - startime));
    }
}
