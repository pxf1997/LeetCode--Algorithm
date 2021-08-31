/**
 * 题目Id：483
 * 题目：最小好进制
 * 日期：2021-06-18 09:53:18
 */
//对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。 
//
// 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。 
//
// 
//
// 示例 1： 
//
// 
//输入："13"
//输出："3"
//解释：13 的 3 进制是 111。
// 
//
// 示例 2： 
//
// 
//输入："4681"
//输出："8"
//解释：4681 的 8 进制是 11111。
// 
//
// 示例 3： 
//
// 
//输入："1000000000000000000"
//输出："999999999999999999"
//解释：1000000000000000000 的 999999999999999999 进制是 11。
// 
//
// 
//
// 提示： 
//
// 
// n的取值范围是 [3, 10^18]。 
// 输入总是有效且没有前导 0。 
// 
//
// 
// Related Topics 数学 二分查找 
// 👍 77 👎 0


package daily_2021_06;

//最小好进制

public class P483_SmallestGoodBase {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P483_SmallestGoodBase().new Solution();
//        String smallestGoodBase = solution.smallestGoodBase("1000");
        String smallestGoodBase = solution.smallestGoodBase("4681");
//        String smallestGoodBase = solution.smallestGoodBase("1000000000000000000");
        System.out.println("smallestGoodBase = " + smallestGoodBase);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    /*
        设n的最小好进制为k，转化为k进制后的字符串为"111…11"，
        进而n可以转化为 n ==  k^(m-1) + k^(m-2) … + k1 + k0
        (其中m待定，是一个常数，为转换为k进制的后的字符串的长度）
        这就是一个等比数列，根据等比数列的求和公式，
        可知 n == (k^m - 1) / (k - 1)，由于题目中要求进制k尽量的小,
        那么根据求和公式知需要m尽可能的大（转换k进制后1的个数尽量多）。
        因为（k>=2），所以m的最大值为 m = log2(num+1)，
        并且由于n的取值范围是 [3, 10^18]，即m的最小值是2。
    */


    class Solution {
        // 加二分查找
        public String smallestGoodBase1(String n) {
            //将字符串解析成long型数据
            long num = Long.parseLong(n);
            //对进行m的大小进行穷举（m含义是转换为k进制后1的个数）
            for (int m = (int) (Math.log(num + 1) / Math.log(2)); m >= 2; m--) {
                //用二分法搜索对应的k,(k的含义是k进制)
                long left = 2, right = (long) Math.pow(num, 1.0 / (m - 1)) + 1;
                while (left < right) {
                    long mid = left + (right - left) / 2, sum = 0;

                    //等比数列求和
                    for (int j = 0; j < m; j++)
                        sum = sum * mid + 1;
//                    sum = (long) ((Math.pow(mid, m) - 1) / (mid - 1));

                    if (sum == num)
                        return String.valueOf(mid);
                    else if (sum < num)
                        left = mid + 1;
                    else
                        right = mid;
                }
            }
            return String.valueOf(num - 1);
        }

        // 常规
        // m+1--全1串长度  k--k进制
        public String smallestGoodBase(String n) {
            long nVal = Long.parseLong(n);
            // m的最大值
            int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
            for (int m = mMax; m > 1; m--) {
                // 对k的估值
                int k = (int) Math.pow(nVal, 1.0 / m);
                // 等比数列求和 1 + k + k^2 + ..... + k^(m+1)
                long mul = 1, sum = 1;
                for (int i = 0; i < m; i++) {
                    mul *= k;
                    sum += mul;
                }

                // helper
                System.out.println("全1串长度:" + (m + 1));
                System.out.println("对进制k的估值:" + k);
                System.out.println("等比数列求和:" + sum);
                System.out.println();


                if (sum == nVal) {
                    return Integer.toString(k);
                }
            }
            return Long.toString(nVal - 1);
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
