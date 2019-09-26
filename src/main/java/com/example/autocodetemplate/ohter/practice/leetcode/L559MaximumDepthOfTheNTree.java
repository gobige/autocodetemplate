package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.List;

/**
 * 559. N叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * 例如，给定一个 3叉树 :
 */
public class L559MaximumDepthOfTheNTree {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }else {
            List<Node> nodes = root.children;
            int maxInt = 0;
            for (Node node : nodes) {
                int depth = maxDepth(node);
                if (depth > maxInt) {
                    maxInt = depth;
                }
            }
            return maxInt + 1;
        }
    }

}
