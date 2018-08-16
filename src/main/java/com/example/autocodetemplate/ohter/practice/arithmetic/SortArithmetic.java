package com.example.autocodetemplate.ohter.practice.arithmetic;

/**
 * 常用排序算法
 */
public class SortArithmetic {

    public static void main(String[] args) {
        int [] waitSortArray = {2,1,44,1,2,34,11,24,99,565,7,89,22,3,5,23,145,65,47,4,65,7,81,23,4,4,34,5,47,65,1,23};

//      waitSortArray = bubbleSort(waitSortArray);
//      quickSort(waitSortArray,0,waitSortArray.length - 1);
//      int [] temp = new int[waitSortArray.length];
//      mergeSort(waitSortArray,0,waitSortArray.length - 1,temp);
        waitSortArray = cardinalitySort(waitSortArray);


        for (int i : waitSortArray) {
            System.out.print(i+" ");
        }
    }

    /**
     * 基数排序
     * @param arrays
     * @return
     */
    public static int[] cardinalitySort(int[] arrays) {
        int digit = findMaxNumberOfDigitsFromArray(arrays);

        ;
        return sortCore(arrays, 0, digit);
    }

    private static int[] sortCore(int[] array, int digit, int maxLength) {
        if (digit >= maxLength) {
            return array;
        }

        final int radix = 10; // 基数
        int arrayLength = array.length;
        int[] count = new int[radix];
        int[] bucket = new int[arrayLength];

        // 统计将数组中的数字分配到桶中后，各个桶中的数字个数
        for (int i = 0; i < arrayLength; i++) {
            count[getDigit(array[i], digit)]++;
        }

        // 将各个桶中的数字个数，转化成各个桶中最后一个数字的下标索引
        for (int i = 1; i < radix; i++) {
            count[i] = count[i] + count[i - 1];
        }

        // 将原数组中的数字分配给辅助数组 bucket
        for (int i = arrayLength - 1; i >= 0; i--) {
            int number = array[i];
            int d = getDigit(number, digit);
            bucket[count[d] - 1] = number;
            count[d]--;
        }

        return sortCore(bucket, digit + 1, maxLength);
    }

