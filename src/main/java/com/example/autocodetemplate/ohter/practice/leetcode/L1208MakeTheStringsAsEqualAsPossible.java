package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 1208. 尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 * 思路1：将两个字符串转换为两个ASCII的数组，这样问题将变成了求两个数组各对应下标，相减绝对值相加最和的问题
 *
 * 使用贪心法则获取
 */
public class L1208MakeTheStringsAsEqualAsPossible {

    public static void main(String[] args) {
        equalSubstring("krpgjbjjznpzdfy", "nxargkbydxmsgby", 14);
    }

    public static int equalSubstring(String s, String t, int maxCost) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return 0;
        }

        char[] sAcsii = s.toCharArray();
        char[] tAcsii = t.toCharArray();
        int[] rAcsii = new int[sAcsii.length];

        for (int i = 0; i < sAcsii.length; i++) {
            rAcsii[i] = Math.abs(sAcsii[i] - tAcsii[i]);
        }

        int indexL = 0;
        int indexR = 0;

        int maxL = 0;
        int sum = 0;
        while (indexR < sAcsii.length) {
            if (sum + rAcsii[indexR] <= maxCost) {
                sum = sum + rAcsii[indexR];
                indexR++;
            } else {
                sum = sum - rAcsii[indexL] + rAcsii[indexR];
                indexL++;
                indexR++;
            }
        }

        maxL = indexR - indexL;

        return maxL;
    }

}
