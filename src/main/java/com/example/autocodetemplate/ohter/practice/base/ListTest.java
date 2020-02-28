package com.example.autocodetemplate.ohter.practice.base;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * List集合效率对比
 */
public class ListTest {

    public static void main(String[] args) {
        int count = 1000000;
        // 100W明显arraylist比linkedlist快 46:54 addHead < arrylist < addLast
        // 10W明显linkedlist比arraylist快 38:62
        // 60W明显arraylist比linkedlist快 23:77
        // 综上所述以50w为分割线，新增小于50w使用LinkedList合适，大于50使用Arraylist合适
        // 解析：ArrayList每次新增都是在数组后面新增元素，虽然每次扩容会重新复制一个数组，但是由于物理内存地址是连续的，所以IO的寻址上面是由于LinkedList的
        // LinkedList新增默认是在末尾条件元素，如果是指定位置插入还想进行遍历寻址（开销更大）
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

}
