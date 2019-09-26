package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 对从根结点开始的左子树和右子树分别的深度优先遍历
 */
public class L543DiameterOfTheBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 0;
        }

        return Math.max(diameterOfBinaryTree(root.right), diameterOfBinaryTree(root.left)) + 1;
    }


    public int returnNum(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(returnNum(node.right), returnNum(node.left) + 1);
        }
    }
}
