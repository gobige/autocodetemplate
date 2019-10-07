package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 575. 分糖果
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。
 * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 */
public class L575Candy {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();

        for (int candy : candies) {
            set.add(candy);
        }

        int kindNum = set.size();

        if (kindNum < candies.length / 2) {
            return kindNum;
        }else {
           return candies.length / 2;
        }
    }
}
