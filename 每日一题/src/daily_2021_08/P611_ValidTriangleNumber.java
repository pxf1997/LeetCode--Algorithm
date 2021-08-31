/**
 * 题目Id：611
 * 题目：有效三角形的个数
 * 日期：2021-08-04 09:20:59
 */
//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。 
//
// 示例 1: 
//
// 
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是: 
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
// 
//
// 注意: 
//
// 
// 数组长度不超过1000。 
// 数组里整数的范围为 [0, 1000]。 
// 
// Related Topics 贪心 数组 双指针 二分查找 排序 
// 👍 209 👎 0


package daily_2021_08;

//有效三角形的个数

import java.util.Arrays;

public class P611_ValidTriangleNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P611_ValidTriangleNumber().new Solution();
        int[] nums = new int[]{2, 2, 3, 4};
        int res = solution.triangleNumber(nums);
        System.out.println("res = " + res);
    }


    // 暴力法--三重循环 + 判断能否成为三角形 评价--O(n^3)超时
    class Solution_暴力搜索 {
        public int triangleNumber(int[] nums) {
            int cnt = 0;
            int len = nums.length;
            Arrays.sort(nums); // 先对nums排序总归是有用滴!
            for (int i = 0; i < len - 2; i++) {
                for (int j = i + 1; j < len - 1; j++) {
                    for (int k = j + 1; k < len; k++) {
                        if (isTriangle(nums[i], nums[j], nums[k])) {
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }

        private boolean isTriangle(int a, int b, int c) {
            // 两边之和大于第三边 / 之差小于第三边
            // 三个数的关系: a<b<c
            // 注--c-b<a / c-a<b / a+b>c 三者等价
            return a + b > c;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 参考答案--排序+双指针
    class Solution {
        public int triangleNumber(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);
            int cnt = 0;
            // i,j为前两边的边长,k为第三边
            // 一重循环遍历i,双指针维护j和k
            for (int i = 0; i < len; i++) {
                int k = i;
                // 逻辑--固定i,随着j增加,k必须增加
                for (int j = i + 1; j < len; j++) {
                    while (k + 1 < len && nums[k + 1] < nums[i] + nums[j]) {
                        k++;
                    }
                    cnt += Math.max(k - j, 0);//防止加入负数
                }
            }
            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
