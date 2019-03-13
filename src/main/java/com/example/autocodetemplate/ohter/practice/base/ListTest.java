package com.example.autocodetemplate.ohter.practice.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        Collection<Integer> catIdListArray = new ArrayList<>();
        catIdListArray.add(122);
        Collection<Integer> lists = Arrays.asList(new Integer[]{1, 2, 34, 5, 6, 71, 11, 42});
        ((ArrayList<Integer>) catIdListArray).addAll(lists);

        Collection<Integer> catIdListArray2 = new ArrayList<>();
        catIdListArray2.add(122);
        Collection<Integer> lists2 = null;
        ((ArrayList<Integer>) catIdListArray2).addAll(lists2);

        System.out.println(catIdListArray);
    }

    Collection<Integer> catIdListArray;
}
