package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 * 思路  双链表遍历对比大小
 * 时间复杂度n
 * 空间复杂度n
 *
 * 执行用时 : 2 ms, 在Merge Two Sorted Lists的Java提交中击败了97.49% 的用户
 * 内存消耗 : 35.3 MB, 在Merge Two Sorted Lists的Java提交中击败了90.56% 的用户
 *
 */
public class MergingTwoOrderedLinkedLists21 {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

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



    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        ListNode sortListNode = null;

        ListNode curentNode = null;

        boolean firstNode = true;

        while (true) {
            if (l1 != null && l2 != null) {
                if (l2.val < l1.val) {
                    if (firstNode) {
                        curentNode = l2;
                        sortListNode = curentNode;
                    }else {
                        curentNode.next = l2;
                    }
                    l2 = l2.next;
                }else {
                    if (firstNode) {
                        curentNode = l1;
                        sortListNode = curentNode;
                    }else {
                        curentNode.next = l1;
                    }
                    l1 = l1.next;
                }
                curentNode = curentNode.next;
            } else if (l1 == null) {
                if (firstNode) {
                    curentNode = l2;
                    sortListNode = curentNode;
                }else {
                    curentNode.next = l2;
                }
                break;
            } else if (l2 == null) {
                if (firstNode) {
                    curentNode = l1;
                    sortListNode = curentNode;
                }else {
                    curentNode.next = l1;
                }
                break;
            }

            firstNode = false;
            curentNode = curentNode.next;
        }

        return sortListNode;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
