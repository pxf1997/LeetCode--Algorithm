package util.排列组合;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 13:58
 */
public class 组合1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<List<Integer>> res = solution.combine(5, 5);
//        List<List<Integer>> res = solution.combine(4, 2);
        List<List<Integer>> res = solution.combine(5, 2);
        System.out.println("res = " + res);
    }

    private static class Solution {
        /**
         * @return 在序列{1,2,...n}中选取k个元素的组合
         */
        public List<List<Integer>> combine(int n, int k) {
            // 1--准备提工作
            List<List<Integer>> res = new ArrayList<>();
            if (n < k || k <= 0) {
                return res;
            }
            List<Integer> path = new ArrayList<>();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
            }
            // helper
            System.out.println("nums = " + Arrays.toString(nums));
            System.out.println("k = " + k);
            // 2--回溯
            backtracking(nums, k, 0, res, path);
            return res;
        }

        private void backtracking(int[] nums, int k, int begin, List<List<Integer>> res, List<Integer> path) {
            if (path.size() == k) {
                System.out.println("递归终止:" + path);
                res.add(new ArrayList<>(path));
                return;
            }
//            for (int i = begin; i < nums.length - (k - path.size()) + 1; i++) { // 可以剪枝
            for (int i = begin; i < nums.length; i++) {
                path.add(nums[i]);
                System.out.println("递归之前 => " + path);

//                backtracking(nums, k, i, res, path);//可重复
                backtracking(nums, k, i + 1, res, path);//不可重复

                path.remove(path.size() - 1);
                System.out.println("递归之后 => " + path);
            }
        }
    }
}
