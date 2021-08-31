/**
 * 题目Id：剑指 Offer 64
 * 题目：求1+2+…+n
 * 日期：2021-06-22 11:02:10
 */
//求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。 
//
// 
//
// 示例 1： 
//
// 输入: n = 3
//输出: 6
// 
//
// 示例 2： 
//
// 输入: n = 9
//输出: 45
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10000 
// 
// 👍 339 👎 0


package leetcode.editor.cn;

//求1+2+…+n

public class 剑指Offer_64_求1加到n {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_64_求1加到n().new Solution();
//        int res = solution.sumNums(100);
        int res = solution.sumNums(10);
        System.out.println("res = " + res);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        // 普通递归--也不行--用到了if关键字
        public int sumNums1(int n) {
            if (n == 1) return 1;
            return n + sumNums1(n - 1);
        }

        // if (n==1) return 1;
        // 也就是说如果n==1,需要终止递归，所以我们想到了逻辑与&&连接符。
        // A&&B，表示如果A成立则执行B，否则如果A不成立，不用执行B
        // 因此我们可以这样。在n>1的时候，执行递归函数。
        public int sumNums(int n) {
            // 前面的boolean为false完成了递归的停止
            // 两个语句凑成boolean类型,在判断过程中执行,短路与&& 若前面为false不执行后面
            boolean flag = (n > 1) && ((n += sumNums(n - 1)) > 1);
            return n;
        }
    }

    class Solution {
        public int sumNums(int n) {
            boolean helper = n > 1 && (n += sumNums(n - 1)) > 1;
            return n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
