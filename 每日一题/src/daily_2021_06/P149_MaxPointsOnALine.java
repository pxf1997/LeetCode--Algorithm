/**
 * 题目Id：149
 * 题目：直线上最多的点数
 * 日期：2021-06-24 11:07:42
 */
//给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。 
//
// 
//
// 示例 1： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 300 
// points[i].length == 2 
// -104 <= xi, yi <= 104 
// points 中的所有点 互不相同 
// 
// Related Topics 几何 哈希表 数学 
// 👍 277 👎 0


package daily_2021_06;

//直线上最多的点数

import java.util.HashMap;
import java.util.Map;

public class P149_MaxPointsOnALine {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P149_MaxPointsOnALine().new Solution();
        int maxPoints = solution.maxPoints(new int[][]{
                {1, 1},
                {3, 2},
                {5, 3},
                {4, 1},
                {2, 3},
                {1, 4},
        });
        System.out.println("maxPoints = " + maxPoints);
    }
    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 遇到困难睡大觉
    // 常规思路：
    //    以每一个点为基础，计算其余所有点与该点的斜率大小，如果斜率大小相同，则证明在同一条直线上。同时使用map来记录点的个数
    //    每经过一轮，计算map中最大的值，用来更新结果
    //    最后，对结果加1
    class Solution {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n <= 2) {
                return n;
            }
            int ret = 0;
            // 做了若干处优化
            for (int i = 0; i < n; i++) {
                if (ret >= n - i || ret > n / 2) {
                    break;
                }
                // key--  , value--
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int j = i + 1; j < n; j++) {
                    int x = points[i][0] - points[j][0];
                    int y = points[i][1] - points[j][1];
                    if (x == 0) {
                        y = 1;
                    } else if (y == 0) {
                        x = 1;
                    } else {
                        if (y < 0) {
                            x = -x;
                            y = -y;
                        }
                        int gcdXY = gcd(Math.abs(x), Math.abs(y));
                        x /= gcdXY;
                        y /= gcdXY;
                    }
                    int key = y + x * 20001;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                int maxn = 0;
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    int num = entry.getValue();
                    maxn = Math.max(maxn, num + 1);
                }
                ret = Math.max(ret, maxn);

            }

            return ret;
        }

        // 最大公约数
        public int gcd(int a, int b) {
            return b != 0 ? gcd(b, a % b) : a;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
