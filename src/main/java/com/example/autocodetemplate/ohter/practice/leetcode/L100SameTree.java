package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 思路：递归循环判断，先左节点，再右节点
 * 时间复杂度 2n，空间复杂度 1
 *
 * 思路2：遍历两个数组所有元素组成一个数组结构，然后对比数组值
 */
public class L100SameTree {

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        p.left = p2;
        TreeNode q = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        q.right = q2;

        System.out.println(isSameTree(p, q));

    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // 节点都空
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            // 节点值不一致
            if (p.val != q.val) {
                return false;
            }else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }else {
            // 其中一节点为空
            return false;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}