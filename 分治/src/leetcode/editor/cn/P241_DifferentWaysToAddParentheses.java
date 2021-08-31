/**
 * 题目Id：241
 * 题目：为运算表达式设计优先级
 * 日期：2021-04-28 10:49:44
 */
//给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 *
// 。 
//
// 示例 1: 
//
// 输入: "2-1-1"
//输出: [0, 2]
//解释: 
//((2-1)-1) = 0 
//(2-(1-1)) = 2 
//
// 示例 2: 
//
// 输入: "2*3-4*5"
//输出: [-34, -14, -10, -10, 10]
//解释: 
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10 
// Related Topics 分治算法 
// 👍 365 👎 0


package leetcode.editor.cn;

//为运算表达式设计优先级

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P241_DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P241_DifferentWaysToAddParentheses().new Solution();

        List<Integer> list = solution.diffWaysToCompute("2*3-4*5");

//        List<Integer> list = solution.diffWaysToCompute("300");

        System.out.println("list = " + list);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //需要初始化
        private final HashMap<String, List<Integer>> memory = new HashMap<>();

        public List<Integer> diffWaysToCompute(String expression) {

            if (expression.length() == 0) {
                return new ArrayList<Integer>();
            }

            if (memory.containsKey(expression)) {
                return memory.get(expression);
            }

            int len = expression.length();
            List<Integer> res = new ArrayList<Integer>();

            //考虑是全数字的情况
            int sum = 0;
            int index = 0;
            //小细节啊小细节，先判断谁，后判断谁，index越界！！！ 终止循环时index=len charAt已经越界啦
//            while (!isOperation(expression.charAt(index)) && index < len) {
            while (index < len && !isOperation(expression.charAt(index))) {
                sum = sum * 10 + expression.charAt(index) - '0';
                index++;
            }
            //将全数字的情况直接返回
            if (index == len) {
                res.add(sum);
                memory.put(expression, res);
                return res;
            }

            for (int i = 0; i < len; i++) {
                char curChar = expression.charAt(i);
                //通过运算符将字符串分成两部分
                if (isOperation(curChar)) {
                    List<Integer> leftList = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> rightList = diffWaysToCompute(expression.substring(i + 1));
                    //将两个结果依次运算
                    for (Integer l : leftList) {
                        for (Integer r : rightList) {
                            res.add(calculate(curChar, l, r));
                        }
                    }
                }
            }

            memory.put(expression, res);
            return res;


        }

        private int calculate(char c, int num1, int num2) {
            switch (c) {
                case '+':
                    return num1 + num2;
                case '-':
                    return num1 - num2;
                case '*':
                    return num1 * num2;
            }
            return -1;

        }

        private boolean isOperation(char c) {
            return c == '+' || c == '-' || c == '*';
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
