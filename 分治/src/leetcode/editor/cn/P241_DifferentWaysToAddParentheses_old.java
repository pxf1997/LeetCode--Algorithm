/**
 * 题目Id：241
 * 题目：为运算表达式设计优先级
 * 日期：2021-04-26 19:30:52
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

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P241_DifferentWaysToAddParentheses_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P241_DifferentWaysToAddParentheses_old().new Solution();
        List<Integer> list = solution.diffWaysToCompute("2*3-4*5");

//        List<Integer> list = solution.diffWaysToCompute("300");

        System.out.println("list = " + list);

    }

    //    测试包装类和String的相互转换
    @Test //String转包装类
    public void String2Integer() {
        Integer num1 = Integer.valueOf(127);
        Integer num2 = Integer.valueOf(127);
        boolean b1 = num1.equals(num2);
        System.out.println("b1 = " + b1);

        String str = "10";
        Integer res1 = Integer.valueOf(str);
        System.out.println("res1 = " + res1);
        int res2 = Integer.parseInt(str);
        System.out.println("res2 = " + res2);

        boolean b = res1 == res2;

    }

    @Test //String转包装类
    public void String2Integer_1() {
        Double aDouble = Double.valueOf("12.66");//返回Double包装类型
        double aDouble1 = Double.parseDouble("12.66");//返回double基本数据类型

        Long aLong = Long.valueOf("123456");//返回Long包装类型
        long parseLong = Long.parseLong("123456");//返回long基本数据类型

        Integer integer = Integer.valueOf("123");///返回Integer包装类型
        int i = Integer.parseInt("123");//返回int基本数据类型
    }


    @Test //包装类转String
    public void Integer2String() {
        String s = Integer.toString(100);
        System.out.println("s = " + s);

        String s1 = Long.toString(10000);
        System.out.println("s1 = " + s1);

        String s2 = Double.toString(26.66);
        System.out.println("s2 = " + s2);

    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    //        解法一：分治递归+记忆化
    class Solution1 {
        //        改进--记忆化memoization（记录算过的记录）添加一个 map
        HashMap<String, List<Integer>> map = new HashMap<>();

        public List<Integer> diffWaysToCompute(String expression) {
//            本题解采用了 分治 的思想：
//            1--遍历 字符串
//            2--遇到操作符，就将左右两边的字符串，分别当作两个表达式


            if (expression == null || expression.length() == 0)
                return new ArrayList<Integer>();

            //如果已经有当前解了，直接返回
            if (map.containsKey(expression)) {
                return map.get(expression);
            }

//            List<Character> operators = new ArrayList<Character>(Arrays.asList('+', '-', '*'));
            List<Integer> res = new ArrayList<>();
            int len = expression.length();
            for (int i = 0; i < len; i++) {
                char c = expression.charAt(i);
                if (c == '+' || c == '-' || c == '*') {
                    List<Integer> leftList = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> rightList = diffWaysToCompute(expression.substring(i + 1));
//                    左右子集中各选一个---双重循环
                    for (Integer leftNum : leftList) {
                        for (Integer rightNum : rightList) {
                            if (c == '+') {
                                res.add(leftNum + rightNum);
                            } else if (c == '-') {
                                res.add(leftNum - rightNum);
                            } else {
                                res.add(leftNum * rightNum);
                            }
                        }
                    }
                }
            }
//            特例--没有操作符号（也是递归终止条件），就一个数字例如 300
            if (res.isEmpty()) {
                res.add(Integer.parseInt(expression));
            }

            map.put(expression, res);
            return res;
        }
    }

    //    解法二：dp
    class Solution {
        //            0--分析：2 * 3 - 4 * 5
//              存起来的数字是 numList = [2 3 4 5]，
//              存起来的运算符是 opList = [*, -, *]。
//            1--dp[i][j] 定义：含义是第 i 到第 j 个数字（从 0 开始计数）范围内的表达式的所有解。
//              举个例子，2 * 3 - 4 * 5
//              dp[1][3] 就代表第一个数字 3 到第三个数字 5 范围内的表达式 3 - 4 * 5 的所有解。
//            2--初始化条件：2 * 3 - 4 * 5
//            dp[0][0] = [2]，dp[1][1] = [3]，dp[2][2] = [4]，dp[3][3] = [5]。
        public List<Integer> diffWaysToCompute(String input) {

            List<Integer> numList = new ArrayList<>();
            List<Character> opList = new ArrayList<>();
            char[] array = input.toCharArray();
            int num = 0;
            for (char c : array) {
                if (isOperation(c)) {
                    numList.add(num);
                    num = 0;
                    opList.add(c);
                    continue;
                }
                num = num * 10 + c - '0';
            }
            numList.add(num);
            int N = numList.size(); // 数字的个数

            // 一个数字
            ArrayList<Integer>[][] dp = (ArrayList<Integer>[][]) new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> result = new ArrayList<>();
                result.add(numList.get(i));
                dp[i][i] = result;
            }
            dp_util.print_DP_2(dp);
            System.out.println();

            // 2 个数字到 N 个数字
            for (int n = 2; n <= N; n++) {
                // 开始下标
                for (int i = 0; i < N; i++) {
                    // 结束下标
                    int j = i + n - 1;
                    if (j >= N) {
                        break;
                    }
                    ArrayList<Integer> result = new ArrayList<>();
                    // 分成 i ~ s 和 s+1 ~ j 两部分
                    for (int s = i; s < j; s++) {
                        ArrayList<Integer> result1 = dp[i][s];
                        ArrayList<Integer> result2 = dp[s + 1][j];
                        for (int x = 0; x < result1.size(); x++) {
                            for (int y = 0; y < result2.size(); y++) {
                                // 第 s 个数字下标对应是第 s 个运算符
                                char op = opList.get(s);
                                result.add(caculate(result1.get(x), op, result2.get(y)));
                            }
                        }
                    }
                    dp[i][j] = result;
                }
            }

            dp_util.print_DP_2(dp);
            System.out.println();

            return dp[0][N - 1];

        }

        private int caculate(int num1, char c, int num2) {
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
