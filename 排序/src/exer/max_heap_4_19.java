package exer;

/**
 * @author pxf
 * @create 2021-04-19 9:53
 */
public class max_heap_4_19 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = heapSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static int[] heapSort(int[] data) {
        int len = data.length;
        buildMaxHeap(data, len);

        for (int i = len - 1; i > 0; i--) {
//            最大的元素换到最后
            swap(data, 0, i);
//            长度-1
            len--;
//            从堆顶整理为大顶堆
            maxHeapify(data, 0, len);
        }
        return data;
    }

    private static void buildMaxHeap(int[] data, int len) {
        for (int i = len / 2; i >= 0; i--) {
            maxHeapify(data, i, len);
        }
    }

    private static void maxHeapify(int[] data, int i, int len) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int maxIndex = i;
        if (left < len && data[maxIndex] < data[left]) maxIndex = left;
        if (right < len && data[maxIndex] < data[right]) maxIndex = right;
        if (maxIndex != i) {
            swap(data, maxIndex, i);
            maxHeapify(data, maxIndex, len);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
