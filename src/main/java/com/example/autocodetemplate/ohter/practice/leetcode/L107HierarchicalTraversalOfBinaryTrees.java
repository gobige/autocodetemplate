package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 思路1：使用栈来遍历树，出栈即得到结果
 */
public class L107HierarchicalTraversalOfBinaryTrees {

    public static void main(String[] args) {
        L107HierarchicalTraversalOfBinaryTrees ds = new L107HierarchicalTraversalOfBinaryTrees();

        TreeNode node = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node6 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        node.left = node2;
        node.right = node3;

        node3.left = node6;
        node3.right = node7;

        List<List<Integer>> sdf = ds.levelOrderBottom(node);

        System.out.println(sdf);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> stack = new Stack<>();
        if (root != null) {
            stack.push(Arrays.asList(root.val));
        }
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (true) {
            List<Integer> integers = new LinkedList<>();
            nodes = getSonTreeNode(nodes);
            if (isAllNull(nodes)) {
                 break;
            }

            for (TreeNode node : nodes) {
                if (node != null) {
                    integers.add(node.val);
                }
            }
            stack.push(integers);
        }

        List<List<Integer>> lists = new LinkedList<>();

        while (!stack.isEmpty()) {
            lists.add(stack.pop());
        }
        return lists;
    }

    /**
     * 根据父节点获取所有子节点
     * @param parentNode
     * @return
     */
    public List<TreeNode> getSonTreeNode(List<TreeNode> parentNode) {
        List<TreeNode> sons = new LinkedList<>();
        if (parentNode == null || parentNode.size() == 0) {
            return null;
        }

        for (TreeNode treeNode : parentNode) {
            if (treeNode == null) {
                sons.add(null);
                sons.add(null);
            }else {
                sons.add(treeNode.left);
                sons.add(treeNode.right);
            }
        }

        return sons;
    }

    /**
     * 检查是否所有元素都为null
     *
     * @param nodes
     * @return
     */
    public boolean isAllNull(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (node != null) {
                return false;
            }
        }

        return true;
    }
}
