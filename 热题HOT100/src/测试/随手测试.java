package 测试;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-06-07 17:54
 */
public class 随手测试 {
    public static void main(String[] args) {
        System.out.println("(char)1 = " + (char) 1);
        System.out.println("'1'-'0' = " + ('1' - '0'));
    }

    @Test
    public void test1() {
        double a = Math.pow(2, 3) - 1;
        System.out.println("a = " + a);
        System.out.println("a==7 = " + (a == 7));

    }

    @Test
    public void test2() {
        // 默认是 \u0000
        char a = ' ';
        char b = '\u0000';
        char[] c = new char[10];
        System.out.println("c[0] == a = " + (c[0] == a));
        System.out.println("c[0] == b = " + (c[0] == b));

        System.out.println("a==b = " + (a == b));
    }

    @Test
    public void test3() {
        // 0x7fffffff (7个f)  0x80000000
        // 计算为 2^3 * (2^4)^7 = 2^31 2的31次方
        System.out.println("0x7fffffff = " + 0x7fffffff);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Math.pow(2,31) - 1 = " + (int) (Math.pow(2, 31) - 1));
        System.out.println("1e9+7 = " + (1e9 + 7));
    }

    @Test
    public void test4() {
        char c1 = '\0';
        char c2 = ' ';
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
    }


}
