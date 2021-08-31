package exer;


/**
 * @author pxf
 * @create 2021-04-19 9:48
 */
public class quick_sort_4_19 {
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
                while (low < end && data[++low] <= base) ;
                while (high > start && data[--high] >= base) ;
                if (low < high) swap(data, low, high);
                else break;
            }
            swap(data, start, high);
            subStr(data, start, high - 1);
            subStr(data, high + 1, end);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
