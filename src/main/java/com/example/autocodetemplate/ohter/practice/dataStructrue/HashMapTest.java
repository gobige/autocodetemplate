package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.util.*;

public class HashMapTest {
    public static void main(String[] args) {

        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.hashConflict();

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
        HashMap hashMap = new HashMap();
        hashMap.put(null, null);

        // 唯一且为可为null
        HashSet<Object> hashSet = new HashSet();
        hashSet.add(null);
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
