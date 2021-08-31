package exer;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-12 10:23
 */
public class 快速选择第k小的元素_简化快排 {
    public static void main(String[] args) {
        int[] data = sort_exer_util.gennerateArray(15, 100);
        System.out.println("data = " + Arrays.toString(data));
        System.out.println();

        int kthSmallest = findKthSmallest(data, 5);
        System.out.println("5 thSmallest = " + kthSmallest);
    }

    /**
     * @param nums 数组
     * @param k    排序后数组的下标k--第k+1小
     * @return 排序后数组中下标为k的元素
     */
    public static int findKthSmallest(int[] nums, int k) {
        System.out.println("k = " + k);
        int lo = 0, hi = nums.length - 1;
        //有点类似二分法
        while (lo < hi) {
            int j = partition(nums, lo, hi); //j为基准元素(第一个)定位后的下标
            System.out.println("lo=" + lo + "  hi=" + hi);
            System.out.println("基准元素=" + nums[j] + " 所在下标=" + j + "  " + Arrays.toString(nums));
            if (j == k) {
                break;
            }
            if (j < k) {
                System.out.println("下标为" + k + "的元素出现在右侧");
                System.out.println();
                lo = j + 1;
            } else {
                System.out.println("下标为" + k + "的元素出现在左侧");
                System.out.println();
                hi = j - 1;
            }
        }
        return nums[k];
    }

    public static int partition(int[] nums, int startIndex, int endIndex) {
        int low = startIndex, high = endIndex + 1;
        int base = nums[startIndex];
        while (true) {
            while (nums[++low] <= base && low < endIndex) ;
            while (nums[--high] >= base && high > startIndex) ;
            if (low < high) {
                swap(nums, low, high);
            } else {
                break;
            }
        }
        swap(nums, startIndex, high);
        return high; //快排简化版---只定位base元素所在的下标
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
