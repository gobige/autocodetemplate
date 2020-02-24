package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 串联所有单词的子串
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 思路：RB算法每个word，每个word随意组合，每个组合进行匹配字符串s
 * 时间复杂度 n的平方乘以m
 */
public class L30ConcatenateSubstringsOfAllWords {
    public static void main(String[] args) {
        List<Integer> list = findSubstring("a", new String[]{"a"});
        System.out.println(list);
    }

    public static List<Integer> findSubstring(String haystack, String[] words) {

        List<Integer> list = new LinkedList();

        List<String> strings = recursionArrange(words, 0, words.length-1);
        System.out.println(strings);

        for (String needle : strings) {
            List<Integer> index = strStr(haystack, needle);
            if (index != null && index.size() >0) {
                list.addAll(index);
            }
        }

        return list.stream().distinct().collect(Collectors.toList());
    }

    public static List<Integer> strStr(String haystack, String needle) {
        List<Integer> list = new LinkedList<>();
        if (needle == null || needle.length() == 0) {
            list.add(0);
            return list;
        }

        int needlelength = needle.length();
        int haystacklength = haystack.length();
        if (needlelength > haystacklength) {
            return list;
        }

        int match = needle.hashCode();

        for (int i = 0; i <= haystacklength - needlelength; i++) {
            String patch = haystack.substring(i, needlelength + i);
            if (patch.hashCode() == match) {
                list.add(i);
            }
        }

        return list;
    }

    /**
    * 输出字符串数字的各个字符全排列
    * @param arrayA  给定字符串的字符数组
    * @param start  开始遍历字符与其后面各个字符将要进行交换的位置
    * @param end  字符串数组的最后一位
    * @return
    */
    public static List<String> recursionArrange(String[] arrayA, int start, int end){
        List<String> factorial = new LinkedList<>();
        if(start == end){
            StringBuilder str = new StringBuilder("");
            for (int i = 0; i < arrayA.length; i++) {
                str.append(arrayA[i]);
            }
            factorial.add(str.toString());
        } else{
            for(int i = start;i <= end;i++){
                swap(arrayA,i,start);
                factorial.addAll(recursionArrange(arrayA,start+1,end));
                swap(arrayA,i,start);
            }
        }

        return factorial;
    }

    //交换数组m位置和n位置上的值
    public static void swap(String[] arrayA,int m,int n){
        String temp = arrayA[m];
        arrayA[m] = arrayA[n];
        arrayA[n] = temp;
    }

}
