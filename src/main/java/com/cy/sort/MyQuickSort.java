package com.cy.sort;

import com.cy.util.UtilSort;


public class MyQuickSort {

    private static void quickSort(int[] arr, int low, int high) {
//1.为什么必须是大于或者大于等于，等于就是错的（由于递归处的逻辑，high有肯能为-1，所以low有可能大于high）
        if (low >= high){
            return;
        }
        int leftIndex = low;
        int rightIndex = high;

//2.基准位，因为数组在变，尽管low不变，后续不能直接使用arr[low]，因为可能被交换到别的位置去了
        int pivot = arr[low];


        while (leftIndex < rightIndex) {

//3.为什么必须先从右往左，从左往右就是错的（参照值最后肯定要放到中间，如果两个指针重合位置的值比基准值大，互换基准值与重合值就是错误的）
            while (arr[rightIndex] >= pivot && leftIndex < rightIndex) {
                rightIndex--;
            }

//4.为什么必须是小于等于pivot，小于就是错的
            while (arr[leftIndex] <= pivot && leftIndex < rightIndex) {
                leftIndex++;
            }


            if (leftIndex < rightIndex) {
                UtilSort.swap(arr,leftIndex,rightIndex);
            }
        }
//5.交换的是值，而不是位置
//        UtilSort.swap(arr,low,leftIndex);
//6.leftIndex的值为什么要和基准值互换？(leftIndex值一定小于等于基准值，最左边的基准值极大可能大于右侧左半部分)
        arr[low] = arr[leftIndex];
        arr[leftIndex] = pivot;

        quickSort(arr,low,leftIndex-1);
        quickSort(arr,leftIndex+1,high);
    }

    public static void main(String[] args) {
//        int[] arr = UtilSort.genArr(10);
//        int[] arr = new int[]{13, 2, 6, 16, 4, 4, 15, 17, 12, 19};
        int[] arr = new int[]{1, 9, 2};
        UtilSort.printArr(arr);

        if (arr != null && arr.length > 0) {
            quickSort(arr, 0, arr.length - 1);
        }
        UtilSort.printArr(arr);
    }

}
