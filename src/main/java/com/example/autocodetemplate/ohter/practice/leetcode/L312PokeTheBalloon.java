package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 思路1：通过观察，除最两边数字外，中间数字从小开始戳，得到值最大 TODO
 */
public class L312PokeTheBalloon {
    public static void main(String[] args) {
        maxCoins(new int[]{9,76,64,21,97,60});
    }
    public static int maxCoins(int[] nums) {
        int length = nums.length;

        if (length < 1) {
            return 0;
        } else if (length < 2) {
            return nums[0];
        } else if (length < 3) {
            return nums[0] * nums[1] + Math.max(nums[0], nums[1]);
        }
        Node31 first = new Node31(nums[0]);
        Node31 end = first;
        for (int i = 1; i < length; i++) {
            Node31 node = new Node31(nums[i]);
            end.next = node;
            node.pre = end;
            end = node;
        }

        int count = 0;

        for (int i = 0; i < length - 2; i++) {
            Node31 cur2 = first.next;
            Node31 lowNode = first.next;
            while (cur2 != null&& cur2 != end) {
                if (cur2.val < lowNode.val) {
                    lowNode = cur2;
                }
                cur2 = cur2.next;
            }

            count += lowNode.val * lowNode.pre.val * lowNode.next.val;
            lowNode.pre.next = lowNode.next;
            lowNode.next.pre = lowNode.pre;
        }

        return count + first.val * end.val + Math.max(first.val, end.val);
    }
}

class Node31 {
    Node31(){}

    Node31(int x) {
        val = x;
    }
    int val;
    Node31 next;
    Node31 pre;
}