package leetcode.editor;

/**
 * @author pxf
 * @create 2021-03-25 22:21
 */
public class merge_sort {
    public static void main(String[] args) {
        int[] data = {9, -16, 21, 23, -30, -49, 21, 30, 30};
        System.out.println("排序之前：\n" + java.util.Arrays.toString(data));
        merge_sort(data);
        System.out.println("排序之后：\n" + java.util.Arrays.toString(data));
    }

    public static void merge_sort(int[] data) {
        // 归并排序
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] data, int left, int right) {
        if (left < right) {
            //找出中间索引
            int center = (left + right) / 2;
            sort(data, left, center);
            sort(data, center + 1, right);
            //合并
            merge(data, left, center, right);
        }
    }

    // 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序
    private static void merge(int[] data, int left, int center, int right) {
        int[] tempArr = new int[data.length];//新开辟一块tempArr数组
        int mid = center + 1;
        int third = left;
        int temp = left;
        while (left <= center && mid <= right) {
            if (data[left] - data[mid] <= 0) {
                tempArr[third++] = data[left++];
            } else {
                tempArr[third++] = data[mid++];
            }
        }
        //拉链法
        while (mid <= right) { //右边有剩
            tempArr[third++] = data[mid++];
        }
        while (left <= center) { //左边有剩
            tempArr[third++] = data[left++];
        }
        while (temp <= right) { //复制回data数组
            data[temp] = tempArr[temp++];
        }
    }
}
