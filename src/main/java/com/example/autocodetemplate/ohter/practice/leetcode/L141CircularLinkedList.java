package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 思路一：遍历，计算每个对象的hash值，如果遍历过程有重复，则判断有环
 * 时间复杂度 n 空间复杂度 2n
 */
public class L141CircularLinkedList {
    public static void main(String[] args) {
        hasCycle(new ListNode(1));
    }
    public static boolean hasCycle(ListNode head) {

        Set set = new HashSet();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }

            set.add(head);

            head = head.next;
        }


        return false;
    }

}
