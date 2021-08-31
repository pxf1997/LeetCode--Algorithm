/**
 * 题目Id：542
 * 题目：01 矩阵
 * 日期：2021-04-29 16:28:12
 */
//给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。 
//
// 两个相邻元素间的距离为 1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：
//[[0,0,0],
// [0,1,0],
// [0,0,0]]
//
//输出：
//[[0,0,0],
// [0,1,0],
// [0,0,0]]
// 
//
// 示例 2： 
//
// 
//输入：
//[[0,0,0],
// [0,1,0],
// [1,1,1]]
//
//输出：
//[[0,0,0],
// [0,1,0],
// [1,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 给定矩阵的元素个数不超过 10000。 
// 给定矩阵中至少有一个元素是 0。 
// 矩阵中的元素只在四个方向上相邻: 上、下、左、右。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 414 👎 0


package leetcode.editor.cn;

//01 矩阵

import util.dp_util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P542_Zero1Matrix_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P542_Zero1Matrix_old().new Solution();

        int[][] updateMatrix = solution.updateMatrix(new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {1, 1, 1, 1}});
//        int[][] updateMatrix = solution.updateMatrix(new int[][]{{1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 1},{1, 1, 1, 1}});
//        int[][] updateMatrix = solution.updateMatrix(new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1},{1, 1, 1, 1}});
        System.out.println("最终结果:");
        dp_util.print_2D(updateMatrix);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //结果正确,超时
    class Solution1 {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //上下左右！ 第一个数移动x 第二个数移动y

        //最短距离--BFS-最短距离
        public int[][] updateMatrix(int[][] mat) {
            int m = mat.length, n = mat[0].length;//m行 n列
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 拿1取找0--需改进！
                    if (mat[i][j] == 1) {
                        res[i][j] = MinDistance_0(i, j, mat);
                    } else {
                        res[i][j] = 0;
                    }
                }
            }
            return res;

        }

        private int MinDistance_0(int x, int y, int[][] mat) {
            int m = mat.length, n = mat[0].length;//m行 n列
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] marked = new boolean[m][n];
            int path = 0;//理解为层数--放进去的第一个节点层数为1
            queue.add(new int[]{x, y});

            while (!queue.isEmpty()) {
                int size = queue.size();
                path++;
                while (size-- > 0) {
                    int[] curp = queue.poll();
                    int x0 = curp[0], y0 = curp[1];
                    for (int[] direction : directions) {
                        int x1 = x0 + direction[0], y1 = y0 + direction[1];
                        //两个continue条件谁前谁后--一定先判断index是否越界！！！！！
                        if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                            continue;
                        }
                        if (marked[x1][y1]) {
                            continue;
                        }
                        queue.add(new int[]{x1, y1});
                        marked[x1][y1] = true;
                        if (mat[x1][y1] == 0) {
                            return path;
                        }
                    }
                }
            }
            return -1;
        }
    }

    //多源BFS
    class Solution {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int[][] updateMatrix(int[][] matrix) {
            dp_util.print_2D(matrix);
            System.out.println();

            int m = matrix.length, n = matrix[0].length;
            int[][] dist = new int[m][n];
            boolean[][] seen = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<int[]>();
            // 将所有的 0 添加进初始队列中
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                        seen[i][j] = true;
                    }
                }
            }
            //初始所有的 0节点--seen为true  1节点--seen为false

            // 广度优先搜索
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                System.out.println("出队坐标 = " + Arrays.toString(cell));
                int i = cell[0], j = cell[1];
                for (int d = 0; d < 4; ++d) {
                    int ni = i + dirs[d][0];
                    int nj = j + dirs[d][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                        dist[ni][nj] = dist[i][j] + 1; //此处不需要 path 记录路径长度了
                        queue.offer(new int[]{ni, nj});
                        seen[ni][nj] = true;

                        dp_util.print_2D(dist);
                        System.out.println();
                    }
                }
            }

            return dist;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
