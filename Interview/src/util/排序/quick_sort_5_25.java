package util.排序;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-25 16:39
 */
public class quick_sort_5_25 {
    public static void main(String[] args) {
        // int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 31};
        int[] data = sort_exer_util.gennerateArray(15, 100);
        System.out.println("排序之前：\n" + Arrays.toString(data));
        int[] result = quickSort(data);
        System.out.println("排序之后：\n" + Arrays.toString(result));
    }

    private static int[] quickSort(int[] data) {
        int[] res = new int[data.length];
//        res = data; // 浅拷贝
        System.arraycopy(data, 0, res, 0, data.length);// 深拷贝
        subStr(res, 0, res.length - 1);
        return res;
    }

    private static void subStr(int[] data, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int low = startIndex, high = endIndex + 1;
            int base = data[startIndex];
            while (true) {
                while (low < endIndex && data[++low] <= base) ;
                while (high > startIndex && data[--high] >= base) ;
                if (low < high) {
                    swap(data, low, high);
                } else {
                    break;
                }
            }
            swap(data, startIndex, high);
            subStr(data, startIndex, high - 1);
            subStr(data, high + 1, endIndex);
        }

    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
