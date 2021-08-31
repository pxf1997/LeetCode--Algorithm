package util;


import org.junit.Test;

/**
 * @author pxf
 * @create 2021-05-11 17:06
 */
public class Hex_converter {
    public static String convertToBaseX(int num, int x) {
        if (num == 0) {
            return "0";
        }
        boolean isNegative = (num < 0);
        if (isNegative) {
            num = -num;
        }
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.append(num % x);
            num /= x;
        }
        String res = str.reverse().toString();
        return (isNegative ? "-" + res : res);
    }


    @Test
    public void testConvert1() {
        String s = convertToBaseX(256, 2);
        System.out.println("s = " + s);

        String s1 = Integer.toString(-255, 16);
        System.out.println("s1 = " + s1);
    }
}