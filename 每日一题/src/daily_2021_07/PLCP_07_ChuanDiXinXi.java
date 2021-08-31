/**
 * 题目Id：LCP 07
 * 题目：传递信息
 * 日期：2021-07-01 09:55:48
 */
//小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下： 
//
// 
// 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0 
// 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。 
// 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人 
// 
//
// 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号
//为 n-1 的小伙伴处的方案数；若不能到达，返回 0。 
//
// 示例 1： 
//
// 
// 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3 
//
// 输出：3 
//
// 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->
//3->4。 
// 
//
// 示例 2： 
//
// 
// 输入：n = 3, relation = [[0,2],[2,1]], k = 2 
//
// 输出：0 
//
// 解释：信息不能从小 A 处经过 2 轮传递到编号 2 
// 
//
// 限制： 
//
// 
// 2 <= n <= 10 
// 1 <= k <= 5 
// 1 <= relation.length <= 90, 且 relation[i].length == 2 
// 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1] 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划 
// 👍 79 👎 0


package daily_2021_07;

//传递信息

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PLCP_07_ChuanDiXinXi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PLCP_07_ChuanDiXinXi().new Solution();
        int res = solution.numWays(5, new int[][]{
                {0, 2},
                {2, 1},
                {3, 4},
                {2, 3},
                {1, 4},
                {2, 0},
                {0, 4},
        }, 3);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // my--bfs
    class Solution1 {
        int cnt = 0;

        public int numWays(int n, int[][] relation, int k) {
            int level = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            while (!queue.isEmpty()) {
                int size = queue.size();
                level++; // 这样写,层数level从1开始算
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    if (cur == n - 1 && level == k + 1) cnt++;
                    // 根据边的关系图,入队下一个节点,不设置visited
                    // ints结构 {起点, 终点}
                    for (int[] ints : relation) {
                        if (ints[0] == cur) {
                            queue.add(ints[1]);
                            System.out.println("level = " + level + "  入队:" + ints[1]);
                        }
                    }
                }
                // 只统计到 k 层
                if (level == k + 1) break;
            }
            return cnt;
        }
    }

    // 参考--bfs
    class Solution {
        public int numWays(int n, int[][] relation, int k) {
            // 构建邻接表
            List<List<Integer>> edges = new ArrayList<List<Integer>>();
            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<Integer>());
            }
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                edges.get(src).add(dst);
            }

            System.out.println("edges = " + edges);

            int steps = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(0);
            while (!queue.isEmpty() && steps < k) {
                steps++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int index = queue.poll();
                    List<Integer> list = edges.get(index);
                    for (int nextIndex : list) {
                        queue.offer(nextIndex);
                    }
                }
            }

            int ways = 0;
            if (steps == k) {
                while (!queue.isEmpty()) {
                    if (queue.poll() == n - 1) {
                        ways++;
                    }
                }
            }
            return ways;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
