/**
 * 题目Id：847
 * 题目：访问所有节点的最短路径
 * 日期：2021-08-06 10:10:18
 */
//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。 
//
// 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。 
//
// 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 12 
// 0 <= graph[i].length < n 
// graph[i] 不包含 i 
// 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a 
// 输入的图总是连通图 
// 
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 
// 👍 161 👎 0


package daily_2021_08;

//访问所有节点的最短路径

import java.util.LinkedList;
import java.util.Queue;

public class P847_ShortestPathVisitingAllNodes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P847_ShortestPathVisitingAllNodes().new Solution();
    }

    //力扣代码
    // 困难题,还他妈是图,不挣扎了!
    class Solution {
        public int shortestPathLength(int[][] graph) {
            int n = graph.length;
            Queue<int[]> queue = new LinkedList<int[]>();
            boolean[][] seen = new boolean[n][1 << n];
            for (int i = 0; i < n; ++i) {
                queue.offer(new int[]{i, 1 << i, 0});
                seen[i][1 << i] = true;
            }

            int ans = 0;
            while (!queue.isEmpty()) {
                int[] tuple = queue.poll();
                int u = tuple[0], mask = tuple[1], dist = tuple[2];
                if (mask == (1 << n) - 1) {
                    ans = dist;
                    break;
                }
                // 搜索相邻的节点
                for (int v : graph[u]) {
                    // 将 mask 的第 v 位置为 1
                    int maskV = mask | (1 << v);
                    if (!seen[v][maskV]) {
                        queue.offer(new int[]{v, maskV, dist + 1});
                        seen[v][maskV] = true;
                    }
                }
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
