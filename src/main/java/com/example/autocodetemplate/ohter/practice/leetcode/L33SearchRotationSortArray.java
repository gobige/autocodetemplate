package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SearchArithmetic;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 思路 先找到旋转点；因为是排序，可以借助二分查找，
 */
public class L33SearchRotationSortArray {

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,5,6,1,2};

        System.out.println(search(arr, 2));
    }

    public static int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            if (target == nums[0]) {
                return 0;
            }else {
                return -1;
            }
        }

        int index = -1;
        // 数组有序
        if (nums[0] <= nums[length-1]) {
            index = SearchArithmetic.binarySearch(nums, 0, nums.length-1, target);
        }else {
            int rotationPoint = rotationPointSearch(nums, 0, nums.length-1);

            // 旋转后左数组二分查找
            if (target > nums[length - 1]) {
                index = SearchArithmetic.binarySearch(nums, 0, rotationPoint, target);
            } else {
                // 旋转后右数组二分查找
                index = SearchArithmetic.binarySearch(nums, rotationPoint + 1, length - 1, target);
            }
        }

        return index;
    }

    /**
     * 查询旋转节点下标
     * @param soredArrs
     * @param start
     * @param end-
     * @return
     */
    public static int rotationPointSearch(int[] soredArrs, int start, int end) {
        if (start <= end) {
            int middleNumIndex = (start + end) / 2;

            // 如果中间数既大于最左数，又大于最右边数
            if (soredArrs[middleNumIndex] >= soredArrs[soredArrs.length - 1] && soredArrs[middleNumIndex] >= soredArrs[0]) {
                if (middleNumIndex != soredArrs.length - 1 && soredArrs[middleNumIndex + 1] <= soredArrs[middleNumIndex]) {
                    return middleNumIndex;
                }else {
                    return rotationPointSearch(soredArrs, start, middleNumIndex );
                }

                // 如果中间数既小于最左数，又小于最右边数
            } else if (soredArrs[middleNumIndex] <= soredArrs[soredArrs.length - 1] && soredArrs[middleNumIndex] <= soredArrs[0]) {
                if (middleNumIndex != 0 && soredArrs[middleNumIndex - 1] >=  soredArrs[middleNumIndex]) {
                    return middleNumIndex;
                }else {
                    return rotationPointSearch(soredArrs, start, middleNumIndex );
                }
            } else {
                return -1;
            }

        } else {
            return -1;
        }
    }


}
