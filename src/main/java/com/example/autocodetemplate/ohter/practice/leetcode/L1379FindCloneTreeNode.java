package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 找出克隆二叉树中的相同节点
 *
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身
 *
 */
public class L1379FindCloneTreeNode {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {


        TreeNode treeNode = reverse(cloned, target);
        return treeNode;
    }

    public final TreeNode reverse(TreeNode cloned, final TreeNode target) {
        if (cloned.val == target.val) {
            return cloned;
        }

        if (cloned.left != null) {
            TreeNode treeNode = reverse(cloned.left, target);
            if (treeNode != null) {
                return treeNode;
            }
        }
        if (cloned.right != null) {
            TreeNode treeNode = reverse(cloned.right, target);
            if (treeNode != null) {
                return treeNode;
            }
        }

        return null;
    }
}
