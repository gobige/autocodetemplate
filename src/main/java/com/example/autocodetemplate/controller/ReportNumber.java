package com.example.autocodetemplate.controller;

/**
 * 标号1-n的n个人首尾相接，1到3报数，报到3的退出，求最后一个人的标号；
 */
public class ReportNumber {
    public static void main(String[] args) {

        ListNode num1 = new ListNode(1);
        ListNode num2 = new ListNode(2);
        num1.next = num2;
        ListNode num3 = new ListNode(3);
        num2.next = num3;
        ListNode num4 = new ListNode(4);
        num3.next = num4;
        num4.next = num1;

        System.out.println(reportNum(num1).val);
    }

    public static ListNode reportNum(ListNode curNode) {
        int i = 1;
        while (true) {
            if (i == 2) {
                curNode.next = curNode.next.next;
                i = 0;
            }
            if (curNode == curNode.next) {
                break;
            }
            i++;
            curNode = curNode.next;
        }

        return curNode;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}