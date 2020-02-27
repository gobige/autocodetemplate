package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路1 遍历奇数与下一个数继续交换 使用递归的手段来进行
 * 思路2 遍历到一个数组中，然后分组交换数组对象，最后遍历数组重新组装链表
 * 时间复杂度 3n
 * 空间复杂度复杂度 n
 */
public class L24NodesInAPairOfExchangeLists {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode ws = swapPairs(head);
        System.out.println(ws);
    }

    public static ListNode swapPairs(ListNode head) {

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
        int k = 1;
        listNodeArr[0] = curNode;
        while (curNode.next != null) {
            listNodeArr[k] = curNode.next;
            curNode = curNode.next;
            k++;
        }

        Integer stepSize = 2;
        if (stepSize > listNodeArr.length) {
            return head;
        }

        return revertArr(0, stepSize - 1, listNodeArr, true, stepSize);
    }

    public static ListNode revertArr(Integer startIndex, Integer endIndex, ListNode[] listNodeArr,Boolean revert,Integer stepSize) {
        ListNode curNode = null;
        ListNode headNode = null;

        if (revert) {
            headNode = listNodeArr[endIndex];
            curNode = listNodeArr[endIndex];
            for (int i = endIndex-1; i >= startIndex; i--) {
                curNode.next = listNodeArr[i];
                curNode = curNode.next;
            }
        }else {
            headNode = listNodeArr[startIndex];
            curNode = listNodeArr[startIndex];
            for (int i = startIndex+1; i <= endIndex; i++) {
                curNode.next = listNodeArr[i];
                curNode = curNode.next;
            }
        }

        if (endIndex < listNodeArr.length - 1) {
            if (endIndex + stepSize <= listNodeArr.length - 1) {
                boolean rvert = true;
                curNode.next = revertArr(endIndex + 1, endIndex + stepSize, listNodeArr, rvert, stepSize);
            }else {
                boolean rvert = false;
                curNode.next = revertArr(endIndex + 1, listNodeArr.length - 1, listNodeArr, rvert, stepSize);
            }
        }else {
            curNode.next = null;
        }

        return headNode;
    }

    public static ListNode swapPairs1(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode nextNode = head.next;
        if (nextNode.next != null) {
            head.next = swapPairs(nextNode.next);
        }
        nextNode.next = head;

        return nextNode;
    }

}
