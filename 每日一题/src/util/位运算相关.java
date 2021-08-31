package util;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-05-27 13:51
 */
public class 位运算相关 {
    /**
     * 判断是否是 x 的幂
     */
    public boolean isPowerOfX(int num, int x) {
//        if (num <= 0) {
//            return false;
//        }
        int exponent = 0;
        while (num % x == 0) {
            num /= x;
            exponent++;
        }
        System.out.println("exponent = " + exponent);
        return num == 1;
    }

    /**
     * 统计 num 的二进制串长度
     */
    public int getBitLength(int num) {
        int idx = 0;
        int temp = num;
        while (temp != 0) {
            temp >>>= 1; //无符号右移,左侧补0
            idx++;
        }
        System.out.println("Integer.toBinaryString(" + num + ") = " + Integer.toBinaryString(num));
        System.out.println("位数idx = " + idx);
        return idx;
    }

    public void printBitString(int num){
        System.out.println("Integer.toBinaryString(" + num + ") = " + Integer.toBinaryString(num));
    }

    /**
     * 字符串出现了哪些字母,用最多26位的二进制数表示
     */
    public int letterAppearance(String word){
        int res = 0;
        for (char c : word.toCharArray()) {
            res |= 1 << (c - 'a'); // 字母c 左移 c-'a' 位
        }
        System.out.println(word + ":" + Integer.toBinaryString(res));
        return res;
    }

    @Test
    public void test1() {
        boolean res = isPowerOfX(-8, -2);
        System.out.println("res = " + res);
    }
    @Test
    public void test2() {
        int bitLength = getBitLength(254);
        System.out.println("bitLength = " + bitLength);

        int a = letterAppearance("abcdezzzq");
        System.out.println("a = " + a);

    }
}

