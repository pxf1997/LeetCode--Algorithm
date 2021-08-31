package daily_2021_05; /**
 * 题目Id：1442
 * 题目：形成两个异或相等数组的三元组数目
 * 日期：2021-05-18 10:27:26
 */
//给你一个整数数组 arr 。 
//
// 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。 
//
// a 和 b 定义如下： 
//
// 
// a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] 
// b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 
// 
//
// 注意：^ 表示 按位异或 操作。 
//
// 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [2,3,1,6,7]
//输出：4
//解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
// 
//
// 示例 2： 
//
// 输入：arr = [1,1,1,1,1]
//输出：10
// 
//
// 示例 3： 
//
// 输入：arr = [2,3]
//输出：0
// 
//
// 示例 4： 
//
// 输入：arr = [1,3,5,7,9]
//输出：3
// 
//
// 示例 5： 
//
// 输入：arr = [7,11,12,9,5,2,7,17,22]
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 300 
// 1 <= arr[i] <= 10^8 
// 
// Related Topics 位运算 数组 数学 
// 👍 86 👎 0


//形成两个异或相等数组的三元组数目

import java.util.HashMap;
import java.util.Map;

public class P1442_CountTripletsThatCanFormTwoArraysOfEqualXor {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P1442_CountTripletsThatCanFormTwoArraysOfEqualXor().new Solution();
        int countTriplets = solution.countTriplets(new int[]{2, 3, 1, 6, 7});
//        int countTriplets = solution.countTriplets(new int[]{1,1,1,1,1});
        System.out.println("countTriplets = " + countTriplets);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 朴素--暴力法
    /*class Solution_my {
        public int countTriplets(int[] arr) {
            List<int[]> res = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    //计算 a
                    int a = compute_XOR_range(arr, i, j - 1);
                    System.out.println("i=" + i + " j=" + j + " a=" + a);

                    for (int k = j; k < arr.length; k++) {
                        //计算 b
                        int b = compute_XOR_range(arr, j, k);
                        System.out.println("j=" + j + " k=" + k + " b=" + b);
                        if (a == b) {
                            System.out.println("成功找到: i=" + i + " j=" + j + " k=" + k + " b=" + b);
                            res.add(new int[]{i, j, k});
                        }
                    }
                    System.out.println();
                }
            }
            dp_util.print_ListWithArrays(res);
            return res.size();

        }

        private int compute_XOR_range(int[] arr, int startIndex, int endIndex) {
            if (startIndex <= endIndex) {
                int res = 0;
                for (int i = startIndex; i <= endIndex; i++) {
                    res ^= arr[i];
                }
                return res;
            } else {
                return -100;
            }
        }
    }*/

    //参考--异或前缀
    class Solution {
        //  2重循环--
        //  [i+1,k] 的范围内的任意 j 都是符合要求的，对应的三元组个数为 k-i
        public int countTriplets(int[] arr) {
            // s[i] = arr[i-1]^arr[i-2]...arr[0]  s[0]无意义
            int n = arr.length;
            int[] s = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                s[i + 1] = s[i] ^ arr[i];
            }
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int k = i + 1; k < n; ++k) {
                    if (s[i] == s[k + 1]) {
                        ans += k - i;
                    }
                }
            }
            return ans;
        }

        // 1重循环--不容易理解啊,额外空间复杂度
        public int countTriplets_1(int[] arr) {
            int n = arr.length;
            int[] s = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                s[i + 1] = s[i] ^ arr[i];
            }
            //下标 i 的出现次数 m
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            //下标 i 之和
            Map<Integer, Integer> total = new HashMap<Integer, Integer>();
            int ans = 0;
            for (int k = 0; k < n; ++k) {
                if (cnt.containsKey(s[k + 1])) {
                    ans += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
                }
                cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
                total.put(s[k], total.getOrDefault(s[k], 0) + k);
            }
            return ans;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
