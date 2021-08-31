/**
 * 题目Id：剑指 Offer 17
 * 题目：打印从1到最大的n位数
 * 日期：2021-06-16 17:50:47
 */
//输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。 
//
// 示例 1: 
//
// 输入: n = 1
//输出: [1,2,3,4,5,6,7,8,9]
// 
//
// 
//
// 说明： 
//
// 
// 用返回一个整数列表来代替打印 
// n 为正整数 
// 
// Related Topics 数学 
// 👍 129 👎 0


package leetcode.editor.cn;

//打印从1到最大的n位数

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 剑指Offer_17_打印从1到最大的n位数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_17_打印从1到最大的n位数().new Solution();
//        int[] printNumbers = solution.printNumbers(3);
//        int[] printNumbers = solution.printNumbers(5);
//        System.out.println("printNumbers = " + Arrays.toString(printNumbers));


        Solution2 solution2 = new 剑指Offer_17_打印从1到最大的n位数().new Solution2();
        List<String> printNumbers2 = solution2.printNumbers(7);// 20位数字也要允许超级久
        System.out.println("printNumbers2 = " + printNumbers2);

    }


    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    // 不考虑大数
    class Solution1 {
        // 朴素--不考虑大数
        public int[] printNumbers(int n) {
            int size = (int) (Math.pow(10, n) - 1);
            int[] res = new int[size];
            for (int i = 0; i < size; i++) {
                res[i] = i + 1;
            }
            return res;
        }
    }

    // 考虑大数--生成全排列
    class Solution {
        List<String> list = new ArrayList<>();
        char[] dict = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        public int[] printNumbers(int n) {
            char[] path = new char[n];

            dfs(path, 0, n);

            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = Integer.parseInt(list.get(i));
            }
            return res;
        }

        // 去除先导零 例如 001写成1
        public void dfs(char[] path, int beginIndex, int n) {
            // 1--递归终止条件
            if (beginIndex == n) {
                int restart = 0;//设置一个重启点，去截取第一个非零数据
                for (int i = 0; i < path.length; i++) {
                    if (path[i] == '0') {
                        restart++;
                    } else {
                        break;
                    }
                }
                if (restart != path.length) {
                    list.add(String.valueOf(path).substring(restart, path.length));
                }
                return;
            }
            // 2--递归深入
            for (char c : dict) {
                path[beginIndex] = c;
                dfs(path, beginIndex + 1, n);
            }
        }
    }

    // 返回值为 String列表--更符合逻辑
    class Solution2 {
        List<String> list = new ArrayList<>();
        char[] dict = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        public List<String> printNumbers1(int n) {
            char[] path = new char[n];

            dfs(path, 0, n);

            return list;
        }

        // 去除先导零 例如 001写成1
        public void dfs(char[] path, int beginIndex, int n) {
            // 1--递归终止条件
            if (beginIndex == n) {
                int restart = 0;//设置一个重启点，去截取第一个非零数据
                for (int i = 0; i < path.length; i++) {
                    if (path[i] == '0') {
                        restart++;
                    } else {
                        break;
                    }
                }
                if (restart != path.length) {
                    list.add(String.valueOf(path).substring(restart, path.length));
                }
                return;
            }
            // 2--递归深入
            for (char c : dict) {
                path[beginIndex] = c;
                dfs(path, beginIndex + 1, n);
            }
        }


        public List<String> printNumbers(int n) {
            BigInteger pow = BigInteger.valueOf(10).pow(n);
            BigInteger size = pow.subtract(BigInteger.ONE);
            // 大数的while循环
            BigInteger cur = size;
            while(cur.compareTo(BigInteger.ZERO) > 0){
                list.add(cur.toString());
                cur = cur.subtract(BigInteger.ONE);
            }

            return list;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
