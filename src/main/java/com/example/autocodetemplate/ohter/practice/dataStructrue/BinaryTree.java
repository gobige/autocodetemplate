package com.example.autocodetemplate.ohter.practice.dataStructrue;

/**
 * 二叉树实现
 */
public class BinaryTree {

}

class Node {
    public int value;
    public Node left;
    public Node right;

    Node(int value) {
        this.value = value;
    }

    public void push(int value) {
        if (this.value > value) {
            if (null == this.left) {
                this.left = new Node(value);
            } else {
                this.left.push(value);
            }
        } else if (this.value < value) {
            if (null == this.right) {
                this.right = new Node(value);
            } else {
                this.right.push(value);
            }
        }
    }

    public boolean find(int value) {
        if (this.value == value) {
            return true;
        } else if (this.value > value) {
            if (null == this.left) {
                return false;
            } else {
                this.left.find(value);
            }
        } else if (this.value < value) {
            if (null == this.right) {
                return false;
            } else {
                this.right.find(value);
            }
        }
        return false;
    }

    /**
     * find left num
     */
    public void preNode() {
        if (null == this.left) {
            System.out.println(this.value);
        }
    }
}
