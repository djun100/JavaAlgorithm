package com.cy.sort;

/**
 * Created by cy on 2015/11/2.
 */
public class Rank {
    /**直接插入排序*/
    public static void insertSort(int[] r){
        for (int i=1; i<=r.length-1; i++){
            if (r[i]<r[i-1]){  //小于时，需将 r[i]插入有序表
                int temp = r[i];
                r[i] = r[i-1];
                int j=i-2;
                for (; j>=0 && temp<r[j]; j--){
                    r[j+1] = r[j];      //记录后移
                }
                r[j+1] = temp;       //插入到正确位置
            }
        }
    }

    public static void main(String args[]){
        int[] array={26,53,48,11,13,48,32,15};
        insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
