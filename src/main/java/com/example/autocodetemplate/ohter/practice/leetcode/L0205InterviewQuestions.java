package com.example.autocodetemplate.ohter.practice.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 面试题 02.05. 链表求和
 *
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 *
 * 这些数位是反向存放的，也就是个位排在链表首部。
 *
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 *
 */
@Slf4j
public class L0205InterviewQuestions {
    public static void main(String[] args) {
     }



    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tempNode = new ListNode(0);

        addTwoNumbers(l1, l2, tempNode, false);

        return tempNode.next;
    }


    private static void addTwoNumbers(ListNode l1, ListNode l2,ListNode parent,boolean carry) {
        if (l1 != null && l2 != null) {
            int val = l1.val + l2.val;
            if (carry) {
                val++;
            }
            ListNode node;
            if (val > 9) {
                node = new ListNode(val % 10);
                carry = true;
            }else {
                node = new ListNode(val);
                carry = false;
            }
            parent.next = node;
            addTwoNumbers(l1.next, l2.next, node, carry);
        } else if (l1 != null) {
            int val = l1.val;
            if (carry) {
                val++;
            }
            ListNode node;
            if (val > 9) {
                node = new ListNode(val % 10);
                carry = true;
            }else {
                node = new ListNode(val);
                carry = false;
            }
            parent.next = node;
            addTwoNumbers(l1.next, null, node, carry);
        } else if (l2 != null) {
            int val = l2.val;
            if (carry) {
                val++;
            }
            ListNode node;
            if (val > 9) {
                node = new ListNode(val % 10);
                carry = true;
            }else {
                node = new ListNode(val);
                carry = false;
            }
            parent.next = node;
            addTwoNumbers(null, l2.next, node, carry);
        } else if (carry) {
            parent.next = new ListNode(1);
        }
    }
}



