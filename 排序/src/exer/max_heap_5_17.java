package exer;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-17 14:32
 */
public class max_heap_5_17 {
    public static void main(String[] args) {
        int[] data = sort_exer_util.gennerateArray(10, 100);
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = heapSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(result));
    }

    private static int[] heapSort(int[] data) {
        int len = data.length;
        int[] res = new int[len];
        // int[] 转 Integer[]
        Integer[] integers = Arrays.stream(data).boxed().toArray(Integer[]::new);
        MaxHeap<Integer> maxheap = new MaxHeap<Integer>(integers);


        for (int i = 0; i < len; i++) {
            res[len - 1 - i] = maxheap.extractMax();
        }
        return res;
    }
}
