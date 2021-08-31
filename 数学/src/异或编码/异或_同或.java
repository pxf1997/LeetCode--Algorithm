package 异或编码;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-05-11 19:54
 */
public class 异或_同或 {
    public static String XOR_encode(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            char s1 = s.charAt(i);
            char s2 = s.charAt(i + 1);
            int cur = s1 ^ s2;
            System.out.println("s[i]=" + s1 + "  s[i+1]=" + s2 + "  cur=" + cur);
            sb.append(cur).append(",");
        }
        return sb.toString();
    }

    public static String XOR_decode(String s, char start) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);

        List<Integer> valList = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != ',') {
                cur.append(c);
            } else {
                valList.add(Integer.parseInt(cur.toString()));
                cur.setLength(0);
            }
        }
        System.out.println("valList = " + valList);
        for (int i = 0; i < valList.size(); i++) {
            int encode_i_1 = valList.get(i);
            int arr_i_1 = sb.charAt(sb.length() - 1);
            int arr_i = encode_i_1 ^ arr_i_1;
            System.out.println("encode_i_1=" + encode_i_1 + "  arr_i_1" + arr_i_1 + "  arr_i=" + arr_i + "  解码arr_i=" + (char)arr_i);
            sb.append((char) arr_i);
        }
        return sb.toString();

    }

    /**
     * @param a int型数据
     * @param b int型数据
     * @return 同或运算=异或运算^1  即(A^B)^1
     */
    public static int XNOR(int a, int b) {
        String s1 = Integer.toBinaryString(a);
        String s2 = Integer.toBinaryString(b);
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        StringBuilder sb = new StringBuilder();
        int maxLen = Math.max(s1.length(), s2.length());

        for (int i = 0; i < maxLen; i++) {
            int index1 = s1.length() - 1 - i;
            int index2 = s2.length() - 1 - i;
            int cur1 = (index1 >= 0 ? s1.charAt(index1) - '0' : 0);
            int cur2 = (index2 >= 0 ? s2.charAt(index2) - '0' : 0);
            //按位异或后再异或1----(A^B)^1
            int cur = cur1 ^ cur2 ^ 1;
            //helper
            System.out.println("cur1=" + cur1 + "  cur2=" + cur2 + "  cur=" + cur);
            sb.append(cur);

        }
        String res = sb.reverse().toString();
        System.out.println("res = " + res);
        return Integer.parseInt(res, 2); //X进制的字符串转10进制
    }


    @Test
    public void test1() {
        String encode = XOR_encode("屎币shiba INU");
        System.out.println("encode = " + encode);

        String decode = XOR_decode(encode, '屎');
        System.out.println("decode = " + decode);

    }

    @Test
    public void test_XOR_1() {
        System.out.println("112^120 = " + (112 ^ 120));
        System.out.println("Integer.toBinaryString(112) = " + Integer.toBinaryString(112));
        System.out.println("Integer.toBinaryString(120) = " + Integer.toBinaryString(120));
        System.out.println("Integer.toBinaryString((112 ^ 120)) = " + Integer.toBinaryString((112 ^ 120)));
    }

    @Test
    public void test_XOR_2() {
        System.out.println("112^120^120 = " + (112 ^ 120 ^ 120));
        System.out.println("120^120 = " + (120 ^ 120));
        System.out.println("112^0 = " + (112 ^ 0));
        System.out.println("120^112^120 = " + (120 ^ 112 ^ 120));
    }

    @Test
    public void test_XNOR_1() {
        System.out.println("112^120 = " + (112 ^ 120));
        System.out.println("Integer.toBinaryString(112) = " + Integer.toBinaryString(112));
        System.out.println("Integer.toBinaryString(120) = " + Integer.toBinaryString(120));
        System.out.println("Integer.toBinaryString((112 ^ 120)) = " + Integer.toBinaryString((112 ^ 120)));
        //同或
        System.out.println("112 ^ 120 ^ 127 = " + (112 ^ 120 ^ 127));
        System.out.println("Integer.toBinaryString((112 ^ 120 ^ 127)) = " + Integer.toBinaryString((112 ^ 120 ^ 127)));
        int xnor = XNOR(112, 120);
        System.out.println("xnor = " + xnor);
    }

    @Test
    public void test_XNOR_2() {
        int base = XNOR(197, 197);
        System.out.println("位数相同的全1数字base = " + base);
        System.out.println();

        int temp = XNOR(125, base);
        System.out.println("temp = " + temp);
        System.out.println();

    }


}