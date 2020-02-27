package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.List;

/**
 * 23. 合并K个排序链表
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 分别两两合并
 *
 * 时间复杂度 斐波那契数列增长
 */
public class L23CombineKSortingLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int n = lists.length;
        if (n == 1) {
            return lists[0];
        }


        ListNode result = lists[0];
        for (int i = 1; i < n; i++) {
            result = mergeTwoLists(lists[i], result);
        }

        return result;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode sortListNode = null;

        ListNode curentNode = null;

        // 确定首节点
        if (l1 != null && l2 != null) {
            if (l2.val < l1.val) {
                curentNode = l2;
                l2 = l2.next;
            }else {
                curentNode = l1;
                l1 = l1.next;
            }
            sortListNode = curentNode;
        } else if (l1 == null) {
            sortListNode = l2;

            return sortListNode;
        } else if (l2 == null) {
            sortListNode = l1;

            return sortListNode;
        }

        while (true) {
            if (l1 != null && l2 != null) {
                if (l2.val < l1.val) {
                    curentNode.next = l2;
                    l2 = l2.next;
                }else {
                    curentNode.next = l1;
                    l1 = l1.next;
                }
                curentNode = curentNode.next;
            } else if (l1 == null) {
                curentNode.next = l2;

                return sortListNode;
            } else if (l2 == null) {
                curentNode.next = l1;

                return sortListNode;
            }
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
