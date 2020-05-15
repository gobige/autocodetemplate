package com.example.autocodetemplate.ohter.practice.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 面试题 02.06. 回文链表
 *
 * 编写一个函数，检查输入的链表是否是回文的。
 */
@Slf4j
public class L0206InterviewQuestions {
    public static void main(String[] args) {
     }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack stack = new Stack();
        while (head != null) {
            if (stack.isEmpty()) {
                stack.push(head);
            } else if (((ListNode) stack.peek()).val == head.val) {
                stack.pop();
            }else {
                stack.push(head);
            }

            head = head.next;
        }

        return stack.isEmpty();
    }
}



