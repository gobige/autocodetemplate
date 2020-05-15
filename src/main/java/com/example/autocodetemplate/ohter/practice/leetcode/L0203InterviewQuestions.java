package com.example.autocodetemplate.ohter.practice.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 面试题 02.03. 删除中间节点
 *
 * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
 *
 */
@Slf4j
public class L0203InterviewQuestions {
    public static void main(String[] args) {
     }


    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}



