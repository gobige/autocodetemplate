package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.xml.soap.Node;

/**
 *  k个一组翻转链表
 *  给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 */
public class KSetsOfFlipLinkedLists25 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode ws = reverseKGroup(head,3);
        System.out.println(ws);
    }


    public static ListNode reverseKGroup(ListNode head, int k) {
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
        int j = 1;
        listNodeArr[0] = curNode;
        while (curNode.next != null) {
            listNodeArr[j] = curNode.next;
            curNode = curNode.next;
            j++;
        }

         if (k > listNodeArr.length) {
            return head;
        }

        return NodesInAPairOfExchangeLists24.revertArr(0, k - 1, listNodeArr, true, k);
    }


}
