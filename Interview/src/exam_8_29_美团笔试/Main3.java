package exam_8_29_美团笔试;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pxf
 * @create 2021-08-29 10:14
 */
public class Main3 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Solution();
        // case1
        String s = "meituan";
//        String target = "uta";
        String target = "uuu";
        int res = solution.func(s, target);
        System.out.println(res);

        // 数据流
//        Scanner sc = new Scanner(System.in);
//        List<String> list = new ArrayList<String>();
//        while (sc.hasNext()) {
//            String str = sc.nextLine();
//            list.add(str);
//        }
//        sc.close();
//        System.out.println("list = " + list);
//        String s = list.get(0);
//        String target = list.get(1);
//        int res = solution.func(s, target);
//        System.out.println(res);

    }

    static class Solution {

        public int func(String s, String target) {
            // target串中某些字符在s中没有 --> 无法生成
            // 换言之,target串中全部字符在s中存在 --> 一定可以生成
            if (!check(s, target)) {
                return -1;
            }
            // 循环生成的字符串
            StringBuilder sb = new StringBuilder();
            int index_s = 0;
            int index_target = 0;// target当前下标

            // 模拟循环输出字符串
            while (true) {
                sb.append(s.charAt(index_s % s.length()));
                index_s++;
                // 新添加的是所需要的
                if (sb.charAt(sb.length() - 1) == target.charAt(index_target)) {
                    index_target++;
                    if (index_target == target.length()) break;
                }
            }
            System.out.println("sb = " + sb);
            return sb.length() - target.length();
        }

        private boolean check(String s, String target) {
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                set.add(c);
            }
            for (char c : target.toCharArray()) {
                if (!set.contains(c)) return false;
            }
            return true;
        }
    }
}
// 输入描述
//第一行一个长为 n 的字符串 s，表示这个循环出现的随机字符串。
//
//第二行一个长为 m 的字符串 a，表示小团想要生成的字符串。
//
//对于 30% 的数据，1≤n, m≤103。
//
//对于另外 20% 的数据，保证 s 串中 ASCII 码大的字母均出现在 ASCII 码小的字母之后。
//
//对于全部数据，1≤n, m≤105，保证字符串 s 与 a 只包含小写英文字母。
//
//输出描述
//输出一行一个整数，如果不能生成字符串 a，则输出 -1，否则输出恰好生成完这个字符串时，浪费了这个流的多少个字符。
