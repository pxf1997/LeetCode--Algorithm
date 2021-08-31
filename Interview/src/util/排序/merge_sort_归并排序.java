package util.排序;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-06-21 10:54
 */
public class merge_sort_归并排序 {
    public static void main(String[] args) {
        int[] data = sort_exer_util.gennerateArray(10, 100);
        System.out.println("data = " + Arrays.toString(data));
        int[] res = merge_sort(data);
        System.out.println("res = " + Arrays.toString(res));
    }

    private static int[] merge_sort(int[] data) {
        int len = data.length;
        int[] res = Arrays.copyOfRange(data, 0, len);
        int[] temp = new int[len];
        merge_sort_helper(res, 0, len - 1, temp);
        return res;
    }

    private static void merge_sort_helper(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            merge_sort_helper(nums, left, mid, temp);
            merge_sort_helper(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        // 左边 [left,mid]  右边 [mid+1,right]  操作范围 [left,right]
        int i = left;    //左边的有序数组的左边界
        int j = mid + 1; //右边的有序数组的左边界
        int index = left;
        while (i <= mid && j <= right) {
            if(nums[i]<= nums[j]){
                temp[index++] = nums[i++];
            } else{
                // 注:可以在此统计逆序对
                temp[index++] = nums[j++];
            }
        }
        // 左边没用完的
        while (i <= mid) {
            temp[index++] = nums[i++];
        }
        // 右边没用完的
        while (j <= right) {
            temp[index++] = nums[j++];
        }
        // 至此 temp 的 [left,right] 区间已经有序,将结果赋值给 nums
        while (left <= right) {
            nums[left] = temp[left++];
        }
    }


}
