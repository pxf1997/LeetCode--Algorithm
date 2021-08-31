package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-25 22:18
 */
public class quick_sort {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        quick_sort1(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static void quick_sort(int[] arr) {
        subSort(arr, 0, arr.length - 1);

    }

    private static void subSort(int[] arr, int start, int end) {
        if (start < end) {
            int base = arr[start];
            int low = start;
            int high = end + 1;
            while (true) {
                while (low < end && arr[++low] <= base) {
                    // low左指针定位到比base大的数
                }
                while (high > start && arr[--high] >= base) {
                    // high右指针定位到比base小的数
                }
                if (low < high) swap(arr, low, high);
                else break; //跳出条件--指针交错
            }
            swap(arr, start, high); //第一个base与high处交换
            subSort(arr, start, high - 1);
            subSort(arr, high + 1, end);

        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quick_sort1(int[] arr) {
        subSort1(arr, 0, arr.length - 1);
    }

    public static void subSort1(int[] arr, int start, int end) {
        if (start < end) {
            int base = arr[start];
            int l = start, h = end + 1;
            while (true) {
                while (l < end && arr[++l] <= base) ;
                while (h > start && arr[--h] >= base) ;
                if (l < h) swap(arr, l, h);
                else break;
            }
            swap(arr, start, h);
            subSort1(arr, start, h - 1);
            subSort1(arr, h + 1, end);
        }
    }


}
