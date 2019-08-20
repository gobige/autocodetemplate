package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 思路1：既然 总有众数，那么遍历数组，进行统计，使用hashmap存储，如果某数值计数过半则返回
 *
 * 时间复杂度 n
 * 空间复杂度 n/2
 */
public class L169SeekingTheMajority {

    public static void main(String[] args) {

    }

    public int majorityElement(int[] nums) {
        int halfLength = nums.length / 2;
        Map<Integer, Integer> seekMap = new HashMap<>(halfLength + 1);

        int val = 0;
        for (int num : nums) {
            if (seekMap.containsKey(num)) {
                val = seekMap.get(num);
                if (val == halfLength) {
                    return num;
                }
                seekMap.put(num, val + 1);
            }else {
                seekMap.put(num, 1);
            }
        }

        return nums[0];
    }

}
