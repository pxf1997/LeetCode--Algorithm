package exer;

/**
 * @author pxf
 * @create 2021-04-21 14:37
 */
public class quick_sort_4_21 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 31};
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
            int lo = startIndex, hi = endIndex + 1;
            int base = data[startIndex];
            while (true) {
                while (lo < endIndex && data[++lo] <= base) ;
                while (hi > startIndex && data[--hi] >= base) ;
                if (lo < hi) swap(data, lo, hi);
                else break;
            }
            swap(data, startIndex, hi);
            subStr(data, startIndex, hi - 1);
            subStr(data, hi + 1, endIndex);
        }
    }

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
