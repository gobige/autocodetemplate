package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 思路1：使用双指针分别从最左最右开始遍历，如两指针值大于则右指针移动，小于则左指针移动
 * 时间复杂度：n
 * 空间复杂度：1
 */
public class L167TheSumOfTwoNumbersIiInputOrderedArray {
    public int[] twoSum(int[] numbers, int target) {

        int leftIndex = 0;
        int rightIndex = numbers.length - 1;

        while (leftIndex < rightIndex) {
            if (numbers[leftIndex] + numbers[rightIndex] == target) {
                return new int[]{leftIndex + 1, rightIndex + 1};
            }else if (numbers[leftIndex] + numbers[rightIndex] < target) {
                leftIndex++;
            }else if (numbers[leftIndex] + numbers[rightIndex] > target) {
                rightIndex--;
            }
        }

        return null;
    }

}
