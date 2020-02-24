package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 思路：想想 如果我们想删除倒数第二个节点，那么链表的长度至少为二，那么我们就从链表头结点开始计数当到达第二个开始确定第一个为倒数第N个节点，一次遍历链表，同时更新倒数第N个数字的值
 *
 * 时间复杂度n
 * 空间复杂度n
 *
 * 执行用时 : 1 ms, 在Remove Nth Node From End of List的Java提交中击败了99.74% 的用户
 * 内存消耗 : 33.7 MB, 在Remove Nth Node From End of List的Java提交中击败了99.81% 的用户
 */
public class L19DeleteTheLastNthNodeOfTheLinkedList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);

        ListNode listNode1 = removeNthFromEnd(listNode, 1);
        System.out.println(listNode1);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) {
            return head;
        }

        ListNode ListNodeOfReciprocalN = null;
        ListNode ListNodeOfReciprocalNbefore = null;

        int i = 1;
        ListNode curNode = head;
        while (curNode != null) {
            if (i == n) {
                ListNodeOfReciprocalN = head;
            } else if (i > n) {
                ListNodeOfReciprocalNbefore = ListNodeOfReciprocalN;
                ListNodeOfReciprocalN = ListNodeOfReciprocalN.next;
            }

            i++;
            curNode = curNode.next;
        }

        if (ListNodeOfReciprocalN == null) {
            return head;
        } else if (ListNodeOfReciprocalNbefore == null && ListNodeOfReciprocalN != null) {
            return ListNodeOfReciprocalN.next;
        } else if (ListNodeOfReciprocalNbefore != null && ListNodeOfReciprocalN != null) {
            ListNodeOfReciprocalNbefore.next = ListNodeOfReciprocalN.next;
        }

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
