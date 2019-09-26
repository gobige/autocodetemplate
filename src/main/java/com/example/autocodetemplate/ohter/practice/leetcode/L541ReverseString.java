package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * L541ReverseString
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
 * 如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，
 * 并将剩余的字符保持原样。
 *
 * 思路1：总的来说就是每两千字符，前面1000字符反转
 */
public class L541ReverseString {
    public static void main(String[] args) {
        L541ReverseString r = new L541ReverseString();

        r.reverseStr("abcdefg", 2);
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();

        int length = chars.length;

        int left = 0;
        int right = 2 * k;


        while (true) {
            if ((left + right) / 2 <= length) {
                rotato(left, (left + right) / 2, chars);
            }else {
                rotato(left, length, chars);
                break;
            }

            left += 2 * k;
            right += 2 * k;
        }

        return String.valueOf(chars);
    }

    public void rotato(int left, int right, char[] chars) {
        char temp;
        while (left < right) {
            temp = chars[left];
            chars[left] = chars[right-1];
            chars[right-1] = temp;
            left++;
            right--;
        }
    }
}
