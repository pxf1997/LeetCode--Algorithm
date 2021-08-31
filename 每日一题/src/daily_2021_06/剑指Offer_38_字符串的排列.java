/**
 * 题目Id：剑指 Offer 38
 * 题目：字符串的排列
 * 日期：2021-06-16 15:42:38
 */
//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 288 👎 0


package daily_2021_06;

//字符串的排列

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 剑指Offer_38_字符串的排列 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new 剑指Offer_38_字符串的排列().new Solution();
        String[] permutation = solution.permutation("aab");
//        String[] permutation = solution.permutation("abc");
        System.out.println("permutation = " + Arrays.toString(permutation));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)

    // 排列,不能区分重复元素 例如"aab" 重复的a视为同一个
    // HashSet去重理论可行,但不用
    class Solution_old {
        List<String> list = new ArrayList<>();

        public String[] permutation(String s) {
            char[] chars = s.toCharArray();
            boolean[] visited = new boolean[s.length()];
            List<Character> path = new ArrayList<>();
            // 去重需要排序
            Arrays.sort(chars);
            backtracking(chars, visited, path);

            String[] res = new String[list.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        private void backtracking(char[] chars, boolean[] visited, List<Character> path) {
            // 递归终止
            if (path.size() == chars.length) {
                list.add(make_String(path));
            }
            for (int i = 0; i < chars.length; i++) {
                if (visited[i]) {
                    continue;
                }
                // 去重
                if (i > 0 && (chars[i] == chars[i - 1]) && !visited[i - 1]) {
                    continue;
                }
                path.add(chars[i]);
                visited[i] = true;
                System.out.println("递归之前 => " + path);

                backtracking(chars, visited, path);

                path.remove(path.size() - 1);
                visited[i] = false;
                System.out.println("递归之后 => " + path);
            }
        }

        private String make_String(List<Character> path) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            return sb.toString();
        }
    }

    // 重新练习
    class Solution {
        List<String> res_list = new ArrayList<>();

        public String[] permutation(String s) {
            char[] chars = s.toCharArray();
            List<Character> path = new ArrayList<>();
            boolean[] visited = new boolean[chars.length];
            //"aab" 重复的a视为同一个 需要进行去重
            Arrays.sort(chars);

            backtracking(chars, path, visited);

            return res_list.toArray(new String[res_list.size()]);
        }

        // 排列--用visited,不关心起点begin(组合)
        private void backtracking(char[] chars, List<Character> path, boolean[] visited) {
            // 递归结束
            if (path.size() == chars.length) {
                System.out.println("递归终止:" + path);
                res_list.add(make_string(path));
            }
            for (int i = 0; i < chars.length; i++) {
                // 去重(剪枝)
                if (visited[i]) {
                    continue;
                }
                if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                    continue;
                }
                // 套路
                path.add(chars[i]);
                visited[i] = true;
                System.out.println("递归之前 => " + path);

                backtracking(chars, path, visited);

                path.remove(path.size() - 1);
                visited[i] = false;
                System.out.println("递归之后 => " + path);
            }
        }

        private String make_string(List<Character> path) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
