/**
 * 题目Id：剑指 Offer 13
 * 题目：机器人的运动范围
 * 日期：2021-06-16 15:16:43
 */
//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// 👍 298 👎 0


package leetcode.editor.cn;

//机器人的运动范围

public class 剑指Offer_13_机器人的运动范围 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_13_机器人的运动范围().new Solution();
        int movingCount = solution.movingCount(100, 100, 18);
        System.out.println("movingCount = " + movingCount);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 思路--用visited数组记录到过的位置,统计位置之和
    class Solution {
        int m, n;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int movingCount(int m, int n, int k) {
            this.m = m;
            this.n = n;
            boolean[][] visited = new boolean[m][n];

            dfs(0, 0, k, visited);

            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) cnt++;
                }
            }
            return cnt;
        }

        // 注意--排除条件写在前面(递归终止处),四个方向递归深入时不判断
        private void dfs(int i, int j, int k, boolean[][] visited) {
            // 1--递归终止
            // ①坐标越界 ②已经访问过 ③坐标不能通过check
            if (!inArea(i, j)) return;
            if (visited[i][j]) return;
            if (!check(i, j, k)) return;

            visited[i][j] = true;
            // helper
            System.out.println("到达坐标:(" + i + "," + j + ")");
            // 2--递归深入--四个方向,不进行判断
            for (int[] direction : directions) {
                dfs(i + direction[0], j + direction[1], k, visited);
            }
        }

        // 辅助计算函数 [35,37]--3+5+3+7=18
        private boolean check(int i, int j, int k) {
            return check_help(i) + check_help(j) <= k;
        }

        private int check_help(int x) {
            int res = 0;
            while (x != 0) {
                res += x % 10;
                x /= 10;
            }
            return res;
        }

        public boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    // 这题是dfs而不是回溯,分析二者区别
    // 回溯backtracking--关注的是路径,所以回退的时候需要 "复原现场"
    // dfs--深度优先搜索,关注的是探索

}
