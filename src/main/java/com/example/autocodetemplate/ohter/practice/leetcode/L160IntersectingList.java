package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 *思路1 两个链表，相互遍历，并把对象存入hash表中，如有冲突则该节点为相交节点
 *时间复杂度 m+n 空间复杂度 小于m+n
 *
 * 思路2 快慢指针
 *
 * 时间复杂度 m+n 空间复杂度 1
 */
public class L160IntersectingList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set set = new HashSet();

        while (headA != null || headB != null) {
            if (headA != null) {
                if (set.contains(headA)) {
                    return headA;
                }
                set.add(headA);
                headA = headA.next;
            }
            if (headB != null) {
                if (set.contains(headB)) {
                    return headB;
                }
                set.add(headB);
                headB = headB.next;
            }
        }

        return null;
    }
}
