package com.cy.sort;

/**
 * 堆排序：Java
 *http://www.cnblogs.com/skywang12345/p/3602162.html
 * @author skywang
 * @date 2014/03/11
 */

public class HeapSort {

    /*
     * (最大)堆的向下调整算法
     *
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *     其中，N为数组下标索引值，如数组中第1个数对应的N为0。
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
     *     end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    public static void maxHeapDown(int[] arr, int start, int endPoi) {
        // 当前(current)节点position
        int currAsParentPosition = start;
        // 当前(current)节点value
        int currAsParentValue = arr[currAsParentPosition];

        // 左(left)的位置
        int childPoi = 2*currAsParentPosition + 1;

        while (childPoi <= endPoi) {
            // "childPoi"是左孩子，"childPoi+1"是右孩子
            if ( childPoi < endPoi && arr[childPoi] < arr[childPoi+1])
                childPoi++;        // 左右两孩子中选择较大者

            if (currAsParentValue >= arr[childPoi]){
                // 当前父节点>两个子节点中最大的那一个，这三点小分支符合堆形态，无需调整
                break;
            }
            else {
                // 当前父节点<两个子节点中最大的那一个，交换值，让这三点小分支形成堆形态
                arr[currAsParentPosition] = arr[childPoi];
                arr[childPoi]= currAsParentValue;
            }

            currAsParentPosition=childPoi;
            childPoi=2*childPoi+1;
        }
    }

    /*
     * (最小)堆的向下调整算法
     *
     * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
     *     其中，N为数组下标索引值，如数组中第1个数对应的N为0。
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
     *     end   -- 截至范围(一般为数组中最后一个元素的索引)
     */
    public static void minHeapDown(int[] a, int start, int end) {
        int c = start;            // 当前(current)节点的位置
        int l = 2*c + 1;        // 左(left)孩子的位置
        int tmp = a[c];            // 当前(current)节点的大小

        for (; l <= end; c=l,l=2*l+1) {
            // "l"是左孩子，"l+1"是右孩子
            if ( l < end && a[l] > a[l+1])
                l++;        // 左右两孩子中选择较小者
            if (tmp <= a[l])
                break;        // 调整结束
            else {            // 交换值
                a[c] = a[l];
                a[l]= tmp;
            }
        }
    }

    /*
     * 堆排序
     *     a -- 待排序的数组
     *     isAsc -- 是否从小到大
     */
    public static void heapSort(int[] a,boolean isAsc) {
        int i,tmp;

        for (i = a.length / 2 - 1; i >= 0; i--){
            if (isAsc) {
                // 从(n/2-1)最后一个父节点 --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
                maxHeapDown(a, i, a.length-1);
            }else {
                // 从(n/2-1) --> 0逐次遍历每。遍历之后，得到的数组实际上是一个最小堆。
                minHeapDown(a, i, a.length-1);
            }
        }

        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = a.length - 1; i > 0; i--) {
            // 交换a[0]和a[i]。
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            if (isAsc) {
                // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
                // 即，保证a[i-1]是a[0...i-1]中的最大值。
                maxHeapDown(a, 0, i-1);
            }else {
                // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最小堆。
                // 即，保证a[i-1]是a[0...i-1]中的最小值。
                minHeapDown(a, 0, i-1);
            }
        }
    }


    public static void main(String[] args) {
        int i;
        int a[] = {20,30,90,40,70,110,60,10,100,50,80};

        System.out.printf("before sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        heapSort(a,false);         // 升序排列
//        heapSort(a,false);        // 降序排列

        System.out.printf("after  sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");
    }
}