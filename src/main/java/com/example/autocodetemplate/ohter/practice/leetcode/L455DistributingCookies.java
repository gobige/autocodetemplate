package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.persistence.Index;
import java.util.Arrays;

/**
 *
 * 455. 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，
 * 这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，
 * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 注意：
 * 你可以假设胃口值为正。
 * 一个小朋友最多只能拥有一块饼干。
 *
 * 思路1 先使用当前sj最小的符符合sj>gi的最小胃口小的朋友，
 */
public class L455DistributingCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int gIndex = 0;
        int sIndex = 0;

        while (gIndex < g.length && sIndex < s.length) {
            if (g[gIndex] > s[sIndex]) {
                sIndex++;
                continue;
            }

            gIndex++;
            sIndex++;
        }

        return gIndex;
    }
}
