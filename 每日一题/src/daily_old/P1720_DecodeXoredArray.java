package daily_old; /**
 * 题目Id：1720
 * 题目：解码异或后的数组
 * 日期：2021-05-06 10:19:59
 */
//未知 整数数组 arr 由 n 个非负整数组成。 
//
// 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，a
//rr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。 
//
// 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。 
//
// 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。 
//
// 
//
// 示例 1： 
//
// 
//输入：encoded = [1,2,3], first = 1
//输出：[1,0,2,1]
//解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [
//1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：encoded = [6,2,7,3], first = 4
//输出：[4,2,0,7,4]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 104 
// encoded.length == n - 1 
// 0 <= encoded[i] <= 105 
// 0 <= first <= 105 
// 
// Related Topics 位运算 
// 👍 33 👎 0


//解码异或后的数组

import java.util.Arrays;

public class P1720_DecodeXoredArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1720_DecodeXoredArray().new Solution();
        int[] decode = solution.decode(new int[]{6, 2, 7, 3}, 4);
        System.out.println("decode = " + Arrays.toString(decode));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        异或运算满足交换律和结合律
//        任意整数和自身做异或运算的结果都等于 0，即 x⊕x = 0
//        任意整数和 0 做异或运算的结果都等于其自身，即 x⊕0 = 0⊕x = x。

//        encode[i-1] = arr[i-1] ^ arr[i] 推导出
//        arr[i] = arr[i-1] ^ encode[i-1]


        public int[] decode(int[] encoded, int first) {
            int len = encoded.length + 1;
            int[] arr = new int[len];
            arr[0] = first;
            for (int i = 0; i < encoded.length; i++) {
                arr[i + 1] = encoded[i] ^ arr[i];
            }
            return arr;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
