package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class L102SequenceTraversalOfBinaryTree {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (root == null) {
            return list;
        }
        List<TreeNode> levelNodes = new LinkedList<>();
        levelNodes.addAll(Arrays.asList(root));


        List<Integer> leves = new LinkedList<>();
        leves.add(root.val);
        list.add(leves);
        traverseArrays(list, levelNodes);

        return list;
    }

    private void traverseArrays(List<List<Integer>> list, List<TreeNode> parents) {
        if (parents == null || parents.size() == 0) {
            return;
        }

        List<TreeNode> levelNodes = new LinkedList<>();
        List<Integer> levels = new LinkedList<>();
        for (TreeNode parent : parents) {
            if (parent.left != null) {
                levels.add(parent.left.val);
                levelNodes.add(parent.left);
            }
            if (parent.right != null) {
                levels.add(parent.right.val);
                levelNodes.add(parent.right);
            }
        }

        if (levels.size() > 0) {
            list.add(levels);
        }

        traverseArrays(list, levelNodes);
    }
}
