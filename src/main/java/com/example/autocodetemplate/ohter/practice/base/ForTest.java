package com.example.autocodetemplate.ohter.practice.base;


import org.springframework.util.StopWatch;

public class ForTest {
    public static void main(String[] args) {
        Integer a;
        StopWatch stopWatch = new StopWatch();

        Integer [] as = new Integer[10000000];
        // 预热
        for (int i = 0; i < as.length; ++i) {

        }

// 不优化，直接寻址  慢
        stopWatch.start("general");
        for (int i = 0; i < as.length; ++i) {
            if (as[i] == null)
                as[i] = 0;
        }
        stopWatch.stop();

// 优化，临时变量  快
        stopWatch.start("address optmistic！");
        for (int i = 0; i < as.length; ++i) {
            if ((a = as[i]) == null)
                a = 0;
        }
        stopWatch.stop();


// 结论：不管优化后和优化前的顺序谁先执行  临时变量优化的方案都要快一些
        System.out.println(stopWatch.prettyPrint());

    }
}
