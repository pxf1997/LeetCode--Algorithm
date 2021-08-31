/**
 * 题目Id：542
 * 题目：01 矩阵
 * 日期：2021-04-29 22:55:54
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
// 👍 415 👎 0


package leetcode.editor.cn;

//01 矩阵

import util.dp_util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P542_Zero1Matrix {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P542_Zero1Matrix().new Solution();
//        int[][] updateMatrix = solution.updateMatrix(new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {1, 1, 1, 1}});

//        int[][] updateMatrix = solution.updateMatrix(new int[][]{{1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 1},{1, 1, 1, 1}});
//        int[][] updateMatrix = solution.updateMatrix(new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1},{1, 1, 1, 1}});

        int[][] updateMatrix = solution.updateMatrix(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0},{0, 1, 0},{0, 1, 0},{0, 1, 0}});
        System.out.println("最终结果:");
        dp_util.print_2D(updateMatrix);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //上下左右

        //多源BFS
        public int[][] updateMatrix(int[][] mat) {
//            dp_util.print_2D(mat);
//            System.out.println();

            int m = mat.length, n = mat[0].length;
            boolean[][] mark = new boolean[m][n];
            int[][] res = new int[m][n];
            Queue<int[]> queue = new LinkedList<>();

            // 将所有的 0 添加进初始队列中
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        queue.add(new int[]{i, j}); // 0入队
                        mark[i][j] = true;
                    } else {

                    }
                }
            }
            // 广度优先搜索
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
//                System.out.println("出队坐标 = " + Arrays.toString(cur));

                int x0 = cur[0], y0 = cur[1];
                for (int[] direction : directions) {
                    int x1 = x0 + direction[0], y1 = y0 + direction[1];
                    //判断边界
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                        continue;
                    }
                    if (mark[x1][y1]) {
                        continue;
                    }
//                    res[x1][y1]++; //错啦，应该是以res[x0][y0]基础上再多走一步
                    res[x1][y1] = res[x0][y0] + 1;
                    queue.add(new int[]{x1, y1});
                    mark[x1][y1] = true;

//                    dp_util.print_2D(res);
//                    System.out.println();
                }


            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
