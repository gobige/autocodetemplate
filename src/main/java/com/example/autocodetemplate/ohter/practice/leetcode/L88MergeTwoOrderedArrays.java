package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 思路：使用两个指针遍历两个数组， 循环替换num1和num2数组，最终num1比num2小，最好直接把num2加入到num1末尾
 * 时间复杂度 m*n 空间复杂度 1
 *
 * 思路2：使用额外数组，使用双指针，遍历NUm1，num2，小的放入额外数组
 * 时间复杂度m+n，空间复杂度m
 */
public class L88MergeTwoOrderedArrays {
    public static void main(String[] args) {

        merge(new int[]{-1,0,1,1,0,0,0,0,0}, 4, new int[]{-1,0,2,2,3}, 5);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) {
            return;
        }
        int index1 = 0;
        while (index1 < m) {
            if (nums1[index1] <= nums2[0]) {
                index1++;
            } else if (nums1[index1] > nums2[0]) {
                // 互换num1和num2数值
                nums1[index1] = nums1[index1] + nums2[0];
                nums2[0] = nums1[index1] - nums2[0];
                nums1[index1] = nums1[index1] - nums2[0];

                index1++;

                int index2 = 0;
                while (index2 +1 < n && nums2[index2] > nums2[index2 + 1]  ) {
                    nums2[index2] = nums2[index2] + nums2[index2+1];
                    nums2[index2 + 1] = nums2[index2] - nums2[index2 + 1];
                    nums2[index2] = nums2[index2] - nums2[index2+1];
                    index2++;
                }
            }
        }

        if (index1 == m) {
            int index2 = 0;
            while (index2 < n) {
                nums1[index1] = nums2[index2];
                index2++;
                index1++;
            }
        }
    }
}
