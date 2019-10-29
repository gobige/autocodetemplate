package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 669. 修剪二叉搜索树
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 思路1：遍历搜索树，根据最小，最大节点对左右节点进行剪枝
 */
public class L669TrimBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode nodel = new TreeNode(2);
        TreeNode noder = new TreeNode(4);
        root.left = nodel;
        root.right = noder;

        trimBST(root, 2, 4);
    }
    public static TreeNode trimBST(TreeNode root, int L, int R) {

        while (true) {
            if (root == null || (root.val >= L && root.val <= R)) {
                break;
            }
            if (root.val < L) {
                root = root.right;
            } else if (root.val > R) {
                root = root.left;
            }
        }

        if (root == null) {
            return null;
        }
        goon(root.left, L, R, root, 1);
        goon(root.right, L, R, root, 2);

        return root;
    }


    public static void goon(TreeNode root, int L, int R,TreeNode parent,int dir) {
        if (root == null) {
            return;
        }

        if (root.val < L) {
            if (dir == 1) {
                parent.left = root.right;
            } else {
                parent.right = root.right;
            }
            goon(root.right, L, R, parent, 1);
        }else {
            goon(root.left, L, R, root, 1);
        }

        if (root.val > R) {
            if (dir == 1) {
                parent.left = root.left;
            } else {
                parent.right = root.left;
            }
            goon(root.left, L, R, parent, 2);
        }else {
            goon(root.right, L, R, root, 2);
        }
    }

}
