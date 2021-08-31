package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-26 13:52
 */
public class insert_sort_my {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        int[] result = insert_sort2(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));

    }

    public static int[] insert_sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else break;

            }
        }
        return arr;
    }

    public static int[] insert_sort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            if (i != j) {
                arr[j] = temp;
            }


        }
        return arr;
    }


}
