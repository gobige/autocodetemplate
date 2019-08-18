package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明： 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 *
 * 思路1  准备
 */
public class L136NumberThatOnlyAppearsOnce {

    public int singleNumber(int[] nums) {

        Set numSet = new HashSet();

        for (int num : nums) {
            if (numSet.contains(num)) {
                numSet.remove(num);
            }else {
                numSet.add(num);
            }
        }

        Iterator ite = numSet.iterator();

        return (int)ite.next();
    }

}
