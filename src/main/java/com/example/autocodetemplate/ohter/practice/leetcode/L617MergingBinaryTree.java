package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 617. 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 思路1 两棵树同时遍历
 */
public class L617MergingBinaryTree {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }

        TreeNode tresult = null;
        if (t1 != null && t2 != null) {
            tresult = new TreeNode(t1.val + t2.val);
            mergeTrees(t1, t2, tresult);
        }else
        if (t1 != null) {
            tresult = new TreeNode(t1.val);
            mergeTrees(t1, null, tresult);
        }else
        if (t2 != null) {
            tresult = new TreeNode(t2.val);
            mergeTrees(null, t2, tresult);
        }

        return tresult;
    }

    void mergeTrees(TreeNode t1, TreeNode t2, TreeNode tresult) {
        if (t1 == null && t2 == null) {
            return;
        }

        if (t1 != null) {
            if (t1.left != null) {
                TreeNode left = new TreeNode(t1.left.val);
                tresult.left = left;
            }
            if (t1.right != null) {
                TreeNode right = new TreeNode(t1.right.val);
                tresult.right = right;
            }
        }

        if (t2 != null) {
            if (t2.left != null) {
                if (tresult.left == null) {
                    TreeNode left = new TreeNode(t2.left.val);
                    tresult.left = left;
                }else {
                    tresult.left.val = tresult.left.val + t2.left.val;
                }
            }
            if (t2.right != null) {
                if (tresult.right == null) {
                    TreeNode right = new TreeNode(t2.right.val);
                    tresult.right = right;
                }else {
                    tresult.right.val = tresult.right.val + t2.right.val;
                }
            }
        }

        mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left, tresult.left);
        mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right, tresult.right);
    }
}
