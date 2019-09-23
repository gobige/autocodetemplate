package com.example.autocodetemplate.ohter.practice.leetcode;

import java.time.Instant;
import java.util.BitSet;
import java.util.concurrent.CompletableFuture;

/**
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 */

public class L461HammingDistance {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        CompletableFuture completableFuture = new CompletableFuture();

    }
    public static int hammingDistance(int x, int y) {

        String xbinary = Integer.toBinaryString(x);
        String ybinary = Integer.toBinaryString(y);

        char[] xchars = null;
        char[] ychars = null;
        if (xbinary.length() > ybinary.length()) {
            xchars = xbinary.toCharArray();
            ychars = new char[xbinary.length()];

            for (int i = 1; i <= ybinary.length(); i++) {
                ychars[ychars.length - i] = ybinary.charAt(ybinary.length() - i);
            }
            for (int i = 0; i < ychars.length - ybinary.length(); i++) {
                ychars[i] = '0';
            }
        } else if (xbinary.length() < ybinary.length()) {
            ychars = ybinary.toCharArray();
            xchars  = new char[ybinary.length()];

            for (int i = 1; i <= xbinary.length(); i++) {
                xchars[xchars.length - i] = xbinary.charAt(xbinary.length() - i);
            }
            for (int i = 0; i < xchars.length - xbinary.length(); i++) {
                xchars[i] = '0';
            }
        }else {
            xchars = xbinary.toCharArray();
            ychars = ybinary.toCharArray();
        }

        int limit = 0;
        for (int i = 0; i < ychars.length; i++) {
            if (xchars[i] != ychars[i]) {
                limit++;
            }
        }

        return limit;
    }
}
