package leetcode.editor;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-03-26 11:56
 */
public class heap_sort_my {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = heapSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static int[] heapSort(int[] data) {
        int len = data.length;
        buildMaxHeap(data, len);
        System.out.println("data = " + Arrays.toString(data));
        for (int i = len - 1; i > 0; i--) {//已经建好堆了，如何使用堆进行排序
            System.out.println("此时堆顶data[0] = " + data[0]);
            swap(data, 0, i);
            len--;
            maxHeapify(data, 0, len);//交换，长度-1，重新整理为大顶堆
        }
        return data;
    }

    public static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) { // 倒数第二层开始，最大堆化
            maxHeapify(arr, i, len);
        }
    }

    public static void maxHeapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, len); //往下走
        }


    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
