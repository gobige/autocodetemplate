package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 */
public class L589PreorderTraversalOfNTree {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new LinkedList<>();

        recursive(root, list);

        return list;
    }

    private void recursive(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);

            if (root.children != null) {
                for (Node child : root.children) {
                    recursive(child, list);
                }
            }
        }

    }
}
