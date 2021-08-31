package leetcode.editor.cn;

import java.util.Scanner;

/**
 * @author pxf
 * @create 2021-06-01 11:59
 */
public class helper_题目类改名_创建Solution类 {
    public static void main(String[] args) {
        while (true) {
            // 特殊符号--左右角括号  「」
            Scanner sc = new Scanner(System.in);
            String oldName = sc.nextLine();

//        String oldName = "剑指 Offer 34. 二叉树中和为某一值的路径";
            String newName = rename(oldName);
            System.out.println(newName);

            String build = buildNewSolution(newName);
            System.out.println(build);
        }
    }

    private static String buildNewSolution(String className) {
        String res = "Solution solution = new " + className + "().new Solution();";
        return res;
    }

    private static String rename(String oldName) {
        char[] chars = oldName.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                if (chars[i + 1] == 'O') { // O前的空格去掉
                    continue;
                } else if (isNumber(chars[i + 1])) { // 数字前的空格变下划线
                    sb.append('_');
                }
            } else if (chars[i] == '.') { // 点变下划线
                sb.append('_');
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    private static boolean isNumber(char c) {
        return (c - '0' <= 9) && (c - '0' >= 0);
    }
}
