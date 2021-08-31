/**
 * 题目Id：1734
 * 题目：解码异或后的排列
 * 日期：2021-05-11 10:42:40
 */
//给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。 
//
// 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说
//，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。 
//
// 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。 
//
// 
//
// 示例 1： 
//
// 输入：encoded = [3,1]
//输出：[1,2,3]
//解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
// 
//
// 示例 2： 
//
// 输入：encoded = [6,5,4,6]
//输出：[2,4,1,5,3]
// 
//
// 
//
// 提示： 
//
// 
// 3 <= n < 105 
// n 是奇数。 
// encoded.length == n - 1 
// 
// Related Topics 位运算 
// 👍 56 👎 0


package leetcode.editor.cn;

//解码异或后的排列

import java.util.*;

public class P1734_DecodeXoredPermutation {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1734_DecodeXoredPermutation().new Solution();
        int[] perm = solution.decode(new int[]{6, 5, 4, 6});
        System.out.println("perm = " + Arrays.toString(perm));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    //回溯法--必然超时
/*    class Solution1 {
        //数学推导 ---- x^x=0，x^0=x ---- a^b^b=a
        // encoded[i] = perm[i] ^ perm[i+1]
        // perm[i] ^ encoded[i] = perm[i+1]
        public int[] decode(int[] encoded) {
            int len = encoded.length + 1;
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = i + 1;
            }
            boolean[] visited = new boolean[len];

            List<Integer> result = new ArrayList<>();
            //细节 len_perm=5  1,2,3,4,5是可行排列！
            backtracking(encoded, nums, 0, result, visited);

            int[] res = new int[len];

            for (int i = 0; i < len; i++) {
                res[i] = result.get(i);
            }
            return res;
        }

        private void backtracking(int[] encoded, int[] nums, int begin, List<Integer> result, boolean[] visited) {
            if (result.size() == nums.length) {
                System.out.println("递归终止result = " + result);
                return;
            }
            if (visited[begin]) {
                return;
            }
            // encoded[i-1] = perm[i-1] ^ perm[i]
            int curLen = result.size();
            int perm_i_1 = result.get(curLen - 1);
            int perm_i = result.get(curLen - 2);

            if (encoded[curLen - 2] != (perm_i_1 ^ perm_i)) {
                return;
            }

            for (int i = 0; i <= nums.length; i++) { // 绝壁不行--你在按顺序找
                result.add(nums[i]);
                visited[i] = true;

                backtracking(encoded, nums, i + 1, result, visited);

                result.remove(result.size() - 1);
                visited[i] = false;
            }
        }
    }*/

    //my--第一个元素逐个尝试--超时
    class Solution1 {
        // perm[i] ^ encoded[i] = perm[i+1]
        public int[] decode(int[] encoded) {
            int len = encoded.length + 1;
            for (int i = 1; i <= len; i++) {
                int[] perm = new int[len];
                System.out.println("尝试 perm[0] = " + i);
                Set<Integer> set = new HashSet<Integer>();
                perm[0] = i;
                boolean flag = true;

                for (int j = 1; j < len; j++) {
                    perm[j] = perm[j - 1] ^ encoded[j - 1];
                    System.out.println("perm[" + j + "] = " + perm[j]);
                    if (perm[j] > len || perm[j] < 1) {
                        System.out.println("解码失败--perm元素超出限制");
                        flag = false;
                        break;
                    }
                    if (!set.add(perm[j])) {
//                        System.out.println("解码失败--perm中重复元素");
                        flag = false;
                        break;
                    }
                }

                System.out.println();
                if (flag) {
                    return perm;
                }
            }
            return null;
        }
    }

    //参考--用技巧
    class Solution {
//        思路步骤：
//        既然我们知道了perm = [A, B, C, D, E]，那么encoded = [AB, BC, CD, DE]；
//        根据perm，我们可以得到ABCDE,根据encoded的BC和DE，我们可以得到BCDE；
//        将ABCDE和BCDE进行异或运算，得到A，即perm的第一个元素。这时候，今天的题目转换成上面的第一题。

        public int[] decode(int[] encoded) {
            int len = encoded.length + 1;
            int[] perm = new int[len];

            // 初始化，这个变量用于存放perm中所有数值进行异或的结果
            int ABCDE = 0;
            for (int i = 1; i <= len; i++) {
                ABCDE ^= i;
            }

            // 为了得到perm的第一个数值，需要初始化一个“BCDE”
            int BCDE = 0;
            for (int i = 1; i < encoded.length; i += 2) {
                //“BCDE”的求值，就是encoded中从index=1开始，步长为2地取值进行异或的结果
                BCDE ^= encoded[i];
            }

            perm[0] = ABCDE ^ BCDE;

            for (int i = 1; i < len; i++) {
                perm[i] = perm[i - 1] ^ encoded[i - 1];
            }

            return perm;
        }

    }


//leetcode submit region end(Prohibit modification and deletion)

}
