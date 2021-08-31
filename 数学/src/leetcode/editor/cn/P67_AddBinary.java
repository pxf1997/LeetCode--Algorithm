/**
 * 题目Id：67
 * 题目：二进制求和
 * 日期：2021-05-11 16:42:58
 */
//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 607 👎 0


package leetcode.editor.cn;

//二进制求和

import org.junit.Test;
import util.Hex_converter;

public class P67_AddBinary {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P67_AddBinary().new Solution();
        String s = solution.addBinary("111", "11");
        System.out.println("s = " + s);

//        String s1 = solution.addX("125", "525", 8);
//        System.out.println("s1 = " + s1);
//
//
//        String s2 = solution.addX("85", "341", 10);
//        System.out.println("s2 = " + s2);
//
//        System.out.println("Hex_converter.convertToBaseX(85,8) = " + Hex_converter.convertToBaseX(85, 8));
//        System.out.println("Hex_converter.convertToBaseX(341,8) = " + Hex_converter.convertToBaseX(341, 8));
//        System.out.println("Hex_converter.convertToBaseX(426,8) = " + Hex_converter.convertToBaseX(426, 8));


    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //复杂but通用性还挺强的噻
        public String addBinary(String a, String b) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int add = 0; //进位
            //有一串没用完--继续循环
            while (i < a.length() || i < b.length()) {
                //解决二者不等长，从后往前每次相加的两个char写出来
                int indexA = a.length() - 1 - i;
                int indexB = b.length() - 1 - i;
                int ca = (indexA >= 0 ? a.charAt(indexA) : '0') - '0';
                int cb = (indexB >= 0 ? b.charAt(indexB) : '0') - '0';

                int cur = (ca + cb + add) % 2;
                add = (ca + cb + add) / 2;
                sb.append(cur);
                i++;

                //helper
                System.out.println("ca=" + ca + "  cb=" + cb + "  cur=" + cur);
                System.out.println();

            }
            if (add > 0) {
                sb.append(add);
            }
            return sb.reverse().toString();
        }

        public String addX(String a, String b, int X) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int add = 0; //进位
            //有一串没用完--继续循环
            while (i < a.length() || i < b.length()) {
                //解决二者不等长，从后往前每次相加的两个char写出来
                int indexA = a.length() - 1 - i;
                int indexB = b.length() - 1 - i;
                int ca = (indexA >= 0 ? a.charAt(indexA) : '0') - '0';
                int cb = (indexB >= 0 ? b.charAt(indexB) : '0') - '0';

                int cur = (ca + cb + add) % X;
                add = (ca + cb + add) / X;
                sb.append(cur);
                i++;

                //helper
                System.out.println("ca=" + ca + "  cb=" + cb + "  cur=" + cur + "  add=" + add);

            }
            if (add > 0) {
                sb.append(add);
            }
            return sb.reverse().toString();
        }
    }

    class Solution {
        //位运算
        public String addBinary(String a, String b) {
            return null;
        }
        //调API = 开挂
        public String addBinary_1(String a, String b) {
            //helper
            System.out.println("二进制串 a 十进制数值= " + Integer.parseInt(a, 2));
            System.out.println("二进制串 b 十进制数值= " + Integer.parseInt(b, 2));
            System.out.println("结果十进制数值 = " + (Integer.parseInt(a, 2) + Integer.parseInt(b, 2)));

            return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
