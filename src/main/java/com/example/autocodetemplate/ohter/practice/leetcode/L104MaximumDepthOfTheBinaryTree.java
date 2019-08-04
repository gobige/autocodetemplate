package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.validation.constraints.Max;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。\
 *
 * 思路：深度优先遍历算法
 */
public class L104MaximumDepthOfTheBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }else {
            int leftd = maxDepth(root.left);
            int rightd = maxDepth(root.right);

            return Math.max(leftd, rightd) + 1;
        }
    }
}
