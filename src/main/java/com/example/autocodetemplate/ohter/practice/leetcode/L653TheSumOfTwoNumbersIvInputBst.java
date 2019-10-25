package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 思路1  hashset法，遍历树，并将节点存入set中，若有 r - val = set中的值则返回true
 * 思路2 bst法，遍历，findNode是否存在 r - val ，返回true
 * 思路3 遍历到列表list中，然后双指针求值
 */
public class L653TheSumOfTwoNumbersIvInputBst {

    public boolean findTarget(TreeNode root, int k) {
        return findTarget2(root, root, k);
    }

    public static boolean findTarget2(TreeNode node, TreeNode root, int k) {
        if (node == null) {
            return false;
        }

        TreeNode treeNode = findBstNode(k - node.val, root);
        if (treeNode != null && treeNode != node) {
            return true;
        }

        return findTarget2(node.left, root, k) || findTarget2(node.right, root, k);
    }

    public static TreeNode findBstNode(int val,TreeNode node) {
        if (node == null) {
            return null;
        } else if (node.val == val) {
            return node;
        } else if (node.val < val) {
            return findBstNode(val, node.right);
        } else {
            return findBstNode(val, node.left);
        }
    }
}
