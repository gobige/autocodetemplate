package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 563. 二叉树的坡度
 * 给定一个二叉树，计算整个树的坡度。
 *
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 *
 * 整个树的坡度就是其所有节点的坡度之和。
 *
 * 思路1 遍历左，右节点，求差
 * 时间复杂度 N 空间复杂度 1
 *
 */
public class L563SlopeOfBinaryTree {
    private String[] args;

    public static void main(String[] args) {

    }
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.abs(findNodeSum(root.left) - findNodeSum(root.right));
    }

    public int findNodeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }else {
            return root.val + findTilt(root.left) + findTilt(root.right);
        }
    }
}
