package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Stack;

/**
 * 206. 反转链表
 * 反转一个单链表。
 */
public class L206ReverseList {

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        listNode4.next = listNode5;
        ListNode listNode3 = new ListNode(3);
        listNode3.next = listNode4;

        ListNode listNode2 = new ListNode(2);
        listNode2.next = listNode3;

        ListNode listNode = new ListNode(1);
        listNode.next = listNode2;

        reverseList(listNode);
    }

    public static ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        Stack stack = new Stack();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode newHead = null;
        if (!stack.empty()) {
            newHead = (ListNode) stack.pop();
        }

        ListNode curNode = newHead;
        while (!stack.empty()) {
            curNode.next = (ListNode) stack.pop();
            curNode = curNode.next;
        }

        curNode.next = null;
        return newHead;
    }


}
