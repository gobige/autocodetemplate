package com.example.autocodetemplate.ohter.practice.base;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * List集合效率对比
 */
public class ListTest {
    private static int count = 1000000;

    public static void main(String[] args) {
        InsertOfListTest();
    }

    // ArrayList < LinkedList
    private static void queryOfListTest() {
        StopWatch stopWatch = new StopWatch();
        List arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            arrayList.add(i);
        }
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < count; i++) {
            linkedList.addLast(i);
        }

        stopWatch.start("ArrayList fori");
        int num = arrayList.size();
        for (int i = 0; i < num; i++) {
            arrayList.get(i);
        }
        stopWatch.stop();

        stopWatch.start("LinkedList iterator");
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }


    // 分为两个维度考量,集合大小，会进行什么操作，增，删，遍历？
    // 100W明显arraylist比linkedlist快 46:54 addHead < arrylist < addLast
    // 10W明显linkedlist比arraylist快 38:62
    // 60W明显arraylist比linkedlist快 23:77
    // 综上所述以50w为分割线，新增小于50w使用LinkedList合适，大于50使用Arraylist合适
    // 解析：ArrayList每次新增都是在数组后面新增元素，虽然每次扩容会重新复制一个数组，但是由于物理内存地址是连续的，所以IO的寻址上面是由于LinkedList的
    // LinkedList新增默认是在末尾条件元素，如果是指定位置插入还想进行遍历寻址（开销更大）
    private static void InsertOfListTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ArrayList insert data");
        List arraylist = new ArrayList();
        for (int i = 0; i < count; i++) {
            arraylist.add(i);
        }
        stopWatch.stop();

        stopWatch.start("LinkedList  addLast  data");
        LinkedList linedList = new LinkedList();
        for (int i = 0; i < count; i++) {
            linedList.addLast(i);
        }
        stopWatch.stop();


        stopWatch.start("LinkedList addHead data");
        LinkedList headLinedList = new LinkedList();
        for (int i = 0; i < count; i++) {
            headLinedList.addFirst(i);
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }


    // fori < iterator ~< foreach
    private static void ArrayListForiAndForeachAndIterator() {
        StopWatch stopWatch = new StopWatch();
        List arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            arrayList.add(i);
        }

        stopWatch.start("ArrayList fori");
        int num = arrayList.size();
        for (int i = 0; i < num; i++) {
            arrayList.get(i);
        }
        stopWatch.stop();

        stopWatch.start("ArrayList foreach");
        for (Object o : arrayList) {

        }
        stopWatch.stop();

        stopWatch.start("ArrayList iterator");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    // foreach ~= iterator < fori
    private static void LinkedListForiAndForeachAndIterator() {
        StopWatch stopWatch = new StopWatch();
        List linkedList = new LinkedList();
        for (int i = 0; i < count; i++) {
            linkedList.add(i);
        }

        stopWatch.start("LinkedList fori");
        int num = linkedList.size();
        for (int i = 0; i < num; i++) {
            linkedList.get(i);
        }
        stopWatch.stop();

        stopWatch.start("LinkedList foreach");
        for (Object o : linkedList) {

        }
        stopWatch.stop();

        stopWatch.start("LinkedList iterator");
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
