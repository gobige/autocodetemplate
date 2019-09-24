package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 501. 二叉搜索树中的众数
 *给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 *
 */
public class L501TheModeInTheBinarySearchTree {
    public int[] findMode(TreeNode root) {
        int Number;
        int count = 0;

        TreeNode node = root;
        while (node != null) {
            if (node.right == node) {
                count++;
            }
            if (node.left == node) {
                count++;
            }

        }

        return null;
    }
}