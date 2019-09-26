package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 思路1：使用右序遍历，累加
 */
public class L538ConvertABinarySearchTreeToACumulativeTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(13);

        node1.left = node2;
        node1.right = node3;
        convertBST(node1);
    }

    public static TreeNode convertBST(TreeNode root) {
        // 计算一个节点值，应该先得到右边节点的值
        List<TreeNode> list = new LinkedList();
        kdd(root, list);

        int sum = 0;
        for (TreeNode node : list) {
            node.val = node.val + sum;
            sum = node.val;
        }

        return root;
    }

    public static void kdd(TreeNode node, List<TreeNode> sort) {
        if (node != null) {
            if (node.right != null) {
                kdd(node.right, sort);
            }
            sort.add(node);
            if (node.left != null) {
                kdd(node.left, sort);
            }
        }
    }
}
