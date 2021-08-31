package leetcode.editor;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-03-25 21:22
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 38};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        BubbleSort1(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static void BubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void BubbleSort1(int[] arr) {
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            flag = false;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    flag = true; //发生交换
                }
            }
            if (!flag) break;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
