/**
 * 题目Id：216
 * 题目：组合总和 III
 * 日期：2021-05-08 14:26:09
 */
//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯算法 
// 👍 302 👎 0


package leetcode.editor.cn;

//组合总和 III

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P216_CombinationSumIii {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P216_CombinationSumIii().new Solution();
        List<List<Integer>> res = solution.combinationSum3(3, 9);
//        List<List<Integer>> res = solution.combinationSum3(2, 18);
        System.out.println("输出 => " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //组合 用begin
        public List<List<Integer>> combinationSum3(int k, int n) { // n 可以理解为一个数组nums={1,2,....n}
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            int[] nums = new int[9];
            int target = n;
            //不用排序nums数组哦
            for (int i = 0; i < 9; i++) {
                nums[i] = i + 1;
            }
            System.out.println("nums = " + Arrays.toString(nums));

            backtracking(k, target, nums, 0, res, path);
            return res;


        }

        /**
         * @param k      个数限制
         * @param target 目标总和
         * @param nums   候选数组
         * @param begin  起始下标
         * @param res    由若干路径组成的返回列表(带着传参)
         * @param path   一条路径
         */
        private void backtracking(int k, int target, int[] nums, int begin, List<List<Integer>> res, List<Integer> path) {
            //结束条件:选够个数 or 数组用光

//            if (k == 0 || begin == nums.length) {
//                if (target == 0 && k == 0) {
////                if (target == 0) { //错误案例  用一个9去组成9
//                    res.add(new ArrayList<>(path));
//                }
//                return;
//            }
            if (k == 0 && target == 0) {
                System.out.println("递归终止----成功找到一个path:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
            if (k == 0 || target == 0) {
                System.out.println("递归终止----不成功");
                return;
            }

            for (int i = begin; i < nums.length; i++) {
                //剪枝
                if (target - nums[i] < 0) {
                    break;
                }
                path.add(nums[i]);
                System.out.println("递归之前 => " + path + "  剩余目标 = " + (target - nums[i]) + "  剩余个数 = " + (k - 1));

                //元素不能重复使用
                backtracking(k - 1, target - nums[i], nums, i + 1, res, path);

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path + "  剩余个数 = " + k);
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
