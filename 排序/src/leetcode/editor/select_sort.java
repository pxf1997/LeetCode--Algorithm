package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-25 21:31
 */
public class select_sort {
    public static void select_sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}
