/**
 * 题目Id：剑指 Offer 66
 * 题目：构建乘积数组
 * 日期：2021-06-22 14:01:51
 */
//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[
//i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。 
//
// 
//
// 示例: 
//
// 
//输入: [1,2,3,4,5]
//输出: [120,60,40,30,24] 
//
// 
//
// 提示： 
//
// 
// 所有元素乘积之和不会溢出 32 位整数 
// a.length <= 100000 
// 
// 👍 125 👎 0


package leetcode.editor.cn;

//构建乘积数组

import java.util.Arrays;

public class 剑指Offer_66_构建乘积数组 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_66_构建乘积数组().new Solution();
        int[] constructArr = solution.constructArr(new int[]{1, 2, 0, 4, 5});
//        int[] constructArr = solution.constructArr(new int[]{3});
//        int[] constructArr = solution.constructArr(new int[]{});
        System.out.println("constructArr = " + Arrays.toString(constructArr));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] constructArr(int[] a) {
            int len = a.length;
            if (len == 0) return new int[0];
            int[] left = new int[len];
            int[] right = new int[len];
            int[] res = new int[len];

            // 构建左右侧前缀乘积
            left[0] = a[0];
            for (int i = 1; i < len; i++) {
                left[i] = left[i - 1] * a[i];
            }
            right[len - 1] = a[len - 1];
            for (int i = len - 2; i >= 0; i--) {
                right[i] = right[i + 1] * a[i];
            }

            System.out.println("a = " + Arrays.toString(a));
            System.out.println("left = " + Arrays.toString(left));
            System.out.println("right = " + Arrays.toString(right));

            // 画图看一下,多乘了两次本身(这样写防止数组越界)
            // 但如果a[i]=0,除0报错
//            for (int i = 0; i < len; i++) {
//                res[i] = (left[i] * right[i]) / (a[i] * a[i]);
//            }

            // 特殊处理两边即可
            res[0] = right[1];
            res[len - 1] = left[len - 2];
            for (int i = 1; i < len - 1; i++) {
                res[i] = left[i - 1] * right[i + 1];
            }
            return res;
        }

        // 修改为:index左右侧的乘积,不包括本身
        public int[] constructArr2(int[] a) {
            int len = a.length;
            if (len == 0) return new int[0];
            int[] left = new int[len];
            int[] right = new int[len];

            left[0] = 1;
            // L[i] 为索引 i 左侧所有元素的乘积
            // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
            for (int i = 1; i < len; i++) {
                left[i] = left[i - 1] * a[i - 1];
            }
            right[len - 1] = 1;
            for (int i = len - 2; i >= 0; i--) {
                right[i] = right[i + 1] * a[i + 1];
            }

            System.out.println("a = " + Arrays.toString(a));
            System.out.println("left = " + Arrays.toString(left));
            System.out.println("right = " + Arrays.toString(right));
            // 画图算一下,很直观
            int[] res = new int[len];
            for (int i = 0; i < len; i++) {
                res[i] = left[i] * right[i];
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
