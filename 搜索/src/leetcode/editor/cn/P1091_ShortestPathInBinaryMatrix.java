/**
 * 题目Id：1091
 * 题目：二进制矩阵中的最短路径
 * 日期：2021-04-28 14:26:45
 */
//给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。 
//
// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求
//： 
//
// 
// 路径途经的所有单元格都的值都是 0 。 
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。 
// 
//
// 畅通路径的长度 是该路径途经的单元格总数。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[0,1],[1,0]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 广度优先搜索 
// 👍 99 👎 0


package leetcode.editor.cn;

//二进制矩阵中的最短路径

import java.util.LinkedList;
import java.util.Queue;

public class P1091_ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1091_ShortestPathInBinaryMatrix().new Solution();
        int res = solution.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}});
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }
//             如果起点就阻塞那就玩完啦
            if (grid[0][0] == 1) {
                return -1;
            }
//            定义 8个方向
            int[][] dir = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1},};
//            m行数，n列数
            int m = grid.length, n = grid[0].length;

//            !!!!!---bfs的老套路 来个队列 入队的是 int[2]--坐标
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.add(new int[]{0, 0}); //把起点扔进去
            grid[0][0] = 1; //标记起点为阻塞
            int path = 0; //层数

            while (!queue.isEmpty()) {
                int size = queue.size();
                path++;
                while (size-- > 0) { //体会size-- 每批出队size个，即一层的个数（每一层节点的path值相同），当然过程中发生下一层入队，咱不管
                    int[] cur = queue.poll();
//                    size--;//放这里也可以
                    int x = cur[0];
                    int y = cur[1];
//                    能放进队列里的都是为0可以走的（这一点在后面保证了）
//                    如果等于终点则返回
                    if (x == m - 1 && y == n - 1) {
                        return path;
                    }
//                    开始八个方向的判断
                    for (int[] direction : dir) {
                        int x1 = x + direction[0];
                        int y1 = y + direction[1];
//                        这里开始过滤
//                        注意先判断下标x1 y1是否越界、再判断grid[x1][y1]中是否阻塞
                        if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || grid[x1][y1] == 1) {
                            continue; // 不同于break，它跳过此direction找下一个
                        }
                        //把在数组范围内 并且为0不阻塞的放入队列中
                        queue.add(new int[]{x1, y1});
                        grid[x1][y1] = 1;//标记
                    }
//                    size--;//放这里也可以
                }
            }
            return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
