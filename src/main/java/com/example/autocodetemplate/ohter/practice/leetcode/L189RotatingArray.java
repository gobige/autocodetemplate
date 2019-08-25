package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 思路1：创建一个同样大小数组，分别移位，拷贝数组中元素到新数组
 * 时间复杂度n 空间复杂度n
 * 思路2：创建一个列表，放入数组，切断指定节点到末尾节点连接到头部，再放回数组中
 * 时间复杂度2n，空间复杂度n
 *
 * 思路3：通过观察，先反转全部元素，再反转前k个元素，再反转剩下元素
 * 时间复杂度 3n
 * 空间复杂度1
 */
public class L189RotatingArray {

    public static void main(String[] args) {
        rotate(new int[]{1,2,3,4,5,6,7}, 3);


    }

    public static void rotate(int[] nums, int k) {

        k = k % nums.length;

        rotate(nums, 0, nums.length - 1);

        rotate(nums, 0, k-1);

        rotate(nums, k, nums.length - 1);

        System.out.println(nums);
    }

    private static void rotate(int[] nums,int left,int right) {

        while (left < right) {
            nums[left] = nums[left] + nums[right];
            nums[right] = nums[left] - nums[right];
            nums[left] = nums[left] - nums[right];
            left++;
            right--;
        }
    }
}