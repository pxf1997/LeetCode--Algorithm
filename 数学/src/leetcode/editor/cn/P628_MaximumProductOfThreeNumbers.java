/**
 * 题目Id：628
 * 题目：三个数的最大乘积
 * 日期：2021-05-12 16:19:16
 */
//给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：24
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,-2,-3]
//输出：-6
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 104 
// -1000 <= nums[i] <= 1000 
// 
// Related Topics 数组 数学 
// 👍 296 👎 0


package leetcode.editor.cn;

//三个数的最大乘积

import java.util.*;

public class P628_MaximumProductOfThreeNumbers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P628_MaximumProductOfThreeNumbers().new Solution();
        int res = solution.maximumProduct(new int[]{1, 2, 3, 4, 5, 6});

//        int res = solution.maximumProduct(new int[]{101, -100, -98, -1, 4, 3, 5});
//        int res = solution.maximumProduct(new int[]{-100, -98, -1, 4, 3, 5});
//        int res = solution.maximumProduct(new int[]{-3, -2, -1, 0, 0, 0, 0});
//        int res = solution.maximumProduct(new int[]{-4, -3, -2, -1, 60});
//        int res = solution.maximumProduct(new int[]{-3, 2, 1});
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // dp考虑----dp[i]不一定由dp[i-1]转移而来、如何解决0的问题
    class Solution {
        public int maximumProduct(int[] nums) {
            System.out.println("nums = " + Arrays.toString(nums));

            // dp[i]----前i个元素中，三积最大值
            // key--对应的i  val--组成积dp[i]的三个因子
            Map<Integer, List<Integer>> map = new HashMap<>();
            int len = nums.length;
            if (len <= 2) {
                return 0;
            }
            int[] dp = new int[len];
            dp[0] = Integer.MIN_VALUE;
            dp[1] = Integer.MIN_VALUE;
            dp[2] = nums[2] * nums[1] * nums[0];
            map.put(0, Arrays.asList(nums[0]));
            map.put(1, Arrays.asList(nums[0], nums[1]));
            map.put(2, Arrays.asList(nums[0], nums[1], nums[2]));

            //全局最大值
            int totalmax = dp[2];

            for (int i = 3; i < len; i++) {
                // tempMax初始化为最小值--局部
                int tempMax = Integer.MIN_VALUE;
                for (int j = 2; j < i; j++) {
                    //遍历j的过程中，能否用num[i]替换---维护curMax 初始化为dp[j]
                    int curMax = dp[j];
                    List<Integer> old_List = map.get(j);
                    List<Integer> cur_List = new ArrayList<>(old_List);
                    int replaceIndex = -1;

                    //能否用num[i]替换
                    for (int k = 0; k < old_List.size(); k++) {
                        int replace = old_List.get(k);
                        int temp = (dp[j] / replace) * nums[i];
                        if (temp > curMax) {
                            curMax = temp;
                            replaceIndex = k;
                        }
                    }

                    if (curMax > tempMax) {
                        //此逻辑发生---map中结果更新
                        //①用nums[i]进行替换
                        if (replaceIndex >= 0) {
                            cur_List.set(replaceIndex, nums[i]);
                            System.out.println("dp[" + j + "]=" + dp[j] + " 对应列表:" + old_List + " 可替换为:" + cur_List +
                                    "  得到新最大值为:" + curMax);
                        } else {
                            System.out.println("对于列表:" + old_List + " 无法进行替换");
                        }
                        //②没有用到nums[i]
                        map.put(i, cur_List);
                    }
                    tempMax = Math.max(curMax, tempMax); //记录--遍历前j个的最大值
                }

                // 原版代码错在哪---dp[i-1]的组成，你不知道！！！
                // j∈[0,i-1]----不取nums[j] 改取nums[i]
//                for (int j = 0; j < i; j++) {
//                    int change = (dp[i - 1] / nums[j]) * nums[i];
//                    curMax = Math.max(change, curMax);
//                }


                // helper
//                System.out.print("nums[" + i + "]=" + nums[i]);
//                System.out.print("  选取nums[" + i + "]:" + tempMax);
//                System.out.println("  不选取nums[" + i + "]:" + dp[i - 1]);

                totalmax = Math.max(totalmax, tempMax);
                dp[i] = totalmax;

                System.out.println("最终赋值----dp[" + i + "]=" + dp[i] + "  因子列表:" + map.get(i));
                System.out.println();

            }
            System.out.println("dp = " + Arrays.toString(dp));
            System.out.println("map = " + map);

            return dp[len - 1]; // dp[len]----所有元素中，三积最大值  (注意有时取的是dp数组的最大值)

        }
    }

    //  数学方法--首先将数组排序。
    //  如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数相乘同样也为最大乘积。
    //  如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
    //  综上，我们在给数组排序后，分别求出三个最大正数的乘积，以及两个最小负数与最大正数的乘积，二者之间的最大值即为所求答案。
    class Solution1 {
        public int maximumProduct(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            // ++- 出现这种情况当且仅当有三个元素，符号为++-
            int num1 = nums[0] * nums[1] * nums[n - 1];  // --+
            int num2 = nums[n - 3] * nums[n - 2] * nums[n - 1]; // +++ or ---
            System.out.println("--+ : " + num1 + "  +++/--- : " + num2);
            return Math.max(num1, num2);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
