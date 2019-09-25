package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 思路1：使用右序遍历，累加
 */
public class L538ConvertABinarySearchTreeToACumulativeTree {
    public TreeNode convertBST(TreeNode root) {
        // 计算一个节点值，应该先得到右边节点的值
        if (root != null) {
            TreeNode rightNode = convertBST(root.right);
            root.val = rightNode.val + root.val;
        }

        return root;
    }

    public void kdd(TreeNode node,int preVal) {
        if (node != null) {
            if (node.right != null) {
                kdd(node.right, preVal);
                node.val = node.right.val + node.val;
            }else {
                node.val = node.val + preVal;
            }

            kdd(node.left, node.val);
        }
    }
}
