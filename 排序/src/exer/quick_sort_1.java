package exer;

/**
 * @author pxf
 * @create 2021-03-27 0:33
 */
public class quick_sort_1 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 41};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        quick_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static void quick_sort(int[] data) {
        subStr(data, 0, data.length - 1);

    }

    private static void subStr(int[] arr, int start, int end) {
        if (start < end) {
            int low = start, high = end + 1;
            int base = arr[start];
            while (true) {
                while (low < end && arr[++low] <= base) ;
                while (high > start && arr[--high] >= base) ;
                if (low < high) swap(arr, low, high);
                else break;
            }
            swap(arr, start, high);
            subStr(arr, start, high - 1);
            subStr(arr, high + 1, end);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
