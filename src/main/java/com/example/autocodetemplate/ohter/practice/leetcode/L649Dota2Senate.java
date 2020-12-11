package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 649 DOTA2 参议院
 *
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）
 *
 * 每一位参议员都可以行使两项权利中的一项：1禁止一名参议员的权利 2宣布胜利
 * 基于轮为过程的投票进行
 * 输出最终  应该是 Radiant 或 Dire 获胜
 *
 *         // 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，这个策略应该是 争取本方的发言权的优先
 */
public class L649Dota2Senate {

    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RD")); ;

    }
    public static String predictPartyVictory(String senate) {
        char[] senateC = senate.toCharArray();

        // 用两个值表示 D和R 还有权发言的数量
        int dVoiceCount = 0;
        int rVoiceCount = 0;

        // 用一个链表表示一次轮为的次序

        for (char c : senateC) {
            if (c == 'D') {
                dVoiceCount++;
            } else if (c == 'R') {
                rVoiceCount++;
            }
        }

        while (true) {
            int length = senateC.length;
            // 一轮选举
            for (int i = 0; i < length; i++) {
                // 梦魇阵营发言
                if (senateC[i] == 'D') {
                    int j = i;
                    while (true) {

                        // 遍历 如果下一个参议员为天辉阵营 则ban掉发言权
                        if (senateC[j] == 'R') {
                            senateC[j] = 0;
                            rVoiceCount--;
                            break;
                        }
                        j++;

                        // 遍历到尾
                        if (j == length) {
                            j = 0;
                        }

                        if ((j == i)) {
                            break;
                        }
                    };

                    // 判断是否全是本方参议员
                    if (rVoiceCount < 1) {
                        return "Dire";
                    }
                }
                // 天辉阵营发言
                if (senateC[i] == 'R') {
                    int j = i;
                    while (true){
                        // 遍历 如果下一个参议员为梦魇阵营 则ban掉发言权
                        if (senateC[j] == 'D') {
                            senateC[j] = 0;
                            dVoiceCount--;
                            break;
                        }
                        j++;

                        // 遍历到尾
                        if (j == length) {
                            j = 0;
                        }

                        if ((j == i)) {
                            break;
                        }
                    }

                    // 判断是否全是本方参议员
                    if (dVoiceCount < 1) {
                        return "Radiant";
                    }
                }
            }
        }
    }
}
