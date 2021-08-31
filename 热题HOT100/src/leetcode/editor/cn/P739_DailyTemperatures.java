/**
 * 题目Id：739
 * 题目：每日温度
 * 日期：2021-07-07 14:13:15
 */
//请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 
//
// 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
//, 1, 1, 0, 0]。 
//
// 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。 
// Related Topics 栈 数组 单调栈 
// 👍 801 👎 0


package leetcode.editor.cn;

//每日温度

import java.util.Arrays;
import java.util.Stack;

public class P739_DailyTemperatures {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P739_DailyTemperatures().new Solution();
        int[] res = solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println("res = " + Arrays.toString(res));

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 分析--单调栈
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int len = temperatures.length;
            int[] res = new int[len];
            // 单调栈--从底到顶递减,存储下标
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < len; i++) {
                System.out.println("入栈下标: " + i + "  对应温度:" + temperatures[i] + "  它未入栈之前的单调栈:" + stack);

                // 新来的元素大 --> 出栈更新
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    int idx = stack.pop();
                    res[idx] = i - idx;
                    System.out.println("出栈下标: " + i + "  更新res[" + idx + "]=" + res[idx]);
                }
                // 空栈 or 新来的元素小 --> 入栈
//                if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]) {
//                    stack.push(i);
//                }

                // 不做判断,到此直接入栈
                stack.push(i);
                // 考虑如下结论--每个新来的温度都会入栈
                // 如果它较大则会出栈若干元素,更新结果!
                // 如果它较小则直接入栈,排在上面
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
