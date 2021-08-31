/**
 * 题目Id：90
 * 题目：子集 II
 * 日期：2021-05-08 16:59:23
 */
//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
// 
// 
// 
// Related Topics 数组 回溯算法 
// 👍 571 👎 0


package leetcode.editor.cn;

//子集 II

import java.util.*;

public class P90_SubsetsIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P90_SubsetsIi().new Solution();
//        List<List<Integer>> subsetsWithDup = solution.subsetsWithDup(new int[]{1, 2, 2});
        List<List<Integer>> subsetsWithDup = solution.subsetsWithDup(new int[]{4, 4, 4, 1, 4});
        System.out.println("subsetsWithDup = " + subsetsWithDup);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] hasVisited = new boolean[nums.length];

            for (int size = 0; size <= nums.length; size++) {
                System.out.println("size = " + size);
                backtracking(nums, size, 0, res, path, hasVisited);
                System.out.println();
            }
            return res;
        }

        private void backtracking(int[] nums, int k, int begin, List<List<Integer>> res, List<Integer> path, boolean[] hasVisited) {
            if (path.size() == k) {
                System.out.println("递归结束:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < nums.length; i++) {
                // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
                if (i != 0 && nums[i] == nums[i - 1] && !hasVisited[i - 1]) {
                    continue;
                }

                //添加
                path.add(nums[i]);
                hasVisited[i] = true;
                System.out.println("递归之前 => " + path + "  剩余个数:" + (k - path.size()));

                //不允许重复使用
                backtracking(nums, k, i + 1, res, path, hasVisited);

                //删除
                path.remove(path.size() - 1);
                hasVisited[i] = false;
                System.out.println("递归之后 => " + path);

            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
