package leetcode.editor;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-03-26 15:13
 */
public class heap_sort_my2 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        heapSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static void heapSort(int[] data) {
        int len = data.length;
        buildMaxHeap(data, len);
        System.out.println("data = " + Arrays.toString(data));
        for (int i = data.length - 1; i > 0; i--) { // i是index
            System.out.println("此时堆顶 = " + data[0]);
            swap(data, 0, i);
            len--;
            maxHeapify(data, 0, len);
        }
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = len / 2; i >= 0; i--) {
            maxHeapify(arr, i, len);
        }
    }

    private static void maxHeapify(int[] arr, int i, int len) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) largest = left;
        if (right < len && arr[right] > arr[largest]) largest = right;
        if (largest != i) {
            swap(arr, largest, i);
            maxHeapify(arr, largest, len);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
