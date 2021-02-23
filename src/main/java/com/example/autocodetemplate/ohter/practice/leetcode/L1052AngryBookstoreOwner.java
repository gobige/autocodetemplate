package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 *
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 *
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * 思路1，滑窗 计算最大值
 *  生气时对应的影响人数
 */
public class L1052AngryBookstoreOwner {
    public static void main(String[] args) {

        int[] customers = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
           int[] grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};

        maxSatisfied(customers, grumpy, 3);
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {

        int maxInfPer = 0;
        int maxPerSatiInd = 0;
        int index = 0;
        while (index + X <=customers.length) {
            int infPer = 0;
            for (int i = index; i < index + X; i++) {
                if (grumpy[i] == 1) {
                    infPer += customers[i];
                }
            }
            if (maxInfPer < infPer) {
                maxInfPer = infPer;
                maxPerSatiInd = index;
            }

            index++;
        }

        int num = 0;
        for (int i = 0; i < customers.length; i++) {
            if (i < maxPerSatiInd && grumpy[i] == 0) {
                num += customers[i];
            } else if (i >= maxPerSatiInd && i < maxPerSatiInd + X) {
                num += customers[i];
            } else if (i >= maxPerSatiInd + X && grumpy[i] == 0) {
                num += customers[i];
            }
        }

        return num;
    }
}
