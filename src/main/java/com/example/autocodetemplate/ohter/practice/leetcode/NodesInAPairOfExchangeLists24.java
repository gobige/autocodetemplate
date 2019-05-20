package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.List;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路1 遍历奇数与下一个数继续交换 使用递归的手段来进行
 * 思路2 遍历到一个数组中，然后分组交换数组对象，最后遍历数组重新组装链表
 */
public class NodesInAPairOfExchangeLists24 {

    public static ListNode swapPairs(ListNode head) {

        if (head == null) {
            return null;
        }

        int i = 1;
        ListNode curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
            i++;
        }

        ListNode[] listNodeArr = new ListNode[i];
        curNode = head;
        int k = 1;
        listNodeArr[k] = curNode;
        while (curNode.next != null) {
            listNodeArr[k] = curNode.next;
            curNode = curNode.next;
            k++;
        }



        return nextNode;
    }

    public static ListNode revertArr(Integer stepSize, ListNode[] listNodeArr) {
        ListNode firstNode = null;

        if (stepSize >= listNodeArr.length) {

        }
        for (int i = 0; i < listNodeArr.length; i++) {

        }
    }

    public static ListNode swapPairs1(ListNode head) {
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
