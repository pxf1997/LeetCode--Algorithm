/**
 * 题目Id：204
 * 题目：计数质数
 * 日期：2021-05-10 17:10:56
 */
//统计所有小于非负整数 n 的质数的数量。 
//
// 
//
// 示例 1： 
//
// 输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 106 
// 
// Related Topics 哈希表 数学 
// 👍 677 👎 0


package leetcode.editor.cn;

//计数质数

import java.util.ArrayList;
import java.util.List;

public class P204_CountPrimes_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P204_CountPrimes_old().new Solution();
        int countPrimes = solution.countPrimes(100);
        System.out.println("countPrimes = " + countPrimes);
        System.out.println("solution.res = " + solution.res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //朴素
    class Solution1 {
        List<Integer> res = new ArrayList<Integer>();

        public int countPrimes(int n) {
            int ans = 0;
            for (int i = 2; i < n; ++i) {
                ans += isPrime(i) ? 1 : 0;
            }
            return ans;
        }

        public boolean isPrime(int x) {
            for (int i = 2; i * i <= x; ++i) {
                if (x % i == 0) {
                    return false;
                }
            }
            res.add(x);
            return true;
        }
    }

    //埃拉托斯特尼筛法
    class Solution {
        List<Integer> res = new ArrayList<Integer>();

        public int countPrimes(int n) {
            boolean[] notPrimes = new boolean[n + 1];
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (notPrimes[i]) {
                    continue;
                }
                count++;
                res.add(i);

                // 如果这个数为质数，则将其所有的倍数都标记为合数
                // 从 i * i 开始，因为如果 k < i，那么 k * i 在之前就已经被去除过了--
                // 例如 2 的所有倍数，3 的所有倍数等
                for (long j = (long) (i) * i; j < n; j += i) {
                    notPrimes[(int) j] = true;
                }
            }
            return count;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
