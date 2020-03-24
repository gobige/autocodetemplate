package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 思路1：因为有序，直接遍历，去除重复数据，时间复杂度N，空间复杂度N
 */

public class L83DeleteDuplicateElementsInTheSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curNode = head;
        while (curNode.next != null) {
            if (curNode.next.val == curNode.val) {
                curNode.next = curNode.next.next;
            }else {
                curNode = curNode.next;
            }
        }

        return head;
    }
}
