/**
 * 题目Id：930
 * 题目：和相同的二元子数组
 * 日期：2021-07-08 09:22:10
 */
//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。 
//
// 子数组 是数组的一段连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//如下面黑体所示，有 4 个满足题目要求的子数组：
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// nums[i] 不是 0 就是 1 
// 0 <= goal <= nums.length 
// 
// Related Topics 数组 哈希表 前缀和 滑动窗口 
// 👍 124 👎 0


package daily_2021_07;

//和相同的二元子数组

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P930_BinarySubarraysWithSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P930_BinarySubarraysWithSum().new Solution();
//        int cnt = solution.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2);
        int cnt = solution.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0);
        System.out.println("cnt = " + cnt);
    }


    // 暴力法,O(n^2)
    // 问题规模 1 <= nums.length <= 3 * 10^4 估计超时!
    class Solution1 {
        public int numSubarraysWithSum(int[] nums, int goal) {
            int cnt = 0;
            int len = nums.length;
            // 子数组范围[i,j]
            for (int i = 0; i < len; i++) {
                int curSum = 0;
                // 注意 nums[i] 不是 0 就是 1,即不可能 +1 又 -1
                for (int j = i; j < len; j++) {
                    curSum += nums[j];
                    // 等于goal,结果+1
                    if (curSum == goal) {
                        //System.out.println("找到一个结果,下标范围[" + i + "," + j + "]");
                        cnt++;
                    }
                    // 大于goal,break
                    else if (curSum > goal) break;
                }
            }
            return cnt;
        }
    }


    // 优化--可以优化为前缀和
    // 定义preSum[i+1]--nums下标[0,i]
    // 则有公式 nums下标范围[i,j]的和 = preSum[j+1]-preSum[i]
    // 总结----逻辑没搞清,会丢失一部分解!!!
    class Solution_wrong {
        public int numSubarraysWithSum(int[] nums, int goal) {
            int cnt = 0;
            int len = nums.length;
            int[] preSum = new int[len + 1];
            for (int i = 1; i < len + 1; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
            System.out.println("preSum = " + Arrays.toString(preSum));
            // 滑动窗口,left指向区间左端,right指向区间右端,
            // 子数组下标范围[left, right],用前缀和表示 preSum[right+1]-preSum[left]
            int right = 0, left = 0;
            while (right < len && left <= right) {
                int curSum = preSum[right + 1] - preSum[left];
                // 找到结果--怎么处理指针?
                // right往右走--扩大窗口, left往右走--缩小窗口
                // right往右会丢掉解,例如--[1,4]和[2,4]均为解,找到[1,4]后right往右,丢掉了[2,4]
                // left往右能会丢掉解,例如--[0,2]和[0,3]均为解,找到[0,2]后left往右,丢掉了[0,3]
                if (curSum == goal) {
                    System.out.println("找到一个结果,下标范围[" + left + "," + right + "]");
                    cnt++;
//                    left++;
                    right++;
                }
                // 和不够,right往右走
                else if (curSum < goal) {
                    right++;
                }
                // 和过大,left往右走
                else {
                    left++;
                }
            }
            return cnt;
        }
    }

    // 哈希表法,分析--
    // [i,j]和为goal, 则 preSum[j+1]-preSum[i]=goal
    // 将preSum存入map,遍历右边界j+1,查找preSum[j+1]-goal的出现次数
    // {0,0,0,0,0}显然不对,因为有出现的前后顺序
    class Solution_wrong2 {
        public int numSubarraysWithSum(int[] nums, int goal) {
            int cnt = 0;
            int len = nums.length;
            int[] preSum = new int[len + 1];
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);// preSum[0]=0,即无前缀
            for (int i = 1; i < len + 1; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
                map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
            }
            System.out.println("map = " + map);
            System.out.println("preSum = " + Arrays.toString(preSum));

            for (int j = 0; j < len; j++) {
                int key = preSum[j + 1] - goal;
                // helper
                System.out.print("preSum[" + (j + 1) + "] = " + preSum[j + 1]);
                System.out.println("  所需的preSum[i]=" + key + " 出现次数=" + map.getOrDefault(key, 0));
                cnt += map.getOrDefault(key, 0);
            }
            return cnt;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 哈希表法--一次遍历
    class Solution {
        public int numSubarraysWithSum(int[] nums, int goal) {
            int sum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            int cnt = 0;
            for (int num : nums) {
                // 更新sum出现次数
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                sum += num;
                cnt += map.getOrDefault(sum - goal, 0);
                // helper
                System.out.print("sum = " + sum + "  目标key = " + (sum - goal));
                System.out.print("  map = " + map);
                System.out.println("  出现次数:" + map.getOrDefault(sum - goal, 0));
            }
            return cnt;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
