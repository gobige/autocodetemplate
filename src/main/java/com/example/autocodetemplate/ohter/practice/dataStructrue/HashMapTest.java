package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapTest {
    public static void main(String[] args) {

        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.hashConflict();

        Map<Integer,Object> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map.isEmpty());

        map.put(22, null);
        System.out.println(map.get(22));

        Set<Integer> set = new HashSet<>();
        set.add(22);
        System.out.println(set.contains(22));
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
