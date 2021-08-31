/**
 * 题目Id：46
 * 题目：全排列
 * 日期：2021-06-24 14:00:10
 */
//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯算法 
// 👍 1420 👎 0


package leetcode.editor.cn;

//全排列

import java.util.ArrayList;
import java.util.List;

public class P46_Permutations {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P46_Permutations().new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{1, 2, 3});
        System.out.println("permute = " + permute);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        // 题目条件--无重复
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[len];
            backtracking(nums, visited, path);
            return res;
        }

        private void backtracking(int[] nums, boolean[] visited, List<Integer> path) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) continue;
                path.add(nums[i]);
                visited[i] = true;

                backtracking(nums, visited, path);

                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
