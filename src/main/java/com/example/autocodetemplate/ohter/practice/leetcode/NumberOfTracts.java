package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 是否回文数判断
 */
public class NumberOfTracts {

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
