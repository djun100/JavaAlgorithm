package com.cy.sort

import com.cy.util.UtilSort

class MyQuickSort1 {
    companion object{
        @JvmStatic
        fun quickSort(arr: IntArray?, low: Int, high: Int) {
            if (arr == null || low < 0 || high < 0 || low >= high || high > arr.size - 1) {
                return
            }

            val pivot = arr[low]
            var leftIndex = low
            var rightIndex = high
            while (leftIndex < rightIndex) {
                while (arr[rightIndex] >= pivot && leftIndex < rightIndex) {
                    rightIndex--
                }

                while (arr[leftIndex] <= pivot && leftIndex < rightIndex) {
                    leftIndex++
                }
                if (leftIndex < rightIndex) {
                    UtilSort.swap(arr, leftIndex, rightIndex)
                }
            }
            arr[low] = arr[leftIndex]
            arr[leftIndex] = pivot

            quickSort(arr, low, leftIndex - 1)
            quickSort(arr, leftIndex + 1, high)
        }
    }
}

fun main() {
//        int[] arr = UtilSort.genArr(10);
//        int[] arr = new int[]{13, 2, 6, 16, 4, 4, 15, 17, 12, 19};
    val arr = intArrayOf(1, 9, 2)
    UtilSort.printArr(arr)
    MyQuickSort1.quickSort(arr, 0, arr.size - 1)
    UtilSort.printArr(arr)
}
