package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pxf
 * @create 2021-05-26 14:15
 */
public class 数组相关 {

    /**
     * 统计数组各元素出现次数
     */
    public static int[] find_Frequency(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();

        int[] frequency = new int[max + 1]; // 下标最大为max 长度max+1
        for (int num : nums) {
            frequency[num]++;
        }
        return frequency;
    }

    /**
     * 统计数组各元素出现次数
     */
    public static Map<Integer, Integer> find_Frequency_map(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        return frequency;
    }

    /**
     * 指定范围的最小值
     */
    private int findMin(int[] arr, int beginIndex, int endIndex) {
        int min = Integer.MAX_VALUE;
        for (int i = beginIndex; i <= endIndex; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    /**
     * 指定范围的最大值
     */
    private int findMax(int[] arr, int beginIndex, int endIndex) {
        int max = Integer.MIN_VALUE;
        for (int i = beginIndex; i <= endIndex; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    /**
     * 前缀和
     */
    private int[] preSum(int[] nums) {
        int len = nums.length;
        // preSum[i] 到原数组下标i之前 的所有元素之和
        // 推导出共识 区间 [i,j] 元素和 = preSum[j+1] - preSum[i]
        int[] preSum = new int[len + 1];
        int cur = 0;
        for (int i = 0; i < len; i++) {
            cur += nums[i];
            preSum[i + 1] = cur;
        }
        return preSum;
    }


}
