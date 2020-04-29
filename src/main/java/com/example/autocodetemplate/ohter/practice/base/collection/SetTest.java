package com.example.autocodetemplate.ohter.practice.base.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class SetTest {

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        Set attrGoodsIdSet = new HashSet(list1);

        Set<Integer> intSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            intSet.add(i);
            intSet.remove(i - 1);
        }
        log.info("只剩一个"+ intSet.toString());

        Set<Short> shortSet = new HashSet<>();
        for (Short i = 0; i < 10; i++) {
            shortSet.add(i);
            shortSet.remove(i - 1);
        }

        log.info("因为转型，所以一个都没删除" + shortSet.toString());


        Object i = 1 == 1 ? new Integer(3) : new Float(1);
        System.out.println("关系运算符优先级 > 赋值运算符"+i);

        List<Long> list = new ArrayList<>();
        for (Long j = 0L; j < 10; j++) {
            list.add(j);
        }

        System.out.println(list.toArray(new Long[0]));

    }

}
