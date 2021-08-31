/**
 * 题目Id：47
 * 题目：全排列 II
 * 日期：2021-05-07 18:14:38
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
// Related Topics 回溯算法 
// 👍 689 👎 0


package leetcode.editor.cn;

//全排列 II

import java.util.*;

public class P47_PermutationsIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P47_PermutationsIi().new Solution();
        List<List<Integer>> permute = solution.permuteUnique(new int[]{1, 1, 2});
        System.out.println("permute = " + permute);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //my 用HashSet判断非重复--相当于开挂
    class Solution {
        Set<List<Integer>> res = new HashSet<>(); //全局的感觉，solution的属性

        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[len];
            backtracking(nums, path, visited);
            return new ArrayList<>(res);
        }

        private void backtracking(int[] nums, List<Integer> path, boolean[] visited) {
            if (path.size() == nums.length) { //set包治百病
                List<Integer> now = new ArrayList<>(path);
                System.out.println("path = " + path);
                res.add(now);
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                path.add(nums[i]);

                backtracking(nums, path, visited);

                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    //排序+数组元素判断非重复
    class Solution1 {
        List<List<Integer>> res = new ArrayList<>(); //全局的感觉，solution的属性

        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[len];
            backtracking(nums, path, visited);
            return res;
        }

        private void backtracking(int[] nums, List<Integer> path, boolean[] visited) {
            if (path.size() == nums.length) {
                List<Integer> now = new ArrayList<>(path);
                System.out.println("path = " + path);
                res.add(now);
            }
            for (int i = 0; i < nums.length; i++) {
                if (i != 0 && (nums[i] == nums[i - 1]) && !visited[i - 1]) {
                    continue;
                }
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                path.add(nums[i]);

                backtracking(nums, path, visited);

                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
