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
    public static void maxHeapDown(int[] a, int start, int end) {
        int c = start;            // 当前(current)节点的位置
        int l = 2*c + 1;        // 左(left)孩子的位置
        int tmp = a[c];            // 当前(current)节点的大小

        for (; l <= end; c=l,l=2*l+1) {
            // "l"是左孩子，"l+1"是右孩子
            if ( l < end && a[l] < a[l+1])
                l++;        // 左右两孩子中选择较大者，即m_heap[l+1]
            if (tmp >= a[l])
                break;        // 调整结束
            else {            // 交换值
                a[c] = a[l];
                a[l]= tmp;
            }
        }
    }

    /*
     * 堆排序(从小到大)
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void heapSortAsc(int[] a, int n) {
        int i,tmp;

        // 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
        for (i = n / 2 - 1; i >= 0; i--)
            maxHeapDown(a, i, n-1);

        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = n - 1; i > 0; i--) {
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
            // 即，保证a[i-1]是a[0...i-1]中的最大值。
            maxHeapDown(a, 0, i-1);
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
     * 堆排序(从大到小)
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void heapSortDesc(int[] a, int n) {
        int i,tmp;

        // 从(n/2-1) --> 0逐次遍历每。遍历之后，得到的数组实际上是一个最小堆。
        for (i = n / 2 - 1; i >= 0; i--)
            minHeapDown(a, i, n-1);

        // 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
        for (i = n - 1; i > 0; i--) {
            // 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最小的。
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            // 调整a[0...i-1]，使得a[0...i-1]仍然是一个最小堆。
            // 即，保证a[i-1]是a[0...i-1]中的最小值。
            minHeapDown(a, 0, i-1);
        }
    }

    public static void main(String[] args) {
        int i;
        int a[] = {20,30,90,40,70,110,60,10,100,50,80};

        System.out.printf("before sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        heapSortAsc(a, a.length);            // 升序排列
        //heapSortDesc(a, a.length);        // 降序排列

        System.out.printf("after  sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");
    }

    /**
     * 归并排序：Java
     http://www.cnblogs.com/skywang12345/p/3602369.html
     * @author skywang
     * @date 2014/03/12
     */

    public static class MergeSort {

        /*
         * 将一个数组中的两个相邻有序区间合并成一个
         *
         * 参数说明：
         *     a -- 包含两个有序区间的数组
         *     start -- 第1个有序区间的起始地址。
         *     mid   -- 第1个有序区间的结束地址。也是第2个有序区间的起始地址。
         *     end   -- 第2个有序区间的结束地址。
         */
        public static void merge(int[] a, int start, int mid, int end) {
            int[] tmp = new int[end-start+1];    // tmp是汇总2个有序区的临时区域
            int i = start;            // 第1个有序区的索引
            int j = mid + 1;        // 第2个有序区的索引
            int k = 0;                // 临时区域的索引

            while(i <= mid && j <= end) {
                if (a[i] <= a[j])
                    tmp[k++] = a[i++];
                else
                    tmp[k++] = a[j++];
            }

            while(i <= mid)
                tmp[k++] = a[i++];

            while(j <= end)
                tmp[k++] = a[j++];

            // 将排序后的元素，全部都整合到数组a中。
            for (i = 0; i < k; i++)
                a[start + i] = tmp[i];

            tmp=null;
        }

        /*
         * 归并排序(从上往下)
         *
         * 参数说明：
         *     a -- 待排序的数组
         *     start -- 数组的起始地址
         *     endi -- 数组的结束地址
         */
        public static void mergeSortUp2Down(int[] a, int start, int end) {
            if(a==null || start >= end)
                return ;

            int mid = (end + start)/2;
            mergeSortUp2Down(a, start, mid); // 递归排序a[start...mid]
            mergeSortUp2Down(a, mid+1, end); // 递归排序a[mid+1...end]

            // a[start...mid] 和 a[mid...end]是两个有序空间，
            // 将它们排序成一个有序空间a[start...end]
            merge(a, start, mid, end);
        }


        /*
         * 对数组a做若干次合并：数组a的总长度为len，将它分为若干个长度为gap的子数组；
         *             将"每2个相邻的子数组" 进行合并排序。
         *
         * 参数说明：
         *     a -- 待排序的数组
         *     len -- 数组的长度
         *     gap -- 子数组的长度
         */
        public static void mergeGroups(int[] a, int len, int gap) {
            int i;
            int twolen = 2 * gap;    // 两个相邻的子数组的长度

            // 将"每2个相邻的子数组" 进行合并排序。
            for(i = 0; i+2*gap-1 < len; i+=(2*gap))
                merge(a, i, i+gap-1, i+2*gap-1);

            // 若 i+gap-1 < len-1，则剩余一个子数组没有配对。
            // 将该子数组合并到已排序的数组中。
            if ( i+gap-1 < len-1)
                merge(a, i, i + gap - 1, len - 1);
        }

        /*
         * 归并排序(从下往上)
         *
         * 参数说明：
         *     a -- 待排序的数组
         */
        public static void mergeSortDown2Up(int[] a) {
            if (a==null)
                return ;

            for(int n = 1; n < a.length; n*=2)
                mergeGroups(a, a.length, n);
        }

        public static void main(String[] args) {
            int i;
            int a[] = {80,30,60,40,20,10,50,70};

            System.out.printf("before sort:");
            for (i=0; i<a.length; i++)
                System.out.printf("%d ", a[i]);
            System.out.printf("\n");

            mergeSortUp2Down(a, 0, a.length-1);        // 归并排序(从上往下)
            //mergeSortDown2Up(a);                    // 归并排序(从下往上)

            System.out.printf("after  sort:");
            for (i=0; i<a.length; i++)
                System.out.printf("%d ", a[i]);
            System.out.printf("\n");
        }
    }
}