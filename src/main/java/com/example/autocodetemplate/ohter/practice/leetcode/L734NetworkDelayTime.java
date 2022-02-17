package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 734. 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 * 思路1 根据列表连接各节点
 */
public class L734NetworkDelayTime {
//    public static void main(String[] args) {
//
//    }
//
//    public int networkDelayTime(int[][] times, int n, int k) {
//        if (times == null || times.length == 0) {
//            return -1;
//        }
//
//        List<Integer> nodes = new LinkedList<>();
//        for (int i = 0; i < times.length; i++) {
//            int[] route = times[i];
//            if (nodes.contains(route[0])) {
//            }
//            nodes.add(route[0]);
//            nodes.add(route[1]);
//        }
//
//        return 0;
//    }
//
//    class Node {
//        public int value;
//        public Node left;
//        public Node right;
//
//        Node(int value) {
//            this.value = value;
//        }
//
//        public void push(int value) {
//            if (this.value > value) {
//                if (null == this.left) {
//                    this.left = Node(value);
//                } else {
//                    this.left.push(value);
//                }
//            } else if (this.value < value) {
//                if (null == this.right) {
//                    this.right = new com.example.autocodetemplate.ohter.practice.dataStructrue.Node(value);
//                } else {
//                    this.right.push(value);
//                }
//            }
//        }
//
//        public boolean find(int value) {
//            if (this.value == value) {
//                return true;
//            } else if (this.value > value) {
//                if (null == this.left) {
//                    return false;
//                } else {
//                    this.left.find(value);
//                }
//            } else if (this.value < value) {
//                if (null == this.right) {
//                    return false;
//                } else {
//                    this.right.find(value);
//                }
//            }
//            return false;
//        }
//
//        /**
//         * find left num
//         */
//        public void preNode() {
//            if (null == this.left) {
//                System.out.println(this.value);
//            }
//        }
//    }
}
