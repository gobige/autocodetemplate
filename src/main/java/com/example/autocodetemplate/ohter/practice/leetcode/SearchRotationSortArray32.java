package com.example.autocodetemplate.ohter.practice.leetcode;

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
public class SearchRotationSortArray32 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};

        search(arr, 4);
    }

    public static int search(int[] nums, int target) {
        int length = nums.length;
        int index = -1;
        // 数组有序
        if (nums[0] < nums[length-1]) {
            index = binarySearch(nums, 0, nums.length, target);
        }else {
            int rotationPoint = rotationPointSearch(nums, 0, nums.length-1);

            // 旋转后左数组二分查找
            if (target > nums[length - 1]) {
                index = binarySearch(nums, 0, rotationPoint, target);
            } else {
                // 旋转后右数组二分查找
                index = binarySearch(nums, rotationPoint + 1, length - 1, target);
            }
        }

        return index;
    }

    /**
     * 查询旋转节点下标
     * @param soredArrs
     * @param start
     * @param end
     * @return
     */
    public static int rotationPointSearch(int[] soredArrs, int start, int end) {
        if (start < end) {
            int middleNumIndex = (start + end) / 2;
            if (soredArrs[middleNumIndex] > soredArrs[soredArrs.length - 1] && soredArrs[middleNumIndex] > soredArrs[0]) {
                if (middleNumIndex != soredArrs.length - 1 && soredArrs[middleNumIndex + 1] < soredArrs[middleNumIndex]) {
                    return middleNumIndex;
                }else {
                    return rotationPointSearch(soredArrs, start, middleNumIndex );
                }
            } else if (soredArrs[middleNumIndex] < soredArrs[soredArrs.length - 1] && soredArrs[middleNumIndex] < soredArrs[0]) {
                if (middleNumIndex != 0 && soredArrs[middleNumIndex - 1] > soredArrs[middleNumIndex]) {
                    return middleNumIndex;
                }else {
                    return rotationPointSearch(soredArrs, start, middleNumIndex );
                }
            }else {
                return -1;
            }

        } else {
            return -1;
        }
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
        if (start < end) {
            int middleNumIndex = (start + end) / 2 + 1;
            if (soredArrs[middleNumIndex] == searchNum) {
                return middleNumIndex;
            } else if (soredArrs[middleNumIndex] > searchNum) {
                return binarySearch(soredArrs, start, middleNumIndex, searchNum);
            } else {
                return binarySearch(soredArrs, middleNumIndex, end, searchNum);
            }
        } else {
            return -1;
        }
    }

}
