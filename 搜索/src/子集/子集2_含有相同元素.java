package 子集;

import leetcode.editor.cn.P90_SubsetsIi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 15:29
 */
public class 子集2_含有相同元素 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<List<Integer>> subsetsWithDup = solution.subsetsWithDup(new int[]{1, 2, 2});
        List<List<Integer>> subsetsWithDup = solution.subsetsWithDup(new int[]{4, 4, 4, 1, 4});
        System.out.println("subsetsWithDup = " + subsetsWithDup);
    }
    static class Solution {
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
}

