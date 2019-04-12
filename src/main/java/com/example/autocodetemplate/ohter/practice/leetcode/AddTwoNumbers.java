package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
         listNode.next = listNode2;
        ListNode listNode4 = new ListNode(1);

        ListNode listNoderesult = addTwoNumbers(listNode, listNode4);
        System.out.println(listNoderesult);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumber(l1, l2, false);
    }

    public static ListNode addTwoNumber(ListNode l1Next, ListNode l2Next, boolean preCarry,int sum) {
        boolean carry = false;
        int singleDigitAfterCarry = 0;
        if (sum > 9) {
            carry = true;
            singleDigitAfterCarry = sum % 10;
        }else {
            singleDigitAfterCarry = sum;
        }

        if (preCarry) {
            singleDigitAfterCarry += 1;
        }

        if (singleDigitAfterCarry > 9) {
            carry = true;
            singleDigitAfterCarry = singleDigitAfterCarry % 10;
        }

        ListNode listNode = new ListNode(singleDigitAfterCarry);
        listNode.next = addTwoNumber(l1Next, l2Next, carry);

        return listNode;
    }

    public static ListNode addTwoNumber(ListNode l1, ListNode l2,boolean preCarry) {
        if (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;

            return addTwoNumber(l1Next, l2Next, preCarry, sum);
        }
        if (l1 != null) {
            int sum = l1.val;
            ListNode l1Next = l1.next;
            ListNode l2Next = l2;

            return addTwoNumber(l1Next, l2Next, preCarry, sum);
        }
        if (l2 != null) {
            int sum = l2.val;
            ListNode l1Next = l1;
            ListNode l2Next = l2.next;

            return addTwoNumber(l1Next, l2Next, preCarry, sum);
        }
        if (preCarry) {
            return new ListNode(1);
        }

        return null;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
}