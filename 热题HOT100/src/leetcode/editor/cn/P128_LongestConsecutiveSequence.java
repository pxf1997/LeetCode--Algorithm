/**
 * 题目Id：128
 * 题目：最长连续序列
 * 日期：2021-07-01 16:52:44
 */
//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 哈希表 
// 👍 815 👎 0


package leetcode.editor.cn;

//最长连续序列

import java.util.HashSet;
import java.util.Set;

public class P128_LongestConsecutiveSequence {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P128_LongestConsecutiveSequence().new Solution();
        int longestConsecutive = solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println("longestConsecutive = " + longestConsecutive);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 哈希表,逻辑很巧妙!
    class Solution {
        public int longestConsecutive(int[] nums) {
            // set去重
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            //System.out.println("set = " + set);

            int max = 0;
            // 梳理逻辑--若包含4,则去找是否包含3,2,1...
            // 找到1后,发现不包含0,因此以0为起点进行探索
            for (Integer num : set) {
                if (!set.contains(num - 1)) {
                    // 以start为起点往上增长
                    int start = num;
                    int len = 0;
                    //System.out.println("start = " + start);
                    while (set.contains(start)) {
                        start++;
                        len++;
                        //set.remove(start); // 报ConcurrentModificationException异常
                    }
                    max = Math.max(max, len);
                }
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
