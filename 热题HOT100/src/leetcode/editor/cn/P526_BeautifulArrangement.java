/**
 * 题目Id：526
 * 题目：优美的排列
 * 日期：2021-08-16 14:10:02
 */
//假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
//我们就称这个数组为一个优美的排列。条件： 
//
// 
// 第 i 位的数字能被 i 整除 
// i 能被第 i 位上的数字整除 
// 
//
// 现在给定一个整数 N，请问可以构造多少个优美的排列？ 
//
// 示例1: 
//
// 
//输入: 2
//输出: 2
//解释: 
//
//第 1 个优美的排列是 [1, 2]:
//  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
//
//第 2 个优美的排列是 [2, 1]:
//  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
// 
//
// 说明: 
//
// 
// N 是一个正整数，并且不会超过15。 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 184 👎 0


package leetcode.editor.cn;

//优美的排列

import java.util.ArrayList;
import java.util.List;

public class P526_BeautifulArrangement {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P526_BeautifulArrangement().new Solution();
        solution.countArrangement(2);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案--回溯
    class Solution {
        List<Integer>[] match;
        boolean[] visited; // 记录哪些数被使用过
        int count;

        public int countArrangement(int n) {
            visited = new boolean[n + 1];
            match = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                match[i] = new ArrayList<Integer>();
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i % j == 0 || j % i == 0) {
                        match[i].add(j);
                    }
                }
            }
            backtrack(1, n);
            return count;
        }

        public void backtrack(int index, int n) {
            // 装满了
            if (index == n + 1) {
                count++;
                return;
            }
            for (int x : match[index]) {
                if (!visited[x]) {
                    visited[x] = true;
                    backtrack(index + 1, n);
                    visited[x] = false;
                }
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
