package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-25 21:49
 */
public class insert_sort {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 31};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        insert_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }
    public static void insert_sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 记录要插入的数据
            int temp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入  挪过位置，上面的while进去过
            if (j != i) {
                arr[j] = temp;
            }

        }

    }

    public static void insert_sort2(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] > arr[j - 1]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }

            }
        }
    }
}
