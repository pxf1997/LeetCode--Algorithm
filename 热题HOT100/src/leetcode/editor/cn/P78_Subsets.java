/**
 * 题目Id：78
 * 题目：子集
 * 日期：2021-08-24 22:53:44
 */
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 👍 1293 👎 0


package leetcode.editor.cn;

//子集

import java.util.ArrayList;
import java.util.List;

public class P78_Subsets {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P78_Subsets().new Solution();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = solution.subsets(nums);
        System.out.println("res = " + res);
    }


    class Solution1 {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            int len = nums.length;
            // 解法--生成长度为[0,len]长度的组合
            for (int i = 0; i <= len; i++) {
                List<Integer> path = new ArrayList<>();
                System.out.println("组合长度 : " + i);
                backtracking(nums, path, 0, i);
                System.out.println();
            }
            return res;
        }

        private void backtracking(int[] nums, List<Integer> path, int begin, int limit) {
            if (path.size() == limit) {
                System.out.println("path = " + path);
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < nums.length; i++) {
                path.add(nums[i]);

                // i--可重复使用  / i+1--不可重复使用
                backtracking(nums, path, i + 1, limit);
//                backtracking(nums, path, i, limit);

                path.remove(path.size() - 1);
            }
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法2--二进制运算
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            int len = nums.length;
            // {1,2,3} 1<<3=8 即 000--111 (0到7)
            for (int mask = 0; mask < (1 << len); mask++) {
                path.clear();
                for (int i = 0; i < len; i++) {
                    // 第i位为1,则添加对应元素
                    if ((mask & (1 << i)) != 0) {
                        path.add(nums[i]);
                    }
                }
                // helper
//                System.out.println("mask = " + mask);
//                System.out.println("path = " + path);
//                System.out.println();
                res.add(new ArrayList<>(path));
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
