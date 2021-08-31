/**
 * 题目Id：69
 * 题目：x 的平方根
 * 日期：2021-04-20 09:44:28
 */
//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 656 👎 0


package leetcode.editor.cn;

//x 的平方根

public class P69_Sqrtx {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P69_Sqrtx().new Solution();
        int res = solution.mySqrt2(2147395599);
        System.out.println(res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*        一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。
                可以利用二分查找在 0 ~ x 之间查找 sqrt。*/
       /* 对于 x = 8，它的开方是 2.82842...，最后应该返回 2 而不是 3。
        在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3，
        因此最后的返回值应该为 h 而不是 l。*/
        public int mySqrt(int x) {
            if (x <= 1) return x;
            int low = 0, high = x;
            while (low <= high) { //循环退出时，h 总是比 l 小 1
                int mid = low + (high - low) / 2; // (low+high)/2 可能溢出
                int sqrt = x / mid;// mid*mid可能溢出
                if (sqrt == mid) {
                    return mid;
                } else if (sqrt > mid) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return high;
        }

        public int mySqrt2(int x) {
            int l = 0, h = x;
            while (l <= h) {
                int mid = l + (h - l) / 2;
                int res = mid * mid;
                if (res == x) return mid;
                else if (res < x) l = mid + 1;
                else h = mid - 1;
            }
            return h;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
