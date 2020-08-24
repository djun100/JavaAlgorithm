package com.cy.util;

import java.util.Arrays;
import java.util.Random;

public class UtilSort {
    public static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static int[] genArr(int size){
        return genArr(size,20);
    }
    public static int[] genArr(int size,int bound){
        Random random=new Random();
        int[] arr=new int[size];
        for (int i = 0; i < size; i++) {
            arr[i]=random.nextInt(bound);
        }
        return arr;
    }

    public static void printArr(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        printArr(genArr(10));
    }
}
