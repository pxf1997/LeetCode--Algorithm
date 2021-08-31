/**
 * 题目Id：303
 * 题目：区域和检索 - 数组不可变
 * 日期：2021-04-21 10:39:11
 */
//给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。 
//
// 
// 
// 实现 NumArray 类： 
//
// 
// NumArray(int[] nums) 使用数组 nums 初始化对象 
// int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 s
//um(nums[i], nums[i + 1], ... , nums[j])） 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["NumArray", "sumRange", "sumRange", "sumRange"]
//[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//输出：
//[null, 1, -1, -3]
//
//解释：
//NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
//numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
//numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 0 <= i <= j < nums.length 
// 最多调用 104 次 sumRange 方法 
// 
// 
// 
// Related Topics 动态规划 
// 👍 327 👎 0


package leetcode.editor.cn;

//区域和检索 - 数组不可变

public class P303_RangeSumQueryImmutable {
    public static void main(String[] args) {
        //测试代码
//        Solution solution = new P303_RangeSumQueryImmutable().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray1 {
        private final int[] data;

        public NumArray1(int[] nums) {
            data = nums;
        }

        public int sumRange(int left, int right) {
            int res = 0;
            for (int i = left; i <= right; i++) {
                res += data[i];
            }
            return res;
        }
    }

    class NumArray {
//        sums[i] 表示数组 nums 从下标 0 到下标 i−1 的前缀和。
//        将前缀和数组 sums 的长度设为 n+1 的目的是不需要对 i=0 的情况特殊处理

        private  int[] sums;

        public NumArray(int[] nums) {
            sums = new int[nums.length + 1];
//            sums[0] = 0;
            for (int i = 1; i < nums.length; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return sums[right + 1] - sums[left];

        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
