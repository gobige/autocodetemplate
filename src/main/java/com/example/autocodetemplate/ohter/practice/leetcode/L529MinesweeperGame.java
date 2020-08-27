package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 529. 扫雷游戏
 *
 */
public class L529MinesweeperGame {
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
