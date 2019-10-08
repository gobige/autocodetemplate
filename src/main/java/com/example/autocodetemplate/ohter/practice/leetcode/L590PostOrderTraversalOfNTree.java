package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 590. N叉树的后序遍历
 *
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 */
public class L590PostOrderTraversalOfNTree {
    public List<Integer> postorder(Node root) {
        List<Integer> list = new LinkedList<>();

        recursive(root, list);

        return list;
    }

    private void recursive(Node root, List<Integer> list) {
        if (root != null) {
            if (root.children != null) {
                for (Node child : root.children) {
                    recursive(child, list);
                }
            }

            list.add(root.val);
        }

    }
}
