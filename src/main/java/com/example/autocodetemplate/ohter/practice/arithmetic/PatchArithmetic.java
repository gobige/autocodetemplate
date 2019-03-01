package com.example.autocodetemplate.ohter.practice.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class PatchArithmetic {
    public static void main(String[] args) {
        List<Integer> userIds = new ArrayList<>();
        userIds.add(1);
        userIds.add(2);
        userIds.add(3);
        userIds.add(4);
        userIds.add(56);
        userIds.add(123);
        userIds.add(16);

        Integer startIndex = 0;
        Integer endIndex = 0;
        while (endIndex < userIds.size()) {
            startIndex = endIndex;
            if (endIndex + 3 < userIds.size()) {
                endIndex += 3;
            } else {
                endIndex = userIds.size();
            }

            List<Integer> subList = userIds.subList(startIndex, endIndex);
            System.out.println("show patch list" + subList);
        }
    }
}
