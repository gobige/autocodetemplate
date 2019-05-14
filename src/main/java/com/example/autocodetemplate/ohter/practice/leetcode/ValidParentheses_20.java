package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Stack;

/**
 *  有效的括号
 *  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 思路 乍一看 符合栈的概念 可以使用栈来解题
 *
 * 执行用时 : 7 ms, 在Valid Parentheses的Java提交中击败了69.81% 的用户
 * 内存消耗 : 33.8 MB, 在Valid Parentheses的Java提交中击败了91.22% 的用户
 *
 * 时间复杂度 n
 * 空间复杂度 n
 */
public class ValidParentheses_20 {
    public static boolean isValid(String s) {
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

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));

    }
}
