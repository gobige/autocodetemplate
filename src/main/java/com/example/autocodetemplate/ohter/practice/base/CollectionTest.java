package com.example.autocodetemplate.ohter.practice.base;

import org.springframework.util.StopWatch;

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

        // 预热遍历
        d.forEach(integer -> {});

        StopWatch stopWatch = new StopWatch();
        // 一般
        stopWatch.start("迭代器循环");
        testIterator(d);
        stopWatch.stop();

        // 慢
        stopWatch.start("增强foreach循环");
        testForEnhanceEach(d);
        stopWatch.stop();


        // 快
        stopWatch.start("普通fori循环");
        testOrdinaryFor(d);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

    }

    public static void testForEnhanceEach(List<Integer> list) {
        for (Integer i : list) {

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
