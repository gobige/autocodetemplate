package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 *
 * 思路1：建立两个栈，当push队列时，像栈1中push数据（如果栈2不为空，先复制到栈1）；当pop出队列时，先把栈1数据复制到栈2，然后通过栈2 出栈
 */
public class L232QueueWithStack {

}

class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();

        myQueue.push(1);
        System.out.println();
        myQueue.push(2);
        System.out.println();
        System.out.println(myQueue.peek());
         myQueue.push(3);
        System.out.println(myQueue.peek());
    }

    private final Stack stack1;
    private final Stack stack2;

    public MyQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void push(int x) {
        if (!stack2.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        stack1.push(x);
    }

    public int pop() {
        if (!stack1.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.peek() == null ? null : (int) stack2.pop();
    }

     public int peek() {
        if (!stack1.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.peek() == null ? null : (int) stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}