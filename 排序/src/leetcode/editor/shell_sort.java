package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-25 21:57
 */
public class shell_sort {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        shell_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static void shell_sort(int[] arr) {
/*        int len = arr.length;
        int temp;
        for (int step = len / 2; step >= 1; step /= 2) {
            for (int i = step; i < len; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }*/
        int len = arr.length;
        int step;
        for (step = len / 2; step >= 1; step /= 2) {//每隔step的插入排序
            for (int i = 0; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }

        }
    }
}

