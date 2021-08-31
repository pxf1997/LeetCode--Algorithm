/**
 * 题目Id：581
 * 题目：最短无序连续子数组
 * 日期：2021-08-03 10:54:02
 */
//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。 
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？ 
// 
// 
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 
// 👍 615 👎 0


package daily_2021_08;

//最短无序连续子数组

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P581_ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P581_ShortestUnsortedContinuousSubarray().new Solution();

//        int res = solution.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15});
        int res = solution.findUnsortedSubarray(new int[]{1, 4, 2, 3, 5});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 评价--逻辑有误!
    // 分析-- "排序集"{6, 4, 8, 10, 9}的特点:
    // 左右侧均升序, 左侧最大元素2 < 排序集中最小元素, 右侧最小元素15 > 排序集中最大元素
    class Solution_my {
        public int findUnsortedSubarray(int[] nums) {
            // 找到逆序位置
            int len = nums.length;
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < len - 1; i++) {
                if (nums[i] > nums[i + 1]) list.add(i);
            }
            if (list.size() == 0) return 0;
            if (list.size() == 1) return 2;
            // 排序范围[start, end]
            int start = list.get(0);
            int end = list.get(list.size() - 1) + 1;
            return end - start + 1;
        }
    }

    // 参考答案--分析:
    // nums分为三部分 numsA numsB(需要排序) numsC 要找最短的numsB,即最长的numsC
    // 将nums排序,最长相同前缀为numsA,相同后缀为numsC,剩余部分就是numsB
    class Solution {
        public int findUnsortedSubarray(int[] nums) {
            if (isSorted(nums)) return 0;
            int len = nums.length;
            // 拷贝nums内容并且排序
            int[] numsSorted = Arrays.copyOfRange(nums, 0, len);
            Arrays.sort(numsSorted);
            // 分析while终止循环的条件, left/right指针位置的元素是不同的(需要排序)
            int left = 0;
            while (nums[left] == numsSorted[left]) left++;
            int right = len - 1;
            while (nums[right] == numsSorted[right]) right--;
            // 需要排序范围[left, right]
            return right - left + 1;
        }

        private boolean isSorted(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                // 发现逆序
                if (nums[i] > nums[i + 1]) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
