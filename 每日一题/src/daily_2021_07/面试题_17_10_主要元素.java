/**
 * 题目Id：面试题 17.10
 * 题目：主要元素
 * 日期：2021-07-09 09:37:55
 */
//数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1
//) 的解决方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,5,9,5,9,5,5,5]
//输出：5 
//
// 示例 2： 
//
// 
//输入：[3,2]
//输出：-1 
//
// 示例 3： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2 
// Related Topics 数组 计数 
// 👍 111 👎 0


package daily_2021_07;

//主要元素

public class 面试题_17_10_主要元素 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 面试题_17_10_主要元素().new Solution();
//        int majorityElement = solution.majorityElement(new int[]{1, 2, 5, 9, 5, 9, 5, 5, 5});
        int majorityElement = solution.majorityElement(new int[]{1, 2, 3, 4, 5});
        System.out.println("majorityElement = " + majorityElement);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            Integer res = null;
            int cnt = 0;
            for (int num : nums) {
                if (cnt == 0) {
                    res = num;
                }
                cnt += (res == num ? 1 : -1);
                System.out.println("num = " + num + "  cnt = " + cnt + "  res = " + res);
            }
            // 至此得到res--候选众数
            // 再来统计它的出现次数cnt,若 2*cnt>len说明它是真的众数!
            cnt = 0;
            for (int num : nums) {
                if (num == res) cnt++;
            }
            int len = nums.length;
            System.out.println("len = " + len + "  cnt = " + cnt);
            return (2 * cnt > len) ? res : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
