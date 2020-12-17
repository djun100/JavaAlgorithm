package com.cy.sort;

import com.cy.util.UtilSort;

class MyHeapSort {
    public static void heapSort(int[] nums) {
        //buildMaxHeap，从最后一个父节点开始调整
        for (int i = (nums.length / 2) -1; i >= 0; --i) {
            maxHeapify(nums, i, nums.length);
        }

        for (int i = nums.length-1; i >= 1; --i) {
            UtilSort.swap(nums, i, 0);
            maxHeapify(nums, 0, i);
        }
    }

    //把大值拿走后交换过来的小值循环下沉到合适的位置
    public static void maxHeapify(int[] nums, int i, int len) {
        for (; (i << 1) + 1 < len;) {
            int lson = (i << 1) + 1;
            int rson = (i << 1) + 2;
            int large;
            if (lson < len && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }
            if (rson < len && nums[rson] > nums[large]) {
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
        int[] arr = UtilSort.genArr(10);
        heapSort(arr);
        UtilSort.printArr(arr);
    }
}
