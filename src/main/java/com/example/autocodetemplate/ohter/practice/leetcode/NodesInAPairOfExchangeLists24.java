package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路 遍历奇数与下一个数继续交换
 */
public class NodesInAPairOfExchangeLists24 {

    public static ListNode swapPairs(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode nextNode = head.next;
        if (nextNode.next != null) {
            head.next = swapPairs(nextNode.next);
        }
        nextNode.next = head;

        return nextNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode ws = swapPairs(head);
        System.out.println(ws);
    }
}
