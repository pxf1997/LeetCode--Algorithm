/**
 * 题目Id：773
 * 题目：滑动谜题
 * 日期：2021-06-26 22:07:10
 */
//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示. 
//
// 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 示例： 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//输入：board = [[3,2,4],[1,5,0]]
//输出：14
// 
//
// 提示： 
//
// 
// board 是一个如上所述的 2 x 3 的数组. 
// board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列. 
// 
// Related Topics 广度优先搜索 数组 矩阵 
// 👍 196 👎 0


package daily_2021_06;

//滑动谜题

import java.util.*;

public class P773_SlidingPuzzle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P773_SlidingPuzzle().new Solution();
        int puzzle = solution.slidingPuzzle(new int[][]{
                {1, 2, 3},
                {4, 0, 5}
        });
//        int puzzle = solution.slidingPuzzle(new int[][]{
//                {1, 2, 3},
//                {5, 4, 0}
//        });
        System.out.println("puzzle = " + puzzle);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 思路的对的,错在哪里--Set无法判断重复的二维数组!!!
    // 按理说,六个元素的排列最多6!=720
    class Solution {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] end = {{1, 2, 3}, {4, 5, 0}};
        int m, n;

        public int slidingPuzzle(int[][] board) {
            if (Arrays.deepEquals(board, end)) {
                return 0;
            }
            m = board.length;
            n = board[0].length;

//            Set<int[][]> visited = new HashSet<>(); // set大小最多 6!=720
            Set<String> visited = new HashSet<>(); // set大小最多 6!=720
            Deque<int[][]> queue = new LinkedList<>();
            // board理解为start,起始节点
            queue.offer(board);
            visited.add(makeString_helper(board));
            int level = 0;
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[][] cur = queue.poll();
                    List<int[][]> next_list = next_helper(cur);
                    // heler
                    System.out.println("level = " + level + "  出队:" + Arrays.deepToString(cur));
                    for (int[][] next : next_list) {
                        // 没访问过此节点
                        if (!visited.contains(makeString_helper(next))) {
                            if (Arrays.deepEquals(next, end)) return level;
                            visited.add(makeString_helper(next));
                            queue.offer(next);
                            System.out.println("入队:" + Arrays.deepToString(next));
                        }
                    }
                    System.out.println();
                }
            }
            return -1;
        }

        // 二维数组变字符串存储,方便set判断
        private String makeString_helper(int[][] board) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
            }
            return sb.toString();
        }

        private List<int[][]> next_helper(int[][] cur) {
            List<int[][]> res = new ArrayList<>();
            // 定位0元素位置 + 进行交换
            int[] idx_0 = new int[2]; //[行号,列号]
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (cur[i][j] == 0) idx_0 = new int[]{i, j};
                }
            }
            // 四个可行方向,返回可行交换结果
            for (int[] direction : directions) {
                // [i,j]为0的原坐标, [x1,y1]为0交换后的坐标
                int x1 = idx_0[0] + direction[0], y1 = idx_0[1] + direction[1];
                if (inArea(x1, y1)) {
                    int[][] swap = swap(idx_0[0], idx_0[1], x1, y1, cur);
                    res.add(swap);
                }
            }
            return res;
        }

        // 交换并造新的对象!
        private int[][] swap(int x0, int y0, int x1, int y1, int[][] cur) {
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == x0 && j == y0) res[i][j] = cur[x1][y1];
                    else if (i == x1 && j == y1) res[i][j] = cur[x0][y0];
                    else res[i][j] = cur[i][j];
                }
            }
            return res;
        }

        // 坐标合法
        boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
