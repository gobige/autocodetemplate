package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 思路1：因为每个节点都只有一个父节点，那么N个叶子节点就会有N条从根节点到叶子节点的路径
 * 每一次 分叉都创建一个新的字符串，加入list
 *
 * 时间复杂度 N 空间复杂度 N
 *
 *
 *
 */
public class L257AllPathsToTheBinaryTree {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new LinkedList<>();
        List<List<String>> llist = new LinkedList<List<String>>();

        if (root != null) {
            List<String> strL = new LinkedList<>();
            strL.add(root.val + "");
            // 优化 当判断无节点时，将 string加入list列表
            llist.add(strL);

            sdf(root.left, root.right, strL, llist);
        }

        for (List<String> strs : llist) {
            String str = new String();
            for (String s : strs) {
                str += s;
            }

            list.add(str);
        }

        return list;
    }


    public void sdf(TreeNode left, TreeNode right, List<String>  sb, List<List<String>> list) {
        if (left != null && right != null) {
            List<String> strL = new LinkedList<>(sb);

            sb.add("->" + left.val);

            strL.add("->" + right.val);
            list.add(strL);
            sdf(left.left, left.right, sb, list);
            sdf(right.left, right.right, strL, list);
        } else if (left != null) {
            sb.add("->" + left.val);
            sdf(left.left, left.right, sb, list);
        } else if (right != null) {
            sb.add("->" + right.val);
            sdf(right.left, right.right, sb, list);
        }
    }
}