/**
 * 题目Id：376
 * 题目：摆动序列
 * 日期：2021-04-21 18:11:17
 */
//如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。 
//
// 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,
//4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。 
//
// 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。 
//
// 示例 1: 
//
// 输入: [1,7,4,9,2,5]
//输出: 6 
//解释: 整个序列均为摆动序列。
// 
//
// 示例 2: 
//
// 输入: [1,17,5,10,13,15,10,5,16,8]
//输出: 7
//解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。 
//
// 示例 3: 
//
// 输入: [1,2,3,4,5,6,7,8,9]
//输出: 2 
//
// 进阶: 
//你能否用 O(n) 时间复杂度完成此题? 
// Related Topics 贪心算法 动态规划 
// 👍 425 👎 0


package leetcode.editor.cn;

//摆动序列

public class P376_WiggleSubsequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P376_WiggleSubsequence().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //    	概念--峰、谷、上升序列or下降序列、过渡元素
//       up[i] 表示以前 i 个元素中的某一个为结尾的最长的「上升摆动序列」的长度。
//       down[i] 表示以前 i 个元素中的某一个为结尾的最长的「下降摆动序列」的长度。
//        状态转移规则--看网站收藏夹
        public int wiggleMaxLength1(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return len;
            }

            int[] up = new int[len];
            int[] down = new int[len];
            up[0] = down[0] = 1;

            for (int i = 1; i < len; i++) {
                if (nums[i] > nums[i - 1]) {
                    up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                    down[i] = down[i - 1];
                } else if (nums[i] < nums[i - 1]) {
                    up[i] = up[i - 1];
                    down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
                } else {
                    up[i] = up[i - 1];
                    down[i] = down[i - 1];
                }
            }
            return Math.max(up[len - 1], down[len - 1]);
        }

        //        贪心法
        public int wiggleMaxLength(int[] nums) {
            int n = nums.length;
            // 1. 长度为1的都是摆动序列
            if (n < 2) {
                return n;
            }
            // 2. 初始化
            int prevdiff = nums[1] - nums[0]; // 记录相邻三个元素 x y z(前两个元素 x 和 y 的差是正还是负)
            int ret = prevdiff != 0 ? 2 : 1; // 前两个元素是否有重复

            // 3. 贪心遍历数组:加入一个新元素
            for (int i = 2; i < n; i++) {
                int diff = nums[i] - nums[i - 1]; // 记录相邻三个元素 x y z(y 和 z 的差是正还是负)
                if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {// 判断当前序列的上升下降趋势
                    ret++; // 如果出现了「峰」或「谷」，答案加一
                    prevdiff = diff; // 更新当前序列的上升下降趋势
                }
            }
            return ret;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
