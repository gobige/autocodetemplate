package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 思路：二分查找目标 如果又找到目标，则对其坐标左右扩展查询，开始位置和结束位置
 */
public class FindTheFirstAndLastPositionOfAnElementInASortedArray34 {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println(searchRange(nums, 1));
    }


    public static int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, 0, nums.length - 1, target);

        int startIndex = -1;
        int endIndex = -1;
        if (index == -1) {
            return new int[]{-1, -1};
        }

        startIndex = index;
        endIndex = index;


        while (startIndex - 1 >= 0) {
            if (nums[startIndex - 1] == target) {
                startIndex--;
            }else {
                break;
            }
        }

        while (endIndex + 1 < nums.length) {
            if (nums[endIndex + 1] == target) {
                endIndex++;
            }else {
                break;
            }
        }


        int[] retNums = new int[2];
        retNums[0] = startIndex;
        retNums[1] = endIndex;

        return retNums;
    }

    /**
     * 二分查找递归实现
     *
     * @param soredArrs 排序后数组
     * @param start     开始查找index
     * @param end       结束查找index
     * @param searchNum 查找number
     * @return 查找num数组下标
     */
    public static int binarySearch(int[] soredArrs, int start, int end, int searchNum) {
        if (start <= end) {
            int middleNumIndex = (start + end) / 2;
            if (soredArrs[middleNumIndex] == searchNum) {
                return middleNumIndex;
            } else if (soredArrs[middleNumIndex] > searchNum) {
                return binarySearch(soredArrs, start, middleNumIndex-1, searchNum);
            } else {
                return binarySearch(soredArrs, middleNumIndex+1, end, searchNum);
            }
        } else {
            return -1;
        }
    }
}
