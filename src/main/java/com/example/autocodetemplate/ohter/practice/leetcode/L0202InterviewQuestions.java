package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.domain.Apple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 02.02. 返回倒数第 k 个节点
 *
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 */
@Slf4j
public class L0202InterviewQuestions {
    public static void main(String[] args) {
     }


    public static int kthToLast(ListNode head, int k) {
        ListNode kNode = null;
        ListNode cNode = head;

        while (cNode != null) {
            k--;
            if (k < 1) {
                if (kNode != null) {
                    kNode = kNode.next;
                }else {
                    kNode = head;
                }
            }

            cNode = cNode.next;
        }

        return kNode != null ? kNode.val : null;
    }

}



