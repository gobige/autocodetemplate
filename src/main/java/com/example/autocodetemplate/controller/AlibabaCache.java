package com.example.autocodetemplate.controller;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 设计最多保存n个项目的本地缓存的类，用于缓存，需要满足LRU、线程安全。举例：AlibabaCache cache=new AlibabaCache(1000）
 */
public class AlibabaCache {
    private Integer cacheNum;
    private LinkedHashMap<String, CacheItem> linkedHashMap = new LinkedHashMap<String, CacheItem>(4, 0.6f, true);

    AlibabaCache(Integer cacheNum) {
        this.cacheNum = cacheNum;
    }

    public static void main(String[] args) {
        AlibabaCache cache = new AlibabaCache(1000);


    }

    public CacheItem put(CacheItem cacheItem) {
        return linkedHashMap.put(cacheItem.getName(), cacheItem);
    }


    public Integer getCacheNum() {
        return cacheNum;
    }

    public void setCacheNum(Integer cacheNum) {
        this.cacheNum = cacheNum;
    }
}


class CacheItem {
    private String name;
    private Date order;
    public CacheItem() {}

    public CacheItem(String name, Date order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrder() {
        return order;
    }

    public void setOrder(Date order) {
        this.order = order;
    }
}