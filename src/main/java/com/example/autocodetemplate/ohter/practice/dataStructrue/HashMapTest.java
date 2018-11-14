package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {

        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.hashConflict();

        Map<Integer,Object> map = new HashMap<>();
        map.put(1, "12");
        System.out.println(map.isEmpty());
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
