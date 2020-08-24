package com.cy.sort;

import java.util.Arrays;

/**
 * 不好理解，次要掌握，和QuickSort3类似
 */
public class QuickSort2 {


    public static void quickSort(int[] arr) {
        if (arr != null && arr.length > 0) {
            quickSort(arr, 0, arr.length - 1);
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = quickSortOneTime(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int quickSortOneTime(int[] arr, int left, int right) {
        //数组的第一个作为中轴（swap第一步）
        int pivot = arr[left];
        while (left < right) {
            //从右往左，找到第一个比标准值小的index
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            //比标准值小的元素移到低端（swap第二步）
            arr[left] = arr[right];
            //从左往右，找到第一个比标准值大的index
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //比标准值大的元素移到高端（swap第三步）
            arr[right] = arr[left];
        }

        arr[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 1, 5, 7, 10, 3, 5, 9, 0};
        System.out.println(Arrays.toString(a));
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}