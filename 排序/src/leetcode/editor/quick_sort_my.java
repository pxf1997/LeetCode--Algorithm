package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-26 14:12
 */
public class quick_sort_my {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 38};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        quick_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static void quick_sort(int[] arr) {
        subSort(arr, 0, arr.length - 1); // 传参--index下标

    }

    public static void subSort(int[] arr, int start, int end) {
        if (start < end) {
            int low = start, high = end + 1;
            int base = arr[start];
            while (true) {
                // low high 指针定位
                while (low < end && arr[++low] <= base) {
                }
                while (high > start && arr[--high] >= base) {
                }
                if (low < high) swap(arr, low, high);
                else break;
            }
            swap(arr, start, high);
            subSort(arr, start, high - 1);
            subSort(arr, high + 1, end);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
