/**
 * 题目Id：39
 * 题目：组合总和
 * 日期：2021-06-30 09:46:24
 */
//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1406 👎 0


package leetcode.editor.cn;

//组合总和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P39_CombinationSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P39_CombinationSum().new Solution();
        List<List<Integer>> res = solution.combinationSum(new int[]{2, 3, 6, 7}, 9);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--组合求和,可以重复使用某个元素!
    // 排列--用visited 组合--用beginIndex
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
//                target -= candidates[i]; // 写在这里是错误的!!!

                // 2--回溯套路
                path.add(candidates[i]);
                System.out.println("递归之前 => " + path + "  剩余:" + (target - candidates[i]));

                // 从 i 开始找--可以使用重复元素
                // 从 i+1 开始找--不能重复
                backtracking(candidates, i, target - candidates[i], path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
