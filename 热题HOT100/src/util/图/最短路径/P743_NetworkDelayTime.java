/**
 * 题目Id：743
 * 题目：网络延迟时间
 * 日期：2021-08-02 10:10:59
 */
//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 
// 👍 320 👎 0


package util.图.最短路径;

//网络延迟时间

import java.util.Arrays;

public class P743_NetworkDelayTime {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P743_NetworkDelayTime().new Solution();
        // case1--有向图
        int[][] times = new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1},
        };
        int n = 4, k = 2;

        // case2--无向图
        int res = solution.networkDelayTime(times, n, k);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--Dijkstra,经典算法咯
    // 基于贪心思想--未确定节点/已确定节点
    class Solution {
        public int networkDelayTime(int[][] times, int n, int k) {
            // 方便判断最后结果--是否都访问到
            final int INF = Integer.MAX_VALUE / 2;
            // 构建邻接矩阵--有向图
            int[][] g = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(g[i], INF);
            }
            // 节点编号减1,范围为[0,n-1]
            for (int[] time : times) {
                int x = time[0] - 1, y = time[1] - 1;
                g[x][y] = time[2];
            }
            int[] dist = new int[n];
            Arrays.fill(dist, INF);
            dist[k - 1] = 0;
            boolean[] used = new boolean[n];
            for (int i = 0; i < n; i++) {
                int x = -1;
                // 1--每次从"未确定节点"中选一个与起点距离最短的点x
                for (int y = 0; y < n; y++) {
                    // 细节--(x == -1 || dist[y] < dist[x])的逻辑:若x未确定(-1),不判断dist[-1]
                    if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                        x = y;
                    }
                }
                System.out.println("选出的节点为:" + x);
                // 2--将x归类为"已确定节点"
                used[x] = true;
                // 3--用x"更新"距离
                for (int y = 0; y < n; y++) {
                    dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
                }
            }
            int ans = Arrays.stream(dist).max().getAsInt();
            return ans == INF ? -1 : ans;
        }
    }

    // 重新练习--Dijkstra
    class Solution_exer {
        public int networkDelayTime(int[][] times, int n, int k) {
            final int INF = Integer.MAX_VALUE / 2;
            // 邻接矩阵
            int[][] adj = new int[n][n];
            for (int[] ints : adj) {
                Arrays.fill(ints, INF);
            }
            for (int[] time : times) {
                int from = time[0] - 1, to = time[1] - 1, weight = time[2];
                adj[from][to] = weight;
            }
            // 距离数组dist
            int[] dist = new int[n];
            boolean[] used = new boolean[n];
            Arrays.fill(dist, INF);
            dist[k - 1] = 0;
            for (int i = 0; i < n; i++) {
                // 1--每次从"未确定节点"中选一个与起点距离最短的点x
                int x = -1;
                for (int y = 0; y < n; y++) {
                    // 第一轮选出来的一定是起点
                    if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                        x = y;
                    }
                }
                // 2--标记x为使用过
                used[x] = true;
                // 3--用x更新距离
                for (int y = 0; y < n; y++) {
                    dist[y] = Math.min(dist[y], dist[x] + adj[x][y]);
                }
            }
            int max = Arrays.stream(dist).max().getAsInt();
            return max == INF ? -1 : max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
