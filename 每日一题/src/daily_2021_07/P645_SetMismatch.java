/**
 * 题目Id：645
 * 题目：错误的集合
 * 日期：2021-07-04 21:11:43
 */
//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。 
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。 
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2,4]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// 1 <= nums[i] <= 104 
// 
// Related Topics 位运算 数组 哈希表 排序 
// 👍 210 👎 0


package daily_2021_07;

//错误的集合

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P645_SetMismatch {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P645_SetMismatch().new Solution();
        int[] res = solution.findErrorNums(new int[]{1, 2, 2, 4});
        System.out.println("res = " + Arrays.toString(res));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 思路--哈希表O(n)
    class Solution {
        public int[] findErrorNums(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            Integer duplicate = null, missing = null;
            // 遍历 1到 n,找到重复的和缺少的
            for (int i = 1; i <= nums.length; i++) {
                if (!map.containsKey(i)) {
                    missing = i;
                }
                // 写if会报空指针错误,为啥?
                // 分析这两种情况的成立逻辑,并不是先后判断,而是互斥的
                // 如果前面判断出key不存在,再去get就会空指针
                else if (map.get(i) == 2) {
                    duplicate = i;
                }
                if (missing != null && duplicate != null) break; // 提前结束
            }
            return new int[]{duplicate, missing};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
