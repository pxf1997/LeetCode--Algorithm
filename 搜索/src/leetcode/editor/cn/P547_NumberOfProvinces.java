/**
 * 题目Id：547
 * 题目：省份数量
 * 日期：2021-05-06 17:38:14
 */
//
// 
// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连
//。 
//
// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。 
//
// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 
//isConnected[i][j] = 0 表示二者不直接相连。 
//
// 返回矩阵中 省份 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 200 
// n == isConnected.length 
// n == isConnected[i].length 
// isConnected[i][j] 为 1 或 0 
// isConnected[i][i] == 1 
// isConnected[i][j] == isConnected[j][i] 
// 
// 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 546 👎 0


package leetcode.editor.cn;

//省份数量

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class P547_NumberOfProvinces {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P547_NumberOfProvinces().new Solution();
//        int[][] isConnected = new int[][]{
//                {1, 1, 0, 1},
//                {1, 1, 0, 0},
//                {0, 0, 1, 0},
//                {1, 1, 0, 1},
//        };
        int[][] isConnected = new int[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
        };
        int res = solution.findCircleNum(isConnected);
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    //分析
    //可以把 n 个城市和它们之间的相连关系看成图，
    //城市是图中的节点，相连关系是图中的边，给定的矩阵isConnected 即为图的邻接矩阵，省份即为图中的连通分量。

    // DFS
    class Solution1 {

        int n;

        public int findCircleNum(int[][] isConnected) {
            n = isConnected.length;
//            boolean[] visited = new boolean[n];


            int circleNum = 0;
            HashSet<Integer> seen = new HashSet<Integer>();
            for (int i = 0; i < n; i++) {
//                if (!visited[i]) {
//                    dfs(isConnected, i, visited);
//                    circleNum++;
//                }

                if (!seen.contains(i)) {
                    dfs1(isConnected, i, seen);
                    System.out.println("seen = " + seen);
                    circleNum++;
                }
            }
            return circleNum;

        }

        private void dfs1(int[][] isConnected, int i, HashSet<Integer> seen) {
            seen.add(i);
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && !seen.contains(j)) {
                    dfs1(isConnected, j, seen);
                }
            }
        }

        private void dfs(int[][] isConnected, int i, boolean[] visited) {
            visited[i] = true;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && isConnected[i][j] == 1) {
                    dfs(isConnected, j, visited);
                }
            }
        }
    }

    // BFS
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int provinces = isConnected.length;
            boolean[] visited = new boolean[provinces];
            int circles = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            for (int i = 0; i < provinces; i++) {
                if (!visited[i]) {
                    queue.offer(i);
                    System.out.println("入队=" + i);
                    while (!queue.isEmpty()) {
                        int j = queue.poll();
                        System.out.println("出队=" + j);
                        visited[j] = true;
                        for (int k = 0; k < provinces; k++) {
                            if (isConnected[j][k] == 1 && !visited[k]) {
                                queue.offer(k);
                                System.out.println("入队=" + k);
                            }
                        }
                    }
                    circles++;
                    System.out.println();
                }
            }
            return circles;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
