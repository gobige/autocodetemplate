package com.example.autocodetemplate.ohter.practice.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class CollectionTest {

    private static final int cicleNum = 1000000;
    public static void main(String[] args) {
        List<Integer> d = new ArrayList<>();
        for (int i = 0; i <cicleNum ; i++) {
            d.add(i);
        }


        Long startTime3 = System.currentTimeMillis();
        testForEnhanceEach(d);
        System.out.println("EnhanceEach time:" + (System.currentTimeMillis() - startTime3));

        Long startTime2 = System.currentTimeMillis();
        testIterator(d);
        // 迭代器的遍历速度要比增强for循环快很多，是增强for循环的2倍左右。
        System.out.println("iterator time:" + (System.currentTimeMillis() - startTime2));


        Long startTime = System.currentTimeMillis();
        testOrdinaryFor(d);
        System.out.println("OrdinaryFor time:" + (System.currentTimeMillis() - startTime));

    }

    public static void testForEnhanceEach(List<Integer> list) {
        for (int i = 0; i <cicleNum ; i++) {
            list.get(i);
        }
    }

    public static void testOrdinaryFor(List<Integer> list) {
        for (int i = 0; i <cicleNum ; i++) {
            list.get(i);
        }
    }

    public static void testIterator(List<Integer> list) {
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            iterator.next();

        }
    }
}
