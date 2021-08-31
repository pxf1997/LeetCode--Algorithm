/**
 * 题目Id：剑指 Offer 61
 * 题目：扑克牌中的顺子
 * 日期：2021-06-22 10:28:21
 */
//从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任
//意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// 👍 140 👎 0


package leetcode.editor.cn;

//扑克牌中的顺子

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class 剑指Offer_61_扑克牌中的顺子 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_61_扑克牌中的顺子().new Solution();
        boolean b = solution.isStraight(new int[]{0, 0, 1, 2, 5});
        System.out.println("b = " + b);
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    // 可以这么理解，简单来说就是要是5个数字，最大和最小差值在5以内，并且没有重复数值。
    class Solution {
        public boolean isStraight(int[] nums) {
            int cnt_0 = 0;
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (num != 0) {
                    set.add(num);
                } else {
                    cnt_0++;
                }
            }
            if (set.size() + cnt_0 < 5) return false;
            int max = Collections.max(set);
            int min = Collections.min(set);
            return max - min < 5;
        }
    }

    // 参考
    class Solution2 {
        public boolean isStraight(int[] nums) {
            Set<Integer> repeat = new HashSet<>();
            int max = 0, min = 14;
            for (int num : nums) {
                if (num == 0) continue; // 跳过大小王
                max = Math.max(max, num); // 最大牌
                min = Math.min(min, num); // 最小牌
                if (repeat.contains(num)) return false; // 若有重复，提前返回 false
                repeat.add(num); // 添加此牌至 Set
            }
            return max - min < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
