/**
 * 题目Id：47
 * 题目：全排列 II
 * 日期：2021-06-24 14:04:23
 */
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯算法 
// 👍 734 👎 0


package leetcode.editor.cn;

//全排列 II

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47_PermutationsIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P47_PermutationsIi().new Solution();
        List<List<Integer>> permute = solution.permuteUnique(new int[]{1, 1, 3});
        System.out.println("permute = " + permute);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[len];
            Arrays.sort(nums);
            backtracking(nums, visited, path);
            return res;
        }

        private void backtracking(int[] nums, boolean[] visited, List<Integer> path) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) continue;
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

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
