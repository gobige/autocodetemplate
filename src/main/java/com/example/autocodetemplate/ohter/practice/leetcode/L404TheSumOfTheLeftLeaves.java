package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 */
public class L404TheSumOfTheLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        Integer result = new Integer(0);
        result = recurse(root, result);

        return result;
    }

    Integer recurse(TreeNode node, Integer result) {
        if (node == null) {
            return 0;
        }
        if (node.left != null && node.right != null) {
            if (node.left.left == null && node.left.right == null) {
                return result + node.left.val + recurse(node.left, result) + recurse(node.right, result);
            }else {
                return result + recurse(node.left, result) + recurse(node.right, result);
            }
        }
        if (node.right != null) {
            return result + recurse(node.right, result);
        }
        if (node.left != null) {
            if (node.left.left == null && node.left.right == null) {
                return result + node.left.val + recurse(node.left, result);
            } else {
                return result + recurse(node.left, result);
            }
        }

        return 0;
    }
}
