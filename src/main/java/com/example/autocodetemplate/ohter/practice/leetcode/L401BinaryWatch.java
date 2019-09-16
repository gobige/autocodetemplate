package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 *
 * 思路1，使用Integer.bitcount方法 计算10机制数字对应的二进制中1的数量
 *
 * 思路2，回溯法  n：亮灯数量  stepstep：递归层数  resultresult：单次结果  result_allresult
 * 递归结构   r(n)=r(n-1) + w
 * w 代表从 numsnums 中选出一个数字。
 * ​
 */
public class L401BinaryWatch {
    public static void main(String[] args) {
        IntStream.range(0, 5)
                .parallel()
                .sorted()
                .forEach(System.out::print);
    }

    public List<String> readBinaryWatch(int num) {
        List<String> str = new LinkedList<>();

        return str;
    }

    public List<String> readBinaryWatch1(int num) {
        List<String> str = new LinkedList<>();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    if (j < 10) {
                        str.add(i + ":" + "0" + j);
                    } else {
                        str.add(i + ":" + j);
                    }
                }
            }
        }

        return str;
    }

}