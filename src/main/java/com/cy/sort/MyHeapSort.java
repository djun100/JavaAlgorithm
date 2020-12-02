package com.cy.sort;

import com.cy.util.UtilSort;

class MyHeapSort {
    public static void heapSort(int[] nums) {
        int len = nums.length - 1;

        //buildMaxHeap
        for (int i = len / 2; i >= 0; --i) {
            maxHeapify(nums, i, len);
        }

        for (int i = len; i >= 1; --i) {
            UtilSort.swap(nums, i, 0);
            len -= 1;
            maxHeapify(nums, 0, len);
        }
    }

    //把大值拿走后交换过来的小值循环下沉到合适的位置
    public static void maxHeapify(int[] nums, int i, int len) {
        for (; (i << 1) + 1 <= len;) {
            int lson = (i << 1) + 1;
            int rson = (i << 1) + 2;
            int large;
            if (lson <= len && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }
            if (rson <= len && nums[rson] > nums[large]) {
                large = rson;
            }
            if (large != i) {
                UtilSort.swap(nums, i, large);
                //有变动的节点继续使下方的子节点调整为大根堆
                i = large;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,5,8};
        heapSort(arr);
        UtilSort.printArr(arr);
    }
}
