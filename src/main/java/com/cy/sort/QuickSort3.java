package com.cy.sort;

import com.cy.util.UtilSort;

import java.util.Arrays;

public class QuickSort3 {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp;
        if (low >= high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                UtilSort.swap(arr, i, j);
            }
        }

        /**
         跳出上面大的while循环之后，i和j肯定是相等的，因为当i和j相差1时，如果要i和j反差1，
         那么j--和i++必须都发生，那么两次都要满足i<j，但是当之一发生变不再满足i<j的条件，
         所以，跳出循环时，i和j是相等的

         假如最好的情况是一个有序序列 1 3 5 7 9
         temp = 1
         i = 0    arr[i] = 1
         j = 4    arr[j] = 9
         而且在这里，如果先从左边开始寻找的话，一直往右寻找大于1的数，直到i变成4还没有找到就停止了；
         但是下面的语句就会把9赋值在1上了，如果先从右边开始寻找的话，一直往左寻找小于1的数，
         直到j变成0还没有找到然后停止，此时i和j都是0，所以就是把自身交换一下并不影响顺序。
         这也是为什么强调如果选择数组左边第一个数作为基准值的时候，得先从右边开始查找数。
         */
        // 最后将基准位与i和j相等位置的数字交换
        // 下面的i和j其实相等的，所以用哪一个都一样。
        // 最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }


    public static void main(String[] args) {
        int[] arr = UtilSort.genArr(10);
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
}