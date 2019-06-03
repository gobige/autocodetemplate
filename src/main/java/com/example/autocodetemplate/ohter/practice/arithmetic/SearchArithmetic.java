package com.example.autocodetemplate.ohter.practice.arithmetic;

public class SearchArithmetic {

    /**
     * 二分查找递归实现
     *
     * @param soredArrs 排序后数组
     * @param start     开始查找index
     * @param end       结束查找index
     * @param searchNum 查找number
     * @return 查找num数组下标
     */

    /**
     * 二分查找递归实现
     *
     * @param soredArrs 排序后数组
     * @param start     开始查找index
     * @param end       结束查找index
     * @param searchNum 查找number
     * @return 查找num数组下标
     */
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

    /**
     * kmp 匹配字符串
     *
     * @param matchStr  待匹配字符串
     * @param searchStr 搜索字符串
     * @return 所在位置index
     */
    public static int searchMatchStrByKmp(String matchStr, String searchStr) {
        char[] searchCharArr = searchStr.toCharArray();
        char[] matchCharArr = matchStr.toCharArray();

        boolean flag = false;
        int alreadyMatchNum = 0;
        for (int i = 0; i < searchCharArr.length; i++) {
            alreadyMatchNum = 0;
            for (int j = 0; j < matchCharArr.length; j++) {
                if (i + j == searchCharArr.length) {
                    flag = false;
                    break;
                }
                if (matchCharArr[j] == searchCharArr[i + j]) {
                    alreadyMatchNum++;
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//		int mid = binarySearch(arrays, 0, arrays.length - 1, 23);

        System.out.println(searchMatchStrByKmp("abcd", "acdefgabcd"));
    }

}
