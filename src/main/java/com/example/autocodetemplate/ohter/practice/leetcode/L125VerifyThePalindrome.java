package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 思路：使用双指针前后遍历，略过忽略字符，忽略大小写  错了？？？
 *
 */
public class L125VerifyThePalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("9;8'4P?X:1Q8`dOfJuJXD6FF,8;`Y4! YBy'Wb:ll;;`;JI0c2uvD':!LAk:s!'0.!2B55.T1VI?00Du?1,l``RFsc?Y?9vD5 K'3'1b!N8hn:'l. R:9:o`m1r.M2mrJ?`Wjv1`G6i6G`1vjW`?Jrm2M.r1m`o:::R .l':nh8N!b1'3'K 5Dv9?Y?csFR``l,1?uD00?IV1T.55B2!.0'!s:kAL!:'Dvu2c0IJ;`;;ll9bW'yBY !4Y`;8,FF6DXJuJfOd`8Q1:X?P4'8;9"));
    }

    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            if (!isAvalid(chars[left])) {
                left++;
                continue;
            }

            if (!isAvalid(chars[right])) {
                right--;
                continue;
            }

            if (upToLow(chars[left]) != upToLow(chars[right])) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


    static boolean isAvalid(char c) {

        // 数字 字母
        if ((48 <= c && c <= 56) || (65 <= c && c <= 90) || (97 <= c && c <= 122)) {
            return true;
        }

        return false;
    }

    static char upToLow(char c) {
        if (65 <= c && c <= 90) {
            return (char) ((int) c + 32);
        }

        return c;
    }
}
