/**
 * 题目Id：503
 * 题目：下一个更大元素 II
 * 日期：2021-07-07 16:54:38
 */
//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第
//一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
//
// 示例 1:
//
//
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数；
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
//
//
// 注意: 输入数组的长度不会超过 10000。
// Related Topics 栈 数组 单调栈
// 👍 447 👎 0


package leetcode.editor.cn;

//下一个更大元素 II

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P503_NextGreaterElementIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P503_NextGreaterElementIi().new Solution();
        // case
//        int[] nums = new int[]{1, 2, 1};
//        int[] nums = new int[]{2, 3, 5, 1, 0, 7, 4};
        int[] nums = new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100};

        int[] res = solution.nextGreaterElements(nums);
        System.out.println("res = " + Arrays.toString(res));
    }


    // 分析--单调栈
    // 为了解决循环查找问题,将该数组"拉直"--
    // 即复制该序列的前 len−1 个元素拼接在原序列的后面。
    // 评价--拉直有重复计算,长度为 2*len-1
    class Solution1 {
        public int[] nextGreaterElements(int[] nums) {
            int len = nums.length;
            int[] res = new int[len];

            // 1--构造辅助数组
            // 前len-1个元素循环添加到后面
            // 例如--{1,2,1} -->{1,2,1, 1,2}
            int[] helper = new int[2 * len - 1];
            int idx = 0;
            for (int num : nums) {
                helper[idx++] = num;
            }
            for (int i = 0; i < len - 1; i++) {
                helper[idx++] = nums[i];
            }
            System.out.println("helper = " + Arrays.toString(helper));
            System.out.println("有效下标到:" + (len - 1));

            // 2--构造单调栈及哈希表
            // 单调栈,从底到顶递减----新来的比栈顶大,则不断出栈更新
            Stack<Integer> stack = new Stack<>();
            // !!!错误!!! key--nums对应元素, val--它的右侧元素
            // 为啥错误??? 有重复元素,直接覆盖掉了!
            // 修改 key--nums下标, val--它的右侧元素
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < helper.length; i++) {
                int num = helper[i];
                System.out.println("入栈下标:" + i + "  对应元素:" + num + "  它未入栈之前的单调栈:" + stack);
                while (!stack.isEmpty() && num > helper[stack.peek()]) {
                    int out = stack.pop();
                    map.put(out, num);
                    System.out.println("出栈下标:" + out + " 对应元素:" + helper[out] + "  它的右侧元素为:" + num);
                }
                stack.push(i);
            }
            // 分析--下标范围[0,len]为有效结果, 进行了一些多余计算,不过无所谓
            System.out.println("map = " + map);

            // 3--遍历原始nums数组,从map中取值出来
            for (int i = 0; i < len; i++) {
                res[i] = map.getOrDefault(i, -1);
            }
            return res;
        }
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 单调栈思想不变
    // 循环数组 + 取模
    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int len = nums.length;
            int[] res = new int[len];
            Arrays.fill(res, -1);
            Stack<Integer> stack = new Stack<Integer>();
            // 循环次数 == 拉直 == 2*len-1
            // 下标为len --> len%len=0 循环到下标0
            for (int i = 0; i < 2 * len - 1; i++) {
                System.out.println("入栈下标:" + (i % len) + "  对应元素:" + nums[i % len] + "  它未入栈之前的单调栈:" + stack);
                // 新来的元素大,出栈若干个并进行更新
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i % len]) {
                    int idx = stack.pop();
                    res[idx] = nums[i % len];
                    // helper--其实也进行了一些重复计算
                    System.out.println("出栈下标:" + idx + "  对应元素:" + nums[idx] + "  它的右侧元素为:" + res[idx]);
                }
                stack.push(i % len);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
