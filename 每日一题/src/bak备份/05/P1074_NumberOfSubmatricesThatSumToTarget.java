/**
 * 题目Id：1074
 * 题目：元素和为目标值的子矩阵数量
 * 日期：2021-05-29 20:51:40
 */
//给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。 
//
// 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。 
//
//
// 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵
//也不同。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//输出：4
//解释：四个只含 0 的 1x1 子矩阵。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,-1],[-1,1]], target = 0
//输出：5
//解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[904]], target = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= matrix.length <= 100 
// 1 <= matrix[0].length <= 100 
// -1000 <= matrix[i] <= 1000 
// -10^8 <= target <= 10^8 
// 
// Related Topics 数组 动态规划 Sliding Window 
// 👍 146 👎 0


//元素和为目标值的子矩阵数量

import java.util.Arrays;
import java.util.HashMap;

public class P1074_NumberOfSubmatricesThatSumToTarget {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1074_NumberOfSubmatricesThatSumToTarget().new Solution();
//        int res = solution.numSubmatrixSumTarget(new int[][]{
//                {1, -1},
//                {-1, 1},
//        }, 0);
        int res = solution.numSubmatrixSumTarget(new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0},
        }, 3);
        System.out.println("res = " + res);
    }


    //  纯暴力法---可想而知的超时  时间复杂度O(n^6) 就nm离谱
    //  ----应该利用已经计算过的结论避免重复计算,这就是dp的核心原理
    class Solution1 {
        int[][] matrix;
        int m, n;

        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            this.matrix = matrix;
            m = matrix.length;
            n = matrix[0].length;
            // 遍历 起点x1,y1
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int[] start = new int[]{i, j};
                    // addx 最大为 m-1-i
                    for (int addx = 0; addx <= m - 1 - i; addx++) {
                        for (int addy = 0; addy <= n - 1 - j; addy++) {
                            int[] end = new int[]{i + addx, j + addy};
                            if (computeAreaSum(start, end) == target) {
                                System.out.println("找到一个结果--起点:" + Arrays.toString(start) +
                                        " 终点:" + Arrays.toString(end));
                                System.out.println();
                                res++;
                            }
                        }
                    }
                }
            }
            return res;
        }

        int computeAreaSum(int[] a, int[] b) {
            int x1 = a[0], y1 = a[1];
            int x2 = b[0], y2 = b[1];
            int sum = 0;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    sum += matrix[i][j];
                }
            }
            return sum;
        }
    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 前缀和 + 哈希表
    class Solution {
        int[][] matrix;
        int m, n;

        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            this.matrix = matrix;
            m = matrix.length;
            n = matrix[0].length;
            // dp[i][j]----遍历到矩阵下标[i,j]处的可行方案数
            // ---不好因为你还要去求和--势必O(n^2)
            int total = 0;
            for (int i = 0; i < m; i++) { // 枚举上边界
                int[] cur = new int[n];
                for (int j = i; j < m; j++) { // 枚举下边界
                    for (int col = 0; col < n; col++) {
                        cur[col] += matrix[j][col];  // 更新每列的元素和
                    }
                    int x = subarraySum(cur, target);
//                    System.out.println(i + "到" + j + "行累加数组cur = " + Arrays.toString(cur));
//                    System.out.println("x = " + x);
//                    System.out.println();
                    total += x;
                }
            }

            return total;
        }

        public int subarraySum(int[] nums, int k) {
            int cnt = 0, preSum = 0;
            // key--前缀和  value--此前缀和出现次数
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                preSum += nums[i];
                if (map.containsKey(preSum - k)) {
                    cnt += map.get(preSum - k);
                }
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
            }
//            System.out.println("map = " + map);
            return cnt;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
