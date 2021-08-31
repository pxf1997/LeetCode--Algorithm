package 最长公共子序列LCS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static util.dp_util.print_2D;
import static util.dp_util.print_path;

/**
 * @author pxf
 * @create 2021-05-21 11:54
 */
public class 回溯_输出LCS {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        Set<String> longestCommonSubsequence = solution.longestCommonSubsequence("abcde", "ace");
//        Set<String> longestCommonSubsequence = solution.longestCommonSubsequence("xxabcdeyy", "zzzabcdewww");

        Set<String> longestCommonSubsequence = solution.longestCommonSubsequence("BDCABA", "ABCBDAB");
        System.out.println("longestCommonSubsequence = " + longestCommonSubsequence);
    }

    static class Solution {
        // LCS长度
        /*public int longestCommonSubsequence(String text1, String text2) {
            int len1 = text1.length(), len2 = text2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 0; i < len1 + 1; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i < len2 + 1; i++) {
                dp[0][i] = 0;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                        sb.append(text1.charAt(i - 1)); //记录所有斜边--多了
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }

                }
            }
//            System.out.println("sb = " + sb);
            print_2D(dp);
            return dp[len1][len2];
        }*/

        // 记录所有LCS
        public Set<String> longestCommonSubsequence(String text1, String text2) {
            // 1--LCS的dp
            int len1 = text1.length(), len2 = text2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 0; i < len1 + 1; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i < len2 + 1; i++) {
                dp[0][i] = 0;
            }
            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            print_2D(dp);

            Set<String> LCSList = new HashSet<>();
            traceBack(dp, text1, text2, len1, len2, new StringBuilder(), LCSList);
            return LCSList;
        }

        //回溯，求出所有的最长公共子序列，并放入set中
        private void traceBack(int[][] dp, String text1, String text2, int i, int j, StringBuilder path, Set<String> LCSList) {
            while (i > 0 && j > 0) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    path.append(text1.charAt(i - 1));
                    i--;
                    j--;
                } else {
                    if (dp[i][j - 1] > dp[i - 1][j])
                        j--;
                    else if (dp[i][j - 1] < dp[i - 1][j])
                        i--;
                    else {
                        // 相等的情况--这里是分叉点--向一个方向(↑ ←)递归深入后,需要还原 path
                        StringBuilder temp = new StringBuilder(path);
                        traceBack(dp, text1, text2, i - 1, j, path, LCSList);
                        path = temp;
                        traceBack(dp, text1, text2, i, j - 1, path, LCSList);
                        return;
                    }
                }
            }
            // 到这里 i==0 || j==0
            System.out.print("递归终止--找到一条路径:");
            print_path(path);
            LCSList.add(path.reverse().toString());
        }

    }
}
