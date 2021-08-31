/**
 * 题目Id：40
 * 题目：组合总和 II
 * 日期：2021-06-30 10:04:08
 */
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯 
// 👍 615 👎 0


package leetcode.editor.cn;

//组合总和 II

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P40_CombinationSumIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P40_CombinationSumIi().new Solution();
        // 理解--「有重复元素+不能重复使用」
        // 如下例,不能区分重复的{3,3,3} 但依然可以用{3,3,3}组成9
        List<List<Integer>> res = solution.combinationSum2(new int[]{2, 2, 2, 3, 3, 3, 6, 6, 7}, 9);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<Integer> path = new ArrayList<>();
            backtracking(candidates, 0, target, path);
            return res;
        }

        // 对target做减法
        private void backtracking(int[] candidates, int beginIndex, int target, List<Integer> path) {
            // 1--递归终止
            if (target == 0) {
                System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
            // 注意for循环里面----写下标i而不是beginIndex
            for (int i = beginIndex; i < candidates.length; i++) {
                // 剪枝--配合排序使用
                if (target - candidates[i] < 0) break;
                // 去重
                if (i > beginIndex && candidates[i] == candidates[i - 1]) continue;

                // 2--回溯套路
                path.add(candidates[i]);
                System.out.println("递归之前 => " + path + "  剩余:" + (target - candidates[i]));

                // 从 i 开始找--可以使用重复元素
                // 从 i+1 开始找--不能重复
                backtracking(candidates, i + 1, target - candidates[i], path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
