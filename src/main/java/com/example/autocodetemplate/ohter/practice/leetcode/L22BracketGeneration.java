package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 22. 括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 思路 先生成所有括号的可能性 在通过函数进行验证
 *
 * 还有一种思路 左边（数量大于)数量即可继续递归 手动狗头
 *
 * 执行用时 : 285 ms, 在Generate Parentheses的Java提交中击败了5.01% 的用户
 * 内存消耗 : 49.6 MB, 在Generate Parentheses的Java提交中击败了5.02% 的用户
 */
public class L22BracketGeneration {

    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        System.out.println(list);
    }

    public static List<String> generateParenthesis(int n) {
        char[] brackets = new char[]{'(', ')'};

        double m = new Double(2);
        double n1 = new Double(2 * n);
        int loop = 2 * n - 1;
        List<String> list = new ArrayList<>(new Double(Math.pow(m, n1)).intValue());
        // 确定第一层

        int level = 0;
        if (level == loop) {
            list.add("(" + "");
        }else {
            recursive(brackets, loop, "(" + "", level + 1, list);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            if (!checkValide(s)) {
                iterator.remove();
            }
        }

        return list;
    }

    /**
     *
     * @param brackets
     * @param loop
     * @param str
     * @param level
     * @param list
     */
    public static void recursive(char[] brackets, int loop, String str, Integer level, List<String> list) {
        for (char a : brackets) {
            if (level == loop) {
                list.add(str + a);
            } else {
                recursive(brackets, loop, str + a, level + 1, list);
            }
        }
    }


    /**
     * 验证括号组合是否有效
     * @param s
     * @return
     */
    public static boolean checkValide(String s) {
        char[] chars = s.toCharArray();
        Stack stack = new Stack();
        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == ')') {
                if (stack.empty()) {
                    return false;
                }else {
                    if (c != (char) stack.pop()) {
                        return false;
                    }
                }
            }else if (c == '}') {
                if (stack.empty()) {
                    return false;
                }else {
                    if (c != (char) stack.pop()) {
                        return false;
                    }
                }
            }else if (c == ']') {
                if (stack.empty()) {
                    return false;
                }else {
                    if (c != (char) stack.pop()) {
                        return false;
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
