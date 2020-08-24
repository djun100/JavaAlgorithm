package com.cy.sort;

import com.cy.util.UtilSort;

import java.util.Arrays;

public class QuickSort1 {
    public static void quickSort(int[] a) {
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }

    /**核心函数
     * @param array
     * @param low
     * @param high
     */
    private static void quickSort(int[] array, int low, int high) {
        //1,找到递归算法的出口
        if (low > high) {
            return;
        }
        //2, 存
        int leftIndex = low;
        int rightIndex = high;

        //4，以array[low]为参照值，完成一趟排序
        while (leftIndex < rightIndex) {
            //4.1 从右往左找到第一个小于pivotValue的数 思路：如果比标准值大，就设置为左边的一个
            while (array[rightIndex] > array[low]) {
                rightIndex--;
            }
            //4.2 从左往右找到第一个大于pivotValue的数 思路：如果比标准值小，就设置为右边的一个
            while (leftIndex < rightIndex && array[leftIndex] <= array[low]) {
                leftIndex++;
            }
            //4.3 交换
            if (leftIndex < rightIndex) {
                UtilSort.swap(array, leftIndex, rightIndex);
            }
        }

        // 4.4，调整key的位置
        // 分析：leftIndex在上面swap之前是rightIndex，rightIndex是从右往左最后一个小于pivotValue的数
        // 所以不存在把中间的大值换到最前面的情况
        UtilSort.swap(array, leftIndex, low);
        //5, 对pivotValue左边的数快排
        quickSort(array, low, leftIndex - 1);
        //6, 对pivotValue右边的数快排
        quickSort(array, leftIndex + 1, high);
    }


    public static void main(String[] args) {
        int[] a = {4, 2, 1, 5, 7, 10, 3, 5, 9, 0};
        System.out.println(Arrays.toString(a));
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

}