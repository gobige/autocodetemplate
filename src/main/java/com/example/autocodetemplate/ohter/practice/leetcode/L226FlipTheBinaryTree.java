package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 思路1：通过遍历或递归元素并对调子元素
 * 时间复杂度 n
 * 空间复杂度1
 */
public class L226FlipTheBinaryTree {
    public TreeNode invertTree(TreeNode root) {

        recursive(root);

        return root;
    }

    public void recursive(TreeNode node) {
        if (node != null) {
            TreeNode temp = null;
            temp = node.left;
            node.left = node.right;
            node.right = temp;

            recursive(node.left);
            recursive(node.right);
        }
    }
}