    /*
     * 获取 x 这个数的 d 位数上的数字
     * 比如获取 123 的 0 位数,结果返回 3
     *
     * @param x
     * @param d
     * @return
     */
    private static int getDigit(int x, int d) {
        int a[] = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };
        return ((x / a[d]) % 10);
    }

    /**
     * 找出待排序数组中数值最大位数
     * @param arrays
     * @return
     */
    private static int findMaxNumberOfDigitsFromArray(int[] arrays) {
         if (arrays != null && arrays.length > 0) {
             int max = arrays[0];
             for (int i : arrays) {
                 if (i > max) {
                     max = i;
                 }
             }
            return getNumberOfDigits(max);
         } else {
             return 0;
         }
     }

    /**
     * 获取整数位数
     * @param num
     * @return
     */
    private static int getNumberOfDigits(int num) {
         return String.valueOf(num).length();
     }

    /**
     * 计数排序
     * @param arrays
     */
    public static int[] countSort(int[] arrays) {
        // 得到最大值
        int max = arrays[0];
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
        }

        // 初始化数组
        int[] showCountArrays = new int[max+1];

        // 计算数组每个值出现次数
        for (int i : arrays) {
            showCountArrays[i]++;
        }

        // 计算数组每个值比他小的数值个数
        int[] lessNumArrays = new int[arrays.length];
        for (int i : arrays) {
            int num = 0;
            for (int j : arrays) {
                if (i > j) {
                    num++;
                }
            }
            lessNumArrays[num] = i;
        }

        int[] sortResultArrays = new int[arrays.length];

        int j = 0;
        for (int i : lessNumArrays) {
            int count = showCountArrays[i];
            while (count > 0) {
                sortResultArrays[j] = i;
                j++;
                count--;
            }

        }

        return sortResultArrays;
    }

    /**
     * 归并排序
     * @param arrays 待排序数组
     * @param left 左起始点
     * @param right 右结束点
     * @param temp 临时数组
     */
    public static void mergeSort(int[] arrays,int left,int right,int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 排序左边数组
            mergeSort(arrays,left,mid,temp);
            // 排序右边数组
            mergeSort(arrays,mid + 1,right,temp);
            // 合并左右数组
            merge(arrays,left,right,mid,temp);
        }
    }

    /**
     * 合并左右两边有序数组
     * @param arrays 待排序数组
     * @param left merge数组起始位置
     * @param right merge数组结束位置
     * @param mid 左右数组分割位置
     * @param temp 临时数组
     */
    private static void merge(int arrays[], int left, int right, int mid, int[] temp) {
        int i = 0;
        int righStart = mid + 1;
        int leftStart = left;
        // 合并左右两边数组，交替取小的数值
        while (leftStart <= mid && righStart <= right) {
            if(arrays[leftStart] <= arrays[righStart] ) {
                temp[i] = arrays[leftStart];
                i++;
                leftStart++;
            }else {
                temp[i] = arrays[righStart];
                i++;
                righStart++;
            }
        }

        // 合并剩下的数组
        while (leftStart <= mid) {
            temp[i] = arrays[leftStart];
            i++;
            leftStart++;
        }
        while (righStart <= right) {
            temp[i] = arrays[righStart];
            i++;
            righStart++;
        }

        // 重新赋值待合并数组
        int k = 0;
        while (left <= right ) {
            arrays[left] = temp[k];
            left++;
            k++;
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arrays, int start, int end) {
        // 如果每一轮基准排序结束后返回
		if(start > end){
			return;
		}

        int base = arrays[start];
		int i = start,j = end;
        int temp;
        // 保证调用不会内存泄露
        while (start < end) {
            // 从最右边开始查找如果比基准书小则结束查找，锁定位置
            while (base <= arrays[end] && start < end) {
                end--;
            }
            // 从最左边开始查找如果比基准数大则结束查找，锁定位置
            while (base >= arrays[start] && start < end) {
                start++;
            }
            // 交换比基准数大的和小的数值位置
            if (start < end) {
                temp = arrays[start];
                arrays[start] = arrays[end];
                arrays[end] = temp;
            }
        }

        // 基准数归位
        arrays[i] = arrays[start];
        arrays[start] = base;

        // 递归遍历
        quickSort(arrays,i,start - 1);
        quickSort(arrays,start + 1,j);
    }

    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        return arr;
    }

    /**
     * int[] arr 希尔排序
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int incre = arr.length;
        while (true) {
            incre = incre / 2;

            // 分组
               for (int x = 0; x < incre; x++) {
                // 每组排序
                for (int i = x; i < arr.length; i = i + incre) {
                    int waitSortVal = arr[i];

                    // 每组进行插入排序
                    for (int j = x; j < i; j = j + incre) {
                        if (arr[j] > waitSortVal) {
                            // 位移插入位置右边的数字
                            for (int k = i; k > j; k = k - incre) {
                                arr[k] = arr[k - incre];
                            }
                            arr[j] = waitSortVal;
                            break;
                        }
                    }
                }
            }

            if (incre == 1) {
                break;
            }
        }

        return arr;
    }

    /**
     * 二分法插入排序
     * @param arr
     * @return 排序完数组
     */
    public static int[] dichotomyInsertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            if (left != i) {
                arr[left] = temp;
            }
        }


        return arr;
    }


    /**
     * 直接插入排序
     * @param arr 待排序数组
     * @return int[] 排序完数组
     */
    public static int[] directSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int waitSortVal = arr[i];

            for (int j = 0; j < i; j++) {
                if (arr[j] > waitSortVal) {
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k-1];
                    }
                    arr[j] = waitSortVal;
                    break;
                }
            }
        }

        return arr;
    }

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
        if (start < end) {
            int middleNumIndex = (start + end) / 2 + 1;
            if (soredArrs[middleNumIndex] == searchNum) {
                return middleNumIndex;
            } else if (soredArrs[middleNumIndex] > searchNum) {
                return binarySearch(soredArrs, start, middleNumIndex, searchNum);
            } else {
                return binarySearch(soredArrs, middleNumIndex, end, searchNum);
            }
        } else {
            return -1;
        }
    }
}
