package exer;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-17 14:51
 */
public class min_heap_5_17 {
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
        Integer[] data_Integer = Arrays.stream(data).boxed().toArray(Integer[]::new);
        MinHeap<Integer> minheap = new MinHeap<Integer>(data_Integer);

        for (int i = 0; i < len; i++) {
            res[i] = minheap.extractMin();
        }
        return res;
    }


}
