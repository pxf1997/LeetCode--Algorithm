package exer;

import org.testng.annotations.Test;
import util.排序.sort_exer_util;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-08-20 11:00
 */
public class quick_sort {
    public void quick_sort(int[] nums) {
        subStr(nums, 0, nums.length - 1);
    }

    private void subStr(int[] nums, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int base = nums[startIndex];
            int low = startIndex, high = endIndex + 1;
            while (true) {
                while (low < endIndex && nums[++low] <= base) ;
                while (high > startIndex && nums[--high] >= base) ;
                if (low < high) swap(nums, low, high);
                else break;
            }
            swap(nums, startIndex, high);
            subStr(nums, startIndex, high - 1);
            subStr(nums, high + 1, endIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test1() {
        int[] data = sort_exer_util.gennerateArray(15, 100);
        System.out.println("data = " + Arrays.toString(data));
        quick_sort(data);
        System.out.println("data = " + Arrays.toString(data));
    }
}
