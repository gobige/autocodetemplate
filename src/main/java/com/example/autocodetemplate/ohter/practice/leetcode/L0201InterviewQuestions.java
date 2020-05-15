package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.domain.Apple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 02.01. 移除重复节点
 *
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 */
@Slf4j
public class L0201InterviewQuestions {
    public static void main(String[] args) {
        System.out.println(ObjectUtils.identityToString(new Apple(1,2,"china")));
     }


    public static ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        if (head == null) {
            return null;
        }
        set.add(head.val);
        ListNode next;
        ListNode cur = head;
        while (true) {
            next = cur.next;
            if (next == null) {
                return head;
            }

            if (set.contains(next.val)) {
                cur.next = next.next;
            } else {
                set.add(next.val);
                cur = next;
            }
        }
    }

}



