/**
 * 题目Id：525
 * 题目：连续数组
 * 日期：2021-06-03 10:16:09
 */
//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量0和1的最长连续子数组。 
//
// 示例 2: 
//
// 
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// nums[i] 不是 0 就是 1 
// 
// Related Topics 哈希表 
// 👍 316 👎 0


package daily_2021_06;

//连续数组

import java.util.HashMap;
import java.util.Map;

public class P525_ContiguousArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P525_ContiguousArray().new Solution();
//        int res = solution.findMaxLength(new int[]{0, 0, 0, 1, 1, 1, 0, 1});
        int res = solution.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1, 1, 1});
        System.out.println("res = " + res);
    }


    // 暴力法O(n^2)--超时
    class Solution_my {
        public int findMaxLength(int[] nums) {
            int len = nums.length;
            if (len < 2) return 0;
            int totalMax = 0;
            // 遍历起点
            for (int i = 0; i < len; i++) {
                int cnt0 = 0, cnt1 = 0;
                int curMax = 0;
                for (int j = i; j < len; j++) {
                    if (nums[j] == 0) cnt0++;
                    if (nums[j] == 1) cnt1++;
                    if (cnt0 == cnt1) curMax = cnt0 + cnt1;
                }
                totalMax = Math.max(totalMax, curMax);
            }
            return totalMax;

        }
    }

    // 将原数组的0全部变为-1 则问题等价于“元素值总和为0的连续数组”
    // 接着遍历数组 记录当前的前缀和的值 若该前缀和的值已出现过 则说明标记中的下标到当前扫描的下标的这段数组的总和值是为0的
    class Solution {
        public int findMaxLength(int[] nums) {
            int res = 0, sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) nums[i] = -1;
            }
            // key--前缀和  value--此和对应下标
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sum == 0 && i > res) {
                    res = i + 1;
                }
                if (map.containsKey(sum)) {
                    res = Math.max(res, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }

            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案--用一个变量代替整个前缀和
    class Solution1 {
        public int findMaxLength(int[] nums) {
            int maxLength = 0;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int counter = 0;
            map.put(counter, -1);
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                if (num == 1) {
                    counter++;
                } else {
                    counter--;
                }
                if (map.containsKey(counter)) {
                    int prevIndex = map.get(counter);
                    maxLength = Math.max(maxLength, i - prevIndex);
                } else {
                    map.put(counter, i);
                }
            }
            return maxLength;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
