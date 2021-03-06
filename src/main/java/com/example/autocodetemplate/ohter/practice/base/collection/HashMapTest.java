package com.example.autocodetemplate.ohter.practice.base.collection;

import com.example.autocodetemplate.ohter.practice.Apple;
import org.springframework.util.StopWatch;

import java.util.*;

public class HashMapTest {
    public static final int init = 1000000;

    public static void main(String[] args) {
        int[] sadf = new int[12];
        System.out.println(sadf.length);
        testResize();
    }

    private static void testRemove() {
        Map<Integer, Apple> test = new HashMap<>();
        test.remove(1);


        // map去除value为null的对象
        test.values().removeIf(Objects::isNull);
    }

    private static void testResize() {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 33; i++) {
            map.put(i, i);
            if (i == 16) {
                System.out.println(16);
            }
        }
        System.out.println(map.size());
    }

    private static void testInitail() {
        HashMap<Integer, Object> map = new HashMap<>(init);
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("map not Initail");
        HashMap<Integer, Object> map2 = new HashMap<>();
        for (int i = 0; i < init; i++) {
            map2.put(i, new Object());
        }
        stopWatch.stop();

        stopWatch.start("map Initail");
        for (int i = 0; i < init; i++) {
            map.put(i, new Object());
        }
        stopWatch.stop();


        System.out.println(stopWatch.prettyPrint());
    }

    //    entryset ~=keyset
    private static void foriAndForeachAndIterator() {
        HashMap<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < init; i++) {
            map.put(i, new Object());
        }

        // 预热
        Set<Integer> 预热 = map.keySet();
        Iterator 预热ite = 预热.iterator();
        while (预热ite.hasNext()) {
            预热ite.next();
        }

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("map keyset");
        Set<Integer> keysets = map.keySet();
        Iterator ite = keysets.iterator();
        while (ite.hasNext()) {
            ite.next();
        }
        stopWatch.stop();

        stopWatch.start("map entryset");
        Set<Map.Entry<Integer, Object>> entries = map.entrySet();
        Iterator entrieSets = entries.iterator();
        while (entrieSets.hasNext()) {
            entrieSets.next();
        }
        stopWatch.stop();


        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * hashMap method test
     */
    private void hashMapMethodTest() {
        Map<Integer,Object> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map.isEmpty()); // false

        map.put(22, null);
        System.out.println(map.get(22)); // null

        Set<Integer> set = new HashSet<>();
        set.add(22);
        System.out.println(set.contains(22)); // true
    }

    /**
     * hashMap hashSet hashTable 存储值是否可为 null
     */
    private void hashTableCompareHashMap() {
        // key 和 value都不能为空
        Hashtable hashtable = new Hashtable();
        hashtable.put(null, null);

        // key 和 value都可为null,但key为null节点只能有一个,key一样重复插入会覆盖value值
        Map<Integer,Object> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map.isEmpty()); // false

        map.put(22, null);
        System.out.println(map.get(22)); // null

        // 唯一且为可为null
        HashSet<Object> hashSet = new HashSet();
        hashSet.add(null);

        Set<Integer> set = new HashSet<>();
        set.add(22);
        System.out.println(set.contains(22)); // true
    }


    /**
     * hash碰撞
     */
    private void hashConflict() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Aa", "yates");
        map.put("BB", "yangchao");
        map.put("name", "hahaha");
    }
}
