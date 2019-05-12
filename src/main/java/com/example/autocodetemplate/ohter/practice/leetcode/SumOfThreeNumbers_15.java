package com.example.autocodetemplate.ohter.practice.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 15. 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 思路 1任选两数作为基准 获取另外是否存在一个数字  三数之和为0
 * 空间换时间方式
 *
 * 2 任选一个数作为基准 双指针方式实现
 *
 */
public class SumOfThreeNumbers_15 {
    /**
     * 双指针
     * 时间复杂度 N的阶乘
     * 空间复杂度 最大3N 最小N
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {

        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        List<List<Integer>> lists = new LinkedList<>();

        for (int baseNum = 0; baseNum < nums.length; baseNum++) {
            int left = baseNum + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[baseNum];
                if (sum == 0) {
                    // 排除重复的组合
                    while (nums[right] == nums[right - 1] && right - 1 > left)  right--;
                    while (nums[left] == nums[left + 1] && left + 1 < right) left++;
                    List<Integer> set = new ArrayList<Integer>(3);
                    set.addAll(Arrays.asList(nums[baseNum], nums[left], nums[right]));
                    lists.add(set);
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                }else {
                    left++;
                }
            }
        }

        List<List<Integer>> notRepeatLists = noRepeat(lists);

        return notRepeatLists;
    }


    /**
     * 时间复杂度最小为n的平方 最大为(1/2n)的3次方
     * 空间复杂度为 视最大数字大小而定
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }


        // 排序 分为负数组和正数组
        BitSet negativeBitSet = new BitSet();
        BitSet positiveBitSet = new BitSet();

        List<List<Integer>> lists = new ArrayList<>();
        int zeroNum = 0;
        for (int num : nums) {
            if (num <= 0) {
                if (num == 0) {
                    zeroNum++;
                }
                negativeBitSet.set(-num);
            }else {
                positiveBitSet.set(num);
            }
        }

        // 特殊情况 000
        if (zeroNum > 2) {
            List<Integer> set = new ArrayList<Integer>(3);
            set.add(0);
            set.add(0);
            set.add(0);

            lists.add(set);
        }

        int maxLength = nums.length;
        // 排序
        Arrays.sort(nums);

        int index1 = 0;
        // 两负数基准
         for (int out = 0; ; out++) {
            if (out == maxLength || nums[out] > 0) {
                index1 = out;
                break;
            }
            for (int inner = out + 1; ; inner++) {
                if (inner == maxLength || nums[inner] > 0) {
                    break;
                }
                Integer negativeResult = nums[inner] + nums[out];
                if (positiveBitSet.get(-negativeResult)) {
                    List<Integer> set = new ArrayList<Integer>(3);
                    set.add(nums[out]);
                    set.add(nums[inner]);
                    set.add(-negativeResult);

                    lists.add(set);
                }
            }
        }

        // 两正数基准
        for (int out = index1; ; out++) {
            if (out == maxLength) {
                 break;
            }
            for (int inner = out + 1; ; inner++) {
                if (inner == maxLength) {
                    break;
                }
                Integer positiveResult = nums[inner] + nums[out];
                if (negativeBitSet.get(positiveResult)) {
                    List<Integer> set = new ArrayList<Integer>(3);
                    set.add(-positiveResult);
                    set.add(nums[out]);
                    set.add(nums[inner]);

                    lists.add(set);
                }
            }
        }

        // 然后去重
        List<List<Integer>> notRepeatLists = noRepeat(lists);

        return notRepeatLists;
    }

    public static List<List<Integer>> noRepeat(List<List<Integer>> lists) {
        HashSet<String> notRepeatSign = new HashSet<>(10);
        List<List<Integer>> notRepeatLists = new LinkedList<>();
        if (lists.size() > 0) {
            for (List<Integer> list : lists) {
                StringBuilder concatNum = new StringBuilder();
                for (Integer ele : list) {
                    concatNum.append(ele);
                }
                if (!notRepeatSign.contains(concatNum.toString())) {
                    notRepeatSign.add(concatNum.toString());
                    notRepeatLists.add(list);
                }
            }
        }

        return notRepeatLists;
    }

    public static void main(String[] args) {
        System.out.println(threeSum2(new int[]{-2,0,1,1,2}));

    }
}
