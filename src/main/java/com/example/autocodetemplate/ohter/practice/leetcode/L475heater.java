package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 *
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 * 思路1：计算所有供暖器之间的最大距离+最左最右供暖器离边界的距离，取最大值
 * 思路2：计算每一个房屋距离最近的供暖器
 *
 */
public class L475heater {
    public static void main(String[] args) {
        findRadius(new int[]{1, 5}, new int[]{10});
    }

    public static int findRadius(int[] houses, int[] heaters) {
        int maxDistance =0;
        for (int house : houses) {
            int minDistance = Math.abs(heaters[0] - house) ;
            for (int heater : heaters) {
                if (Math.abs(heater - house) < minDistance) {
                    minDistance = Math.abs(heater - house);
                }
            }
            if (maxDistance < minDistance) {
                maxDistance = minDistance;
            }
        }

        return maxDistance;
    }

    public static int findRadius2(int[] houses, int[] heaters) {

        int temp;
        for (int i = 0; i < houses.length; i++) {
            for (int j = 0; j < houses.length - i - 1; j++) {
                if (houses[j] > houses[j + 1]) {
                    temp = houses[j];
                    houses[j] = houses[j + 1];
                    houses[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < heaters.length; i++) {
            for (int j = 0; j < heaters.length - i - 1; j++) {
                if (heaters[j] > heaters[j + 1]) {
                    temp = heaters[j];
                    heaters[j] = heaters[j + 1];
                    heaters[j + 1] = temp;
                }
            }
        }

        if (heaters.length == 1) {
            return Math.max(Math.abs(houses[0] - heaters[0]), Math.abs(houses[houses.length - 1] - heaters[0]));
        }

        int maxDistance = heaters[0] - houses[0];

        for (int i = 0; i < heaters.length - 1; i++) {
            if (heaters[i + 1] > houses[houses.length - 1]) {

                // 边界之外暖器判断
                if (heaters[i + 1] - houses[houses.length - 1] > houses[houses.length - 1] - heaters[i]) {
                    maxDistance = houses[houses.length - 1] - heaters[i];
                } else {
                    maxDistance = (heaters[i + 1] - heaters[i]) / 2;
                }
                break;
            }
            if (heaters[i + 1] - heaters[i] > maxDistance) {
                maxDistance = (heaters[i + 1] - heaters[i]) / 2;
            }
        }

        return maxDistance;
    }
}
