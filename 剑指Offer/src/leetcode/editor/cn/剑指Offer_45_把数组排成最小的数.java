/**
 * 题目Id：剑指 Offer 45
 * 题目：把数组排成最小的数
 * 日期：2021-06-02 09:39:47
 */
//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。 
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 排序 
// 👍 226 👎 0


package leetcode.editor.cn;

//把数组排成最小的数


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 剑指Offer_45_把数组排成最小的数 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_45_把数组排成最小的数().new Solution();
        String minNumber = solution.minNumber(new int[]{3, 30, 34, 5, 9});
//        String minNumber = solution.minNumber(new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8, 9});
        System.out.println("minNumber = " + minNumber);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 回溯--遍历所有可行排列
    // 结论--超时 0 < nums.length <= 100
    class Solution1 {
        String min;
        boolean isFirst = false;
        List<String> resList = new ArrayList<>();

        public String minNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return "";
            }
            if (nums.length == 1) {
                return String.valueOf(nums[0]);
            }
            List<StringBuilder> path = new ArrayList<>();
            boolean[] visited = new boolean[nums.length];

            backtracking(nums, path, visited);
            System.out.println("resList.size() = " + resList.size());
            return min;
        }

        private void backtracking(int[] nums, List<StringBuilder> path, boolean[] visited) {

            if (path.size() == nums.length) {
                String cur = connectString(path);
                resList.add(cur);
//                System.out.println("递归终止:" + path);
                if (!isFirst) {
                    isFirst = true;
                    min = cur;
                } else {
                    // 字符串比较大小--取小的
                    min = (min.compareTo(cur) < 0 ? min : cur);
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                path.add(sb);
                visited[i] = true;

                backtracking(nums, path, visited);

                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }

        private String connectString(List<StringBuilder> path) {
            StringBuilder sb1 = new StringBuilder();
            for (StringBuilder sb : path) {
                sb1.append(sb);
            }
            return sb1.toString();
        }
    }

    // 参考题解
    class Solution {
        public String minNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++)
                strs[i] = String.valueOf(nums[i]);

            // lamda表达式--我不会!
//            Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));

            // 定制排序--逻辑:看题解传递性的证明
            Arrays.sort(strs, new Comparator<String>() {
                public int compare(String x, String y) {
                    return (x + y).compareTo(y + x); // 两串拼起来,谁小取谁
                }
            });
            System.out.println("strs = " + Arrays.toString(strs));

            StringBuilder res = new StringBuilder();
            for (String s : strs)
                res.append(s);
            return res.toString();
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
