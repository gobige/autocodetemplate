package com.example.autocodetemplate.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * 布隆过滤器
 */
public class BloomFilterDemo {
    public static void main(String[] args) {
        //插入的key为字符串，预计数据量为一百万，并且容错率为0.01
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),
                1000000,0.01);
        bloomFilter.put("yates");
        bloomFilter.put("minming");
        System.out.println(bloomFilter.mightContain("xiaoxiao"));
        System.out.println(bloomFilter.mightContain("yates"));
    }
}
