package exer;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-12 9:51
 */
public class quick_sort_5_12 {
    static int depth = 0;

    public static void main(String[] args) {
//        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 31};
        int[] data = sort_exer_util.gennerateArray(15, 100);
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = quickSort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static int[] quickSort(int[] data) {
        subStr(data, 0, data.length - 1);
        return data;
    }

    private static void subStr(int[] data, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int low = startIndex, high = endIndex + 1;
            int base = data[startIndex];
            //helper--每段起始和终止index不用输出，通过观察上一轮的基准就可以很容易的观察到
            depth++;
            System.out.println("递归深度=" + depth + "  基准=" + base + "  data=" + Arrays.toString(data));

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

            //helper

            System.out.println("递归深度=" + depth + "  基准=" + base + "  data=" + Arrays.toString(data));
            System.out.println();

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
