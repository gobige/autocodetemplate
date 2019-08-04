package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 * 思路：递归遍历，一层一层遍历，每一层数据是否对称
 * 时间复杂度N
 * 空间复杂度1
 *
 * 结果 超时??
 */
public class L101SymmetricBinaryTree {

    public static void main(String[] args) {
        L101SymmetricBinaryTree ds = new L101SymmetricBinaryTree();

        TreeNode node = new TreeNode(6);
        TreeNode node2 = new TreeNode(82);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node.left = node2;
        node.right = node3;

        node2.left = node4;
        node2.right = node5;


        node3.left = node6;
        node3.right = node7;

        ds.isSymmetric(node);
    }

    public boolean isSymmetric(TreeNode root) {
        return compare(root, root);
    }

    public boolean compare(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return t1.val == t2.val && compare(t1.left, t2.right) && compare(t1.right, t2.left);

    }

    public boolean isSymmetric1(TreeNode root) {
        List nodes = new LinkedList<>();
        nodes.add(root);

        while (true) {
            nodes = getSonTreeNode(nodes);
            if (isAllNull(nodes)) {
                return true;
            }

            boolean pass = isYymmetry(nodes);
            if (!pass) {
                return false;
            }
        }

    }

    /**
     * 根据父节点获取所有子节点
     * @param parentNode
     * @return
     */
    public List<TreeNode> getSonTreeNode(List<TreeNode> parentNode) {
        List<TreeNode> sons = new LinkedList<>();
        if (parentNode == null || parentNode.size() == 0) {
            return null;
        }

        for (TreeNode treeNode : parentNode) {
            if (treeNode == null) {
                sons.add(null);
                sons.add(null);
            }else {
                sons.add(treeNode.left);
                sons.add(treeNode.right);
            }
        }

        return sons;
    }

    /**
     * 检查是否所有元素都为null
     *
     * @param nodes
     * @return
     */
    public boolean isAllNull(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (node != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检查该层元素是否对称
     * @param treeNodes
     * @return
     */
    public boolean isYymmetry(List<TreeNode> treeNodes) {
        if (treeNodes == null || treeNodes.size() == 0) {
            return true;
        }

        Object[] nodesodeArrays = treeNodes.toArray();

        int size = nodesodeArrays.length;

        int left = 0;
        int right = size - 1;
        while (left < right) {
            if (nodesodeArrays[left] == null && nodesodeArrays[right] == null) {

            } else if (nodesodeArrays[left] != null && nodesodeArrays[right] != null) {
                if (((TreeNode)nodesodeArrays[left]).val != ((TreeNode)nodesodeArrays[right]).val) {
                    return false;
                }
            }else {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
