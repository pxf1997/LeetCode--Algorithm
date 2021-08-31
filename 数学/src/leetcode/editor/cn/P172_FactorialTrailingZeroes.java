/**
 * 题目Id：172
 * 题目：阶乘后的零
 * 日期：2021-05-11 15:51:23
 */
//给定一个整数 n，返回 n! 结果尾数中零的数量。 
//
// 示例 1: 
//
// 输入: 3
//输出: 0
//解释: 3! = 6, 尾数中没有零。 
//
// 示例 2: 
//
// 输入: 5
//输出: 1
//解释: 5! = 120, 尾数中有 1 个零. 
//
// 说明: 你算法的时间复杂度应为 O(log n) 。 
// Related Topics 数学 
// 👍 458 👎 0


package leetcode.editor.cn;

//阶乘后的零

public class P172_FactorialTrailingZeroes {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P172_FactorialTrailingZeroes().new Solution();
        int trailingZeroes = solution.trailingZeroes(125);
        System.out.println("trailingZeroes = " + trailingZeroes);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 朴素法--阶乘算出来--肯定不行
    // 30！超过long的限制了
    class Solution1 {
        public int trailingZeroes(int n) {
            long JieCheng = 1;
            for (int i = 1; i <= n; i++) {
                JieCheng *= i;
            }
            String s = String.valueOf(JieCheng);
            int cnt = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    cnt++;
                } else {
                    break;
                }
            }
            return cnt;

        }
    }

    class Solution {
        // 思路--统计因子 5 的个数
        // 注意--25提供 两个5 125提供3个5
        public int trailingZeroes1(int n) {
            int total = 0;
            for (int i = 1; i <= n; i++) {
                int count = 0;
                int N = i;
                while (N > 0) {
                    if (N % 5 == 0) {
                        count++;
                        N /= 5;
                    } else {
                        break;
                    }
                }
                if (count > 0) {
                    System.out.println(i + " 提供了" + count + "个因子5");
                    total += count;
                }
            }
            return total;
        }

        // 再优化
        // 每隔 25 个数，出现 2个 5。每隔 125 个数，出现 3 个 5
        // 最终 5 的个数就是 n / 5 + n / 25 + n / 125
        public int trailingZeroes(int n) {
            int count = 0;
            int level = 1;
            while (n > 0) {
                System.out.println(Math.pow(5, level) + " 的个数=" + (n / 5));
                count += n / 5;
                n = n / 5;
                level++;
            }
            return count;

        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
