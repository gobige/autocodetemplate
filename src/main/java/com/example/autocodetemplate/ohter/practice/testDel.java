package com.example.autocodetemplate.ohter.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class testDel {
    public static void main(String[] args) {
      List<Integer> s = new ArrayList<>();
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.add(5);
        s.add(6);
        Optional sum = s.parallelStream().reduce((a,b) -> a + b);

        if (sum.isPresent()) {
            System.out.println("list sum is " + sum.get());
        }

        Integer sum2 = s.parallelStream().reduce(0, (a,b) -> a+b);
        System.out.println("list sum is " + sum2);

        Optional sum3 = s.parallelStream().unordered().reduce((a, b) -> a * b);
        System.out.println("list sum is " + sum3.get());

         Integer sum4 = s.parallelStream().reduce(1, (a,b) -> a*b);
        System.out.println("list sum is " + sum4);


    }

}
