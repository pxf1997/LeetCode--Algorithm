/**
 * 题目Id：39
 * 题目：组合总和
 * 日期：2021-05-08 10:24:56
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
// Related Topics 数组 回溯算法 
// 👍 1328 👎 0


package 排列组合;

//组合总和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P39_CombinationSum {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P39_CombinationSum().new Solution();
        int[] candidates = {2, 3, 6, 7};
//        int[] candidates = {2, 7, 6, 3, 5, 1};
        List<List<Integer>> res = solution.combinationSum(candidates, 9);
        System.out.println("输出 => " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    //my----做加法
    class Solution1 {
        int depth;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
//            Arrays.sort(candidates);
            List<List<Integer>> res = new ArrayList<>();
            if (candidates.length == 0) {
                return res;
            }
            List<Integer> path = new ArrayList<>();
            backtracking1(candidates, target, 0, path, res);
            return res;

        }

        private void backtracking1(int[] candidates, int target, int startIndex, List<Integer> path, List<List<Integer>> res) {
            int sum = sumList(path);
            // 递归终止条件是：path总和 等于 target
            if (sum > target) {
                System.out.println("递归终止--当前总和已经大于target : " + path);
                return;
            }
            if (sum == target) {
                System.out.println("递归终止--找到结果:" + path);
                res.add(new ArrayList<Integer>(path));
                return;
            }
            // 遍历可能的搜索起点
            for (int i = startIndex; i < candidates.length; i++) { //i=0 可以使用重复元素
                //剪枝----需要对原数组排序
                if (sum + candidates[i] > target) {
                    break;
                }

                path.add(candidates[i]);
                depth++;
                System.out.println("深度:" + depth + "  递归之前 => " + path);


                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
                backtracking1(candidates, target, i, path, res);

                // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
                path.remove(path.size() - 1);
                depth--;
                System.out.println("深度:" + depth + "  递归之后 => " + path);

            }
        }

        private int sumList(List<Integer> path) {
            int sum = 0;
            for (Integer a : path) {
                sum += a;
            }
            return sum;
        }
    }

    //参考答案--做减法
    class Solution {
        int depth;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            // 排序是剪枝的前提
            Arrays.sort(candidates);

            List<Integer> path = new ArrayList<>();
            backtracking(candidates, target, 0, path, res);
            return res;


        }

        private void backtracking(int[] candidates, int target, int startIndex, List<Integer> path, List<List<Integer>> res) {
            // target 为负数和 0 的时候不再产生新的孩子结点

//            if (target < 0) { //剪枝后不会出现此情况
//                return;
//            }

            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            // 重点理解这里从 begin 开始搜索的语意
            // 理解：对照树形图----在一个for循环中，所有被遍历到的数都是属于一个层级的
            for (int i = startIndex; i < candidates.length; i++) {
                // 重点理解这里剪枝，前提是候选数组已经有序，
                if (target - candidates[i] < 0) {
                    break;
                }

                path.add(candidates[i]);
                System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
                backtracking(candidates, target - candidates[i], i, path, res);
//                backtracking(candidates, target - candidates[i], i + 1, path, res);

                // 状态重置
                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }


        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
