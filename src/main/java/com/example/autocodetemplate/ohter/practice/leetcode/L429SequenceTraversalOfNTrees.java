package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 429. N叉树的层序遍历
 *
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 例如，给定一个 3叉树 :
 */
public class L429SequenceTraversalOfNTrees {
    public static void main(String[] args) {

        Node node6 = new Node(6,null);
        Node node5 = new Node(5,null);
        Node node3 = new Node(3, Arrays.asList(node5, node6));
        Node node2 = new Node(2,null);
        Node node4 = new Node(4,null);
        Node node1 = new Node(1,Arrays.asList(node3, node2, node4));


        L429SequenceTraversalOfNTrees f = new L429SequenceTraversalOfNTrees();
        System.out.println(f.levelOrder(node1));
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new LinkedList<List<Integer>>();
        if (root == null) {
            return new ArrayList<>(0);
        }
        List<Integer> list = new LinkedList<>();
        list.add(root.val);
        lists.add(list);

        getChilds(lists, Arrays.asList(root));

        return lists;
    }

    private void getChilds(List<List<Integer>> lists, List<Node> parents) {
        if (parents == null || parents.size() == 0) {
            return;
        }

        List<Node> childNodes = new LinkedList<>();
        List<Integer> childs = new LinkedList<>();
        for (Node node : parents) {
            if (node.children != null && node.children.size() > 0) {
                for (Node cnode : node.children) {
                    childs.add(cnode.val);
                    childNodes.add(cnode);
                }
            }
        }
        if (childs != null && childs.size() > 0) {
            lists.add(childs);
            getChilds(lists, childNodes);
        }
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};