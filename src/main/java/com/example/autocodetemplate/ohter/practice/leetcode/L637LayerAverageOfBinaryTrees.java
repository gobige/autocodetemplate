package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.*;

/**
 * 637. 二叉树的层平均值
 *
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 思路1：遍历每一层，并计算
 */
public class L637LayerAverageOfBinaryTrees {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> stack = new LinkedList<>();
        if (root == null) {
            return new ArrayList<>(0);
        }

        stack.add(Double.valueOf(root.val));

        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (true) {
            List<TreeNode> sons = new LinkedList<>();
            Double songInte = new Double(0.0);
            if (nodes == null || nodes.size() == 0) {
                return null;
            }

            int divide = 0;
            for (TreeNode treeNode : nodes) {
                if (treeNode.left != null) {
                    sons.add(treeNode.left);
                    songInte += treeNode.left.val;
                    divide++;
                }
                if (treeNode.right != null) {
                    sons.add(treeNode.right);
                    songInte += treeNode.right.val;
                    divide++;
                }
            }
            nodes = sons;

            if (divide == 0) {
                break;
            }
            stack.add(songInte / divide);
        }

        return stack;
    }


}
