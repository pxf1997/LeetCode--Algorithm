/**
 * 题目Id：46
 * 题目：全排列
 * 日期：2021-05-07 17:46:28
 */
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1331 👎 0


package leetcode.editor.cn;

//全排列

import java.util.ArrayList;
import java.util.List;

public class P46_Permutations {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P46_Permutations().new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{1, 2, 3, 4});
        System.out.println("permute = " + permute);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        int depth = 0;

        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<Integer> path = new ArrayList<>();
            boolean[] hasVisited = new boolean[len];
            backtracking(nums, path, hasVisited);
            return res;
        }

        private void backtracking(int[] nums, List<Integer> path, boolean[] visited) {
            if (path.size() == nums.length) {
//                System.out.println("终止条件--找到一个排列:" + path);
//                错误分析----这个地方明明只是引用，我们需要的是其中的在某个特定时期的内容，所以一定要进行拷贝
//                res.add(path);
//                res.add(new ArrayList<>(path));
                List<Integer> now = new ArrayList<>(path);
                res.add(now);
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                //添加
//                depth++;
                visited[i] = true;
                path.add(nums[i]);
//                System.out.print("depth = " + depth);
//                System.out.println("  path = " + path);

                //递归调用
                backtracking(nums, path, visited);

                //删除

                path.remove(path.size() - 1);
                visited[i] = false;

                //                depth--;
//                System.out.print("depth = " + depth);
//                System.out.print("  回溯一哈");
//                System.out.println("  path = " + path);
//                System.out.println();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
