package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.BitSet;

/**
 * 530. 二叉搜索树的最小绝对差
 *
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 *
 * 思路1 先遍历所有节点存放到数组，然后求最短步长
 *
 * 时间复杂度 2N，根据二叉搜索树特点可优化 空间复杂度N
 *
 */
public class L530MinimumAbsoluteDifferenceOfBinarySearchTrees {
    public static void main(String[] args) {
        L530MinimumAbsoluteDifferenceOfBinarySearchTrees l = new L530MinimumAbsoluteDifferenceOfBinarySearchTrees();

        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        root.right = root1;

        System.out.println( l.getMinimumDifference(root));
    }
    public int getMinimumDifference(TreeNode root) {
        BitSet bitSet = new BitSet();

        TreeNode max = root;
        int s = ss(root, bitSet, max);
        if (s == 0) {
            return 0;
        }

        int left = 0;
        boolean start = true;
        int step = max.val;
        for (Integer i = 0; i <= max.val; i++) {
            if (bitSet.get(i) && start) {
                start = false;
                left = i;
            } else if (bitSet.get(i) && !start) {
                if (i - left < step) {
                    step = i - left;
                }

                left = i;
            }
        }

        return step;
    }

    public int ss(TreeNode node, BitSet bitSet,TreeNode max) {
        if (node != null) {
            if (bitSet.get(node.val)) {
                return 0;
            } else {
                bitSet.set(node.val);
            }

            if (node.val > max.val) {
                max.val = node.val;
            }

            int l = ss(node.left, bitSet, max);
            if (l == 0) {
                return 0;
            }

            int r = ss(node.right, bitSet, max);
            if (r == 0) {
                return 0;
            }
        }

        return 1;
    }
}
