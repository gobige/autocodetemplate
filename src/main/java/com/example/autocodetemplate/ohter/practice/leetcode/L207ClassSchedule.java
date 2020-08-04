package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 198. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 思路1：把他想象成一颗树，节点为课程，父节点为先修课程，我们的目的就是从叶子节点遍历到头节点，总共节点数 == 总课程数则 表示能完成所有课程学习
 * TODO
 **/
public class L207ClassSchedule {

    public static void main(String[] args) {

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        // 放入map结构
        for (int[] prerequisite : prerequisites) {
            map1.put(prerequisite[0], prerequisite[1]);
        }

        while (true) {
            Set<Map.Entry<Integer, Integer>> entries = map1.entrySet();
            if (entries.size() == 0) {
                return true;
            }

            // 随机获取一个值
            Integer key = entries.iterator().next().getKey();
            recurseKeyValue(key, map1, map2, true);

            // 是否没有删除 代表父节点不存在
            if (map1.containsKey(key)) {
                return false;
            }
        }

    }

    public void recurseKeyValue(Integer key, HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2,boolean first) {

        if (map1.containsKey(key)) {

            // 转移
            Integer parKey = map1.get(key);
            map1.remove(key);
            map2.put(key, parKey);

            // 递归
            recurseKeyValue(parKey, map1, map2,false);
         }

        if (map2.containsKey(key) && first) {

            // 转移
            Integer parKey = map1.get(key);
            map1.remove(key);
            map2.put(key, parKey);
        }
    }
}
