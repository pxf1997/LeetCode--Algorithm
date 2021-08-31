package exer;

import leetcode.editor.quick_sort;

/**
 * @author pxf
 * @create 2021-04-15 10:32
 */
public class quick_sort_4_15 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 41};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        quick_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static void quick_sort(int[] data) {
        subStr(data, 0, data.length - 1);
    }

    private static void subStr(int[] data, int start, int end) {
        if (start < end) {
            int low = start, high = end + 1;
            int base = data[start];
            while (true) {
                // low high 指针就位--左右移动到需要交换的元素处
                while (data[++low] <= base && low < end) ;
                while (data[--high] >= base && high > start) ;
                if (low < high) swap(data, low, high);
                else break; // low high 指针交错，base元素索引应为high
            }
            swap(data, start, high);
            subStr(data, start, high - 1);
            subStr(data, high + 1, end);

        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[j];
        data[j] = data[i];
        data[i] = temp;

    }


}
