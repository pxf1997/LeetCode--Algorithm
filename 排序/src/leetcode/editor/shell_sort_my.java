package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-26 14:05
 */
public class shell_sort_my {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        shell_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static void shell_sort(int[] arr) {
        int len = arr.length;
        for (int step = len / 2; step >= 1; step /= 2) { //判断 step==1 也要进for循环
            for (int i = 0; i < len; i++) {
                int temp = arr[i];
                int j = i;
                while (j - step >= 0 && arr[j - step] > temp) {  // arr[j-step] 前插位置
                    arr[j] = arr[j - step];
                    j -= step;
                }
                if (j != i) {
                    arr[j] = temp;
                }

            }

        }
    }

}
