package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 思路1，遍历列表，删除匹配节点(要先找到第一个不是 匹配节点的头结点)
 *
 */
public class L203RemoveLinkedListElements {

    public static void main(String[] args) {
    }

    public static ListNode removeElements(ListNode head, int val) {

        if (head == null) {
            return head;
        }
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            }else {
                break;
            }
        }
        if (head == null) {
            return head;
        }
        ListNode curNode = head.next;
        ListNode ValidNode = head;
        while (curNode != null) {
            if (curNode.val != val) {
                ValidNode.next = curNode;
                ValidNode = ValidNode.next;
            }else {
                ValidNode.next = null;
            }

            curNode = curNode.next;
        }

        return head;
    }
}
