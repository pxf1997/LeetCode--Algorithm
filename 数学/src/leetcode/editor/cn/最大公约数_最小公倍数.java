package leetcode.editor.cn;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-05-10 17:49
 */
public class 最大公约数_最小公倍数 {
    public static void main(String[] args) {
    }

    public static int lcm(int a, int b) { // 直观理解--a大、b小
//        System.out.println("a=" + a + "  b=" + b);
        return a * b / gcd(a, b); //必然不对---要缩小递归的规模啊
    }

    public static int gcd(int a, int b) { // 直观理解--a大、b小
        System.out.println("a=" + a + "  b=" + b);
        return (b == 0 ? a : gcd(b, a % b));
    }

    public static int gcd2(int a, int b) {
//        对于 a 和 b 的最大公约数 f(a, b)，有：
//        如果 a 和 b 均为偶数，f(a, b) = 2*f(a/2, b/2);
//        如果 a 是偶数 b 是奇数，f(a, b) = f(a/2, b);
//        如果 b 是偶数 a 是奇数，f(a, b) = f(a, b/2);
//        如果 a 和 b 均为奇数，f(a, b) = f(b, a-b);
//        乘 2 和除 2 都可以转换为移位操作。
        if (a < b) {
            return gcd2(b, a);
        }
        if (b == 0) {
            return a;
        }
        boolean isAEven = isEven(a), isBEven = isEven(b);
        if (isAEven && isBEven) {
            return 2 * gcd2(a >> 1, b >> 1);
        } else if (isAEven && !isBEven) {
            return gcd2(a >> 1, b);
        } else if (!isAEven && isBEven) {
            return gcd2(a, b >> 1);
        } else {
            return gcd2(b, a - b);
        }
    }

    public static boolean isEven(int a) {
        return a % 2 == 0;
    }

    @Test
    public void test1() {
        int res1 = gcd(99, 77);
        System.out.println("res1 = " + res1);
        System.out.println();
        int res2 = lcm(99, 77);
        System.out.println("res2 = " + res2);
    }

    @Test
    public void test2() {
        int res1 = gcd2(99, 77);
        System.out.println("res1 = " + res1);
        System.out.println();
    }


}
