package com.example.autocodetemplate.ohter.practice.base.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        testAccessOrder();
    }

    private static void testAccessOrder() {
        HashMap map = new HashMap<>();
        map.put("yates", "yangchao");
        map.put("billy", "xxxass");
        map.put("tom", "clouanr");
        map.put("cluo", "ronalerdo");
        map.put("messi", "lioanda messi");
        Set<Map.Entry> entriesss = map.entrySet();

        for (Map.Entry entry : entriesss) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
        System.out.println("HashMap 无序 遍历---跟插入顺序无关------------------------------------------");

        LinkedHashMap linkedHashMap = new LinkedHashMap(4, 0.6f, true);
        linkedHashMap.put("yates", "yangchao");
        linkedHashMap.put("billy", "xxxass");
        linkedHashMap.put("tom", "clouanr");
        linkedHashMap.put("cluo", "ronalerdo");
        linkedHashMap.put("messi", "lioanda messi");

        Set<Map.Entry> entries = linkedHashMap.entrySet();

        for (Map.Entry entry : entries) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }

        System.out.println("LinkedHashMap 遍历---跟 accessOrder 有关---------------------------------------------");

        linkedHashMap.get("cluo");


        for (Map.Entry entry : entries) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }

    }
}
