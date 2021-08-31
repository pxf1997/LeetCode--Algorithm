package daily_2021_05; /**
 * 题目Id：598
 * 题目：范围求和 II
 * 日期：2021-05-20 23:05:10
 */
//给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。 
//
// 操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素
// M[i][j] 的值都增加 1。 
//
// 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。 
//
// 示例 1: 
//
// 
//输入: 
//m = 3, n = 3
//operations = [[2,2],[3,3]]
//输出: 4
//解释: 
//初始状态, M = 
//[[0, 0, 0],
// [0, 0, 0],
// [0, 0, 0]]
//
//执行完操作 [2,2] 后, M = 
//[[1, 1, 0],
// [1, 1, 0],
// [0, 0, 0]]
//
//执行完操作 [3,3] 后, M = 
//[[2, 2, 1],
// [2, 2, 1],
// [1, 1, 1]]
//
//M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
// 
//
// 注意: 
//
// 
// m 和 n 的范围是 [1,40000]。 
// a 的范围是 [1,m]，b 的范围是 [1,n]。 
// 操作数目不超过 10000。 
// 
// Related Topics 数学 
// 👍 60 👎 0


//范围求和 II

import util.dp_util;

import java.util.*;

public class P598_RangeAdditionIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P598_RangeAdditionIi().new Solution();
        int maxCount = solution.maxCount(3, 3, new int[][]{{2, 2}, {3, 3}});
        System.out.println("maxCount = " + maxCount);
    }


    //  my最暴力方法----统计所有东西(流程很清晰但是必须超时)
    class Solution_1 {
        public int maxCount(int m, int n, int[][] ops) {
            int[][] matrix = new int[m][n];
            for (int[] op : ops) {
                //  一次操作---op[0]行,op[1]列
                System.out.println("本次操作:" + op[0] + "行," + op[1] + "列");
                for (int i = 0; i < op[0]; i++) {
                    for (int j = 0; j < op[1]; j++) {
                        matrix[i][j]++;
                    }
                }
                dp_util.print_2D(matrix);
                System.out.println();
            }

            // key--元素  val--出现次数
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
                }
            }
            System.out.println("map = " + map);

            // 用map的entrySet 初始化一个列表
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
            System.out.println("排序前list = " + list);

            // list 按key值排序 取key值最大的val
            Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return o2.getKey() - o1.getKey(); //key值降序
                }
            });
            System.out.println("排序后list = " + list);
            return list.get(0).getValue();
        }
    }


    // 考虑左上角元素最大--超时
    class Solution_2 {
        public int maxCount(int m, int n, int[][] ops) {
            int[][] matrix = new int[m][n];
            for (int[] op : ops) {
                //  一次操作---op[0]行,op[1]列
//                System.out.println("本次操作:" + op[0] + "行," + op[1] + "列");
                for (int i = 0; i < op[0]; i++) {
                    for (int j = 0; j < op[1]; j++) {
                        matrix[i][j]++;
                    }
                }
            }

            // 最大的元素一定是左上角,下标[0,0]
            int cnt = 0;
            int maxVal = matrix[0][0];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == maxVal) cnt++;
                }
            }

            return cnt;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //  一遍遍历
    class Solution {
        public int maxCount(int m, int n, int[][] ops) {
            //  我们不需要将操作区域一个一个加一，我们只需要记录交集区域的右下角即可
            //  记录所有操作的 op[i] 中的最小值
            for (int[] op : ops) {
                m = Math.min(m, op[0]);
                n = Math.min(n, op[1]);
            }
            return m * n;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
