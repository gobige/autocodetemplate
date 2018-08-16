package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {

        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.hashConflict();
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
