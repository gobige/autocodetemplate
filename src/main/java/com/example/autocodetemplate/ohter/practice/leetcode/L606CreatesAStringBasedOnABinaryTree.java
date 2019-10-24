package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 606. 根据二叉树创建字符串
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 *
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * 思路 1，做前置节点遍历 右节点不为空，左节点为空节点不进行删除（）
 *
 * 时间复杂度3n 空间复杂度 n
 */
public class L606CreatesAStringBasedOnABinaryTree {
    public String tree2str(TreeNode t) {
        StringBuilder stringBuilder = new StringBuilder();

        if (t == null) {
            return "";
        }
        stringBuilder.append(t.val);

        if (t.left != null || t.right != null) {
            preorderTraversal(t.left, stringBuilder,false);
            preorderTraversal(t.right, stringBuilder,true);
        }

        return stringBuilder.toString().replaceAll("\\(\\)", "").replaceAll("survive","");
    }


    public static void preorderTraversal(TreeNode treeNode, StringBuilder stringBuilder,boolean rightNode) {
        stringBuilder.append("(");
        if (treeNode == null) {
            if (rightNode) {
                stringBuilder.append(")");
            }else {
                stringBuilder.append("survive)");
            }

            return;
        }
        stringBuilder.append(treeNode.val);
        if (treeNode.left != null || treeNode.right != null) {
            preorderTraversal(treeNode.left, stringBuilder,false);
            preorderTraversal(treeNode.right, stringBuilder,true);
        }
        stringBuilder.append(")");
    }
}
