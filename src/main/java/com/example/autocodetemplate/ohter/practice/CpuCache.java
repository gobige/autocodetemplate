package com.example.autocodetemplate.ohter.practice;

import org.springframework.util.StopWatch;

public class CpuCache {

    public static void main(String[] args) {
        sameCacheLine();
    }

    /**
     * CPU会以一个Cache Line 64Bytes最小时单位加载，也就是16个32bits的整型，所以，无论你步长是2还是8
     */
    private static void sameCacheLine() {

        final int LEN = 64 * 1024 * 1024;
        int[] arr = new int[LEN];
        StopWatch sw = new StopWatch();
        sw.start("step 2");
        for (int i = 0; i < LEN; i += 2) arr[i] *= i;
        sw.stop();

        sw.start("step 8");
        for (int i = 0; i < LEN; i += 8) arr[i] *= i;
        sw.stop();

        sw.start("step 16");
        for (int i = 0; i < LEN; i += 16) arr[i] *= i;
        sw.stop();

        System.out.println(sw.prettyPrint());
    }
}
