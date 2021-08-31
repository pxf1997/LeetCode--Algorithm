package util.排序.exer;

import leetcode.editor.cn.P345_ReverseVowelsOfAString;
import org.junit.Test;
import util.排序.sort_exer_util;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-08-19 13:00
 */
public class quick_sort {
    public void quick_sort(int[] nums) {
        subStr(nums, 0, nums.length - 1);
    }

    private void subStr(int[] nums, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int base = nums[startIndex];
            // 初始值--配合后面 ++low --high
            int low = startIndex, high = endIndex + 1;
            while (true) {
                // 先写下标防止越界
                while (low < endIndex && nums[++low] <= base) ;
                while (high > startIndex && nums[--high] >= base) ;
                if (low < high) {
                    swap(nums, low, high);
                } else {
                    break;
                }
            }
            swap(nums, startIndex, high);
            subStr(nums, startIndex, high - 1);
            subStr(nums, high + 1, endIndex);
        }
    }

    private void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }
/*    public void quick_sort(int[] nums) {
        helper(nums, 0, nums.length - 1);
    }

    private void helper(int[] nums, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int base = nums[startIndex];
            int left = startIndex, right = endIndex + 1;
            while (true) {
                while (left < endIndex && nums[++left] <= base) ;
                while (right > startIndex && nums[--right] >= base) ;
                if (left < right) {
                    swap(nums, left, right);
                } else {
                    break;
                }
            }
            swap(nums, startIndex, right);
            helper(nums, startIndex, right - 1);
            helper(nums, right + 1, endIndex);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }*/

    @Test
    public void test1() {
        //测试代码
        int[] nums = sort_exer_util.gennerateArray(15, 100);
        System.out.println("nums = " + Arrays.toString(nums));
        quick_sort(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }
}
