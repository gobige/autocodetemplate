package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 *
 * 思路1 根据题意 root就是最小节点，只需用中序遍历找到第一个大于该root值的节点即可得到答案
 */
public class L671BinaryTreeTheSecondSmallestNodeIn {
    public int findSecondMinimumValue(TreeNode root) {
        int minValue = root.val;

        TreeNode treeNode = findNode(root, minValue);

        return treeNode == null ? -1 : treeNode.val;
    }

    public TreeNode findNode(TreeNode node, int min) {
        if (node == null) {
            return null;
        }

        TreeNode treeNode = findNode(node.left, min);
        if (treeNode != null) {
            return treeNode;
        }
        if (node.val > min) {
            return node;
        }

        TreeNode treeNode2 = findNode(node.right, min);
        if (treeNode2 != null) {
            return treeNode2;
        }

        return null;
    }
}
