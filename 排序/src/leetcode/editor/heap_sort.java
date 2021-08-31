package leetcode.editor;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-03-26 11:39
 */
public class heap_sort {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = heapSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static int[] heapSort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr, len);
        for (int i = len-1; i >0 ; i--) {
            swap(arr, 0, i);
            len--;
            maxHeapify(arr, 0, len);
        }
        return arr;
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            maxHeapify(arr, i, len);
        }
    }

    private static void maxHeapify(int[] arr, int i, int len) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < len && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < len && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, len);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

