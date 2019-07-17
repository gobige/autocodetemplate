package com.example.autocodetemplate.ohter.practice.leetcode;

public class L69SquareRootOfX {
//    public int mySqrt(int x) {
//        boolean leftQuery;
//        if (x < 5) {
//            leftQuery = false;
//        }else {
//            leftQuery = true;
//        }
//
//        int cal = x / 2;
//
//        if (cal * cal == x) {
//            return cal;
//        } else (cal * cal == x){}
//
//        Math.sqrt(x);
//    }

    public static int binarySearch(int[] soredArrs, int start, int end, int searchNum) {
        if (start <= end) {
            int middleNumIndex = (start + end) / 2;
            if (soredArrs[middleNumIndex] == searchNum) {
                return middleNumIndex;
            } else if (soredArrs[middleNumIndex] > searchNum) {
                return binarySearch(soredArrs, start, middleNumIndex-1, searchNum);
            } else {
                return binarySearch(soredArrs, middleNumIndex+1, end, searchNum);
            }
        } else {
            return -1;
        }
    }
}
