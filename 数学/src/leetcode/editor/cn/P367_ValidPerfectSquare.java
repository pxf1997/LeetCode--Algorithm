/**
 * 题目Id：367
 * 题目：有效的完全平方数
 * 日期：2021-05-12 14:05:46
 */
//给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。 
//
// 进阶：不要 使用任何内置的库函数，如 sqrt 。 
//
// 
//
// 示例 1： 
//
// 
//输入：num = 16
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：num = 14
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num <= 2^31 - 1 
// 
// Related Topics 数学 二分查找 
// 👍 212 👎 0


package leetcode.editor.cn;

//有效的完全平方数

public class P367_ValidPerfectSquare {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P367_ValidPerfectSquare().new Solution();
//        boolean is_square = solution.isPerfectSquare(16);
//        boolean is_square = solution.isPerfectSquare(1);
        boolean is_square = solution.isPerfectSquare(5);
//        boolean is_square = solution.isPerfectSquare(2147483647);
        System.out.println("is_square = " + is_square);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //用性质--间隔为等差数列平方序列：1,4,9,16,.. 间隔：3,5,7,...
    class Solution1 {
        public boolean isPerfectSquare(int num) {
            int subNum = 1;
            while (num > 0) {
                num -= subNum;
                subNum += 2;
            }
            return num == 0;
        }
    }

    //二分查找
    class Solution {
        public boolean isPerfectSquare(int num) {
            int lo = 1, hi = num;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                System.out.println("lo=" + lo + "  hi=" + hi + "  mid=" + mid + "  num/mid=" + (double) num / (double) mid);
                if ((double) mid == (double) num / (double) mid) { // mid * mid == num 越界
                    return true;
                } else if ((double) mid < (double) num / (double) mid) { //mid小了
                    lo = mid + 1;
                } else { //mid大了
                    hi = mid - 1;
                }
            }
            System.out.println("结束循环: lo=" + lo + "  hi=" + hi);
            return false;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
