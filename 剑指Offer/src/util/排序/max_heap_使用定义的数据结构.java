package util.排序;

import util.排序.堆_自建数据结构.MaxHeap;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-17 14:32
 */
public class max_heap_使用定义的数据结构 {
    public static void main(String[] args) {
        int[] data = sort_exer_util.gennerateArray(10, 100);
        System.out.println("排序之前：\n" + Arrays.toString(data));
        int[] result = heapSort(data);
        System.out.println("排序之后：\n" + Arrays.toString(result));
    }

    private static int[] heapSort(int[] data) {
        int len = data.length;
        int[] res = new int[len];
        // int[] 转 Integer[]
        Integer[] data_Integer = Arrays.stream(data).boxed().toArray(Integer[]::new);
        MaxHeap<Integer> maxheap = new MaxHeap<Integer>(data_Integer);

        for (int i = 0; i < len; i++) {
            res[len - 1 - i] = maxheap.extractMax();
        }
        return res;
    }
}
