package exer;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-04-20 9:20
 */
public class heap_sort_4_20 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = heapSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static int[] heapSort(int[] data) {
        int len = data.length;
        buildMaxHeap(data, data.length);
        System.out.println("data = " + Arrays.toString(data));
        for (int i = len - 1; i > 0; i--) {
            //i为index 终止条件为i=1（i=0也没错）
            System.out.println("此时堆顶data[0] = " + data[0]);
            sort_exer_util.swap(data, 0, i);
            len--;
            maxHeapify(data, 0, len);

        }

        return data;
    }

    private static void buildMaxHeap(int[] data, int len) { //len为长度 不是index
        for (int i = len / 2; i >= 0; i--) {
            maxHeapify(data, i, len);
        }
    }

    private static void maxHeapify(int[] data, int i, int len) {
        int left = i * 2 + 1, right = i * 2 + 2;
        int maxIndex = i;
        if (left < len && data[left] > data[maxIndex]) maxIndex = left;
        if (right < len && data[right] > data[maxIndex]) maxIndex = right;
        if (maxIndex != i) {
            sort_exer_util.swap(data, maxIndex, i);
            maxHeapify(data, maxIndex, len);
        }
    }


}
