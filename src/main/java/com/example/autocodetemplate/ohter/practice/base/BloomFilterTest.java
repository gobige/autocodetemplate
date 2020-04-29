package com.example.autocodetemplate.ohter.practice.base;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BloomFilterTest {

    private static final BloomFilter<CharSequence> filter = BloomFilter.create(new Funnel<CharSequence>() {
        @Override
        public void funnel(CharSequence arg0, PrimitiveSink arg1) {
            arg1.putString(arg0, Charsets.UTF_8);
        }
    }, 10000000, 0.001F);

    public static void main(String[] args) {
        List<String> hello = new ArrayList<String>(){{
            add("123123");
            add("311222");
            add("443232");
            add("423412");
            add("123123");
            add("443232");
            add("123123");
        }};

        for (String s : hello) {
            if (filter.mightContain(s)) {
                log.info("exsit,{}", s);
            }else {
                filter.put(s);
                log.info("not exsit,{}", s);
            }
        }
    }
}
