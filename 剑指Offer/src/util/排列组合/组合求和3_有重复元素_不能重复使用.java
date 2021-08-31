package util.排列组合;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 14:35
 */
public class 组合求和3_有重复元素_不能重复使用 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //直观理解----不能区分重复的 1  {1,7}、{1,7}视为一种
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> res = solution.combinationSum2(candidates, 8);
        System.out.println("输出 => " + res);
    }

    static class Solution {
        int depth;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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

//            理解：对照树形图----在一个for循环中，所有被遍历到的数都是属于一个层级的
            for (int i = startIndex; i < candidates.length; i++) {

                // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
                if (target - candidates[i] < 0) {
                    break;
                }

                // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
                if (i > startIndex && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                path.add(candidates[i]);
                System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

                // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
                backtracking(candidates, target - candidates[i], i + 1, path, res);

                // 状态重置
                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
            }

        }
    }
}
