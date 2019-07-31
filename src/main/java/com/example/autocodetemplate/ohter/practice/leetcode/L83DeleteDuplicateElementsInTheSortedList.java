package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 思路1：
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
