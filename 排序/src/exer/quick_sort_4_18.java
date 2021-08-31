package exer;

/**
 * @author pxf
 * @create 2021-04-18 21:16
 */
public class quick_sort_4_18 {
    public static void main(String[] args) {
        int[] data = {9, -16, 15, 23, -30, -49, 21, 30, 41};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        quick_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    private static void quick_sort(int[] data) {
        subStr(data, 0, data.length - 1); //传参--index
    }

    private static void subStr(int[] data, int start, int end) {
        if (start < end) {
            int low = start, high = end + 1;
            int base = data[start];
            while (true) {
                // low high 指针就位
                while (low < end && data[++low] <= base) ;
                while (high > start && data[--high] >= base) ;
                if (low < high) swap(data, low, high);
                else break;
            }
            swap(data, start, high); // 标兵base最后在high处
            subStr(data, start, high - 1);
            subStr(data, high + 1, end);
        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
