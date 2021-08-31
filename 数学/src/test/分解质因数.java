package test;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-08-18 19:17
 */
public class 分解质因数 {
    //因数分解函数
    static void decompose(int n) {
        System.out.print(n + "=");
        for (int i = 2; i <= n; i++) {
            while (n % i == 0 && n != i) {
                n /= i;
                System.out.print(i + "*");
            }
            if (n == i) {
                System.out.print(i);
                break;
            }
        }
    }

    @Test
    public void test1() {
        decompose(2197);
    }
}
