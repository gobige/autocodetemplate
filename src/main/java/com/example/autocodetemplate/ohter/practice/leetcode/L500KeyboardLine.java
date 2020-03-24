package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;


/**
 * 500. 键盘行
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 */
public class L500KeyboardLine {
    public String[] findWords(String[] words) {

        String line1OfKeyboard1 = "qwertyuiop";
        String line1OfKeyboard2 = "asdfghjkl";
        String line1OfKeyboard3 = "zxcvbnm";

        String indexStr;
        List<String> strings = new LinkedList<>();
        for (String word : words) {
            boolean flag = true;
            char[] chars = word.toLowerCase().toCharArray();
            indexStr = line1OfKeyboard1.indexOf(chars[0]) > -1 ? line1OfKeyboard1 : line1OfKeyboard2.indexOf(chars[0]) > -1 ? line1OfKeyboard2 : line1OfKeyboard3.indexOf(chars[0]) > -1 ? line1OfKeyboard3 : "";
            if (indexStr == "") {
                continue;
            }
            for (char c : chars) {
                if (indexStr.indexOf(c) < 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                strings.add(word);
            }
        }

        return strings.toArray(new String[]{});
    }


}
