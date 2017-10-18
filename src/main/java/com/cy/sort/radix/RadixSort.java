package com.cy.sort.radix;

/**
 * 对于一个int数组，请编写一个基数排序算法，对数组元素排序。
 * 给定一个int数组A及数组的大小n，请返回排序后的数组。保证元素均小于等于2000。
 * Created by cy on 2016/1/17.
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 5, 2, 3};
        radixSort(array, 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**@param  columns 表示最大的数有多少位*/
    public static int[] radixSort(int[] array, int columns)
    {
        int k = 0;
        int currentColumn = 1;
        int[][] temp = new int[10][array.length]; //数组的第一维表示可能的余数0-9
        int[] order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        for ( int m=0;m < columns;m++) {

            for (int i = 0; i < array.length; i++) {
                int lsd = ((array[i] / currentColumn) % 10);
                temp[lsd][order[lsd]] = array[i];
                order[lsd]++;
            }
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0)
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }

            currentColumn *= 10;
            k = 0;
        }
        return array;
    }
}
