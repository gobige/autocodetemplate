package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *   判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class L9NumberOfTracts {

    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }


    public static boolean isPalindrome(int x) {
        String xstr = String.valueOf(x);

        char[] xchars = xstr.toCharArray();
        char[] reverseXChars = new char[xchars.length];

        int j = xchars.length - 1;
        for (int i = 0; i < xchars.length; i++, j--) {
            reverseXChars[i] = xchars[j];
        }

        try {
            int reverseX = Integer.valueOf(new String(reverseXChars));

            if (reverseX == x) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
