/**
 * 题目Id：405
 * 题目：数字转换为十六进制数
 * 日期：2021-05-11 09:52:18
 */
//给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。 
//
// 注意: 
//
// 
// 十六进制中所有字母(a-f)都必须是小写。 
// 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
// 给定的数确保在32位有符号整数范围内。 
// 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。 
// 
//
// 示例 1： 
//
// 
//输入:
//26
//
//输出:
//"1a"
// 
//
// 示例 2： 
//
// 
//输入:
//-1
//
//输出:
//"ffffffff"
// 
// Related Topics 位运算 
// 👍 132 👎 0


package leetcode.editor.cn;

//数字转换为十六进制数

import org.junit.Test;

public class P405_ConvertANumberToHexadecimal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P405_ConvertANumberToHexadecimal().new Solution();

        String s = solution.toHex(255);
        System.out.println("s = " + s);

        System.out.println();
        String s1 = solution.toHex(-1);
        System.out.println("s1 = " + s1);
    }

    @Test
    public void YouYi() {
        System.out.println("255 = " + Integer.toBinaryString((255)));
        System.out.println("有符号:  255>>4 = " + (255 >> 4));
        System.out.println("有符号:  255>>4 = " + Integer.toBinaryString((255 >> 4)));

        System.out.println("无符号： 255>>>4 = " + (255 >>> 4));
        System.out.println("无符号： 255>>>4 = " + Integer.toBinaryString((255 >>> 4)));

    }
    @Test
    public void YouYi2() {
        System.out.println("-255 = " + Integer.toBinaryString((-255)));

        System.out.println("有符号:  -255>>4 = " + (-255 >> 4));
        System.out.println("有符号:  -255>>4 = " + Integer.toBinaryString((-255 >> 4)));

        System.out.println("无符号： -255>>>4 = " + (-255 >>> 4));
        System.out.println("无符号： -255>>>4 = " + Integer.toBinaryString((-255 >>> 4)));
    }
    @Test
    public void YouYi3() {
        System.out.println("-16 = " + Integer.toBinaryString((-16)));
//        System.out.println("length = " + Integer.toBinaryString((-16)).length());

        System.out.println("有符号:  -16>>4 = " + (-16 >> 4));
        System.out.println("有符号:  -16>>4 = " + Integer.toBinaryString((-16 >> 4)));

        System.out.println("无符号： -16>>>4 = " + (-16 >>> 4));
        System.out.println("无符号： -16>>>4 = " + Integer.toBinaryString((-16 >>> 4)));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String toHex(int num) {
            char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//            char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            if (num == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                // 后四位 & 1111 = 提取出后四位二进制数值
                int index = num & 0b1111;
                //helper
                System.out.println("num = " + Integer.toBinaryString(num));
                System.out.println("后四位二进制 = "+Integer.toBinaryString(index));
                System.out.println("num & 0b1111 = " + index);
                System.out.println();

                char c = map[index];
                sb.append(c);
                num >>>= 4; // 因为考虑的是补码形式，因此符号位就不能有特殊的意义，需要使用无符号右移，左边填 0
            }
            return sb.reverse().toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
