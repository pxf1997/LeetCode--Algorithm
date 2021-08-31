import org.junit.Test;

import java.util.Arrays;

/**
 * @author pxf
 * @create 2021-05-25 16:57
 */
public class 随手测试 {
    @Test
    public void test1() {
        //数组的浅拷贝，a，b两个引用指向同一个数组
        int[] a = {1, 2, 3, 4, 5};
        int[] b = a;

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a==b = " + (a == b));
        System.out.println("Arrays.equals(a, b) = " + Arrays.equals(a, b));



    }
    @Test
    public void test2() {

        //数组的深拷贝，a，b两个引用指向同一个数组
        int[] a = {1,2,3,4,5};
        int[] b = new int[5];

        /**
         * System.arraycopy(src, srcPos, dest, destPos, length);
         * src：源数组
         * srcPos：源数组中拷贝的起始位置
         * dest：目标数组
         * destPos：目标数组中开始存放的位置
         * length：拷贝的长度
         */
        System.arraycopy(a, 0, b, 0, a.length);

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        a.equals(b);
        System.out.println("a==b = " + (a == b));
        System.out.println("Arrays.equals(a, b) = " + Arrays.equals(a, b));



    }
}
