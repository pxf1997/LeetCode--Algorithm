import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pxf
 * @create 2021-04-21 21:42
 */
public class makeChangeCoins {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 21};
        int res = doChange(coins, 63);
        int[] usedList = doChangeList(coins, 63);
        int[] usedCoins = printCoins(usedList, 63);

        System.out.println("总共所用硬币个数" + res);
        System.out.println("每步所用硬币分别是" + Arrays.toString(usedList));
        System.out.println("所用的硬币组合" + Arrays.toString(usedCoins));
    }


    private static int doChange(int[] coins, int key) {
        int[] dp = new int[key + 1];
        dp[0] = 0;
        for (int i = 1; i <= key; i++) {
            int min = i;
            for (int j = 0; j < coins.length && (i - coins[j] >= 0); j++) {
                min = Math.min(min, dp[i - coins[j]] + 1);
            }
            dp[i] = min;
        }

        return dp[key];
    }

    //    改进--加入一个列表记录每一次加入了哪个硬币，方便回溯得到所用硬币列表
    private static int[] doChangeList(int[] coins, int key) {
        int[] usedList = new int[key + 1];
        int[] dp = new int[key + 1];
        dp[0] = 0;
        for (int i = 1; i <= key; i++) {
            int min = i;
            int thisCoin = 1;
            for (int j = 0; j < coins.length && (i - coins[j] >= 0); j++) {
                if (min > dp[i - coins[j]] + 1) {
                    min = dp[i - coins[j]] + 1;
                    thisCoin = coins[j];
                }
            }
            dp[i] = min;
            usedList[i] = thisCoin;
        }
        return usedList;
    }

    //  打印所用硬币
    private static int[] printCoins(int[] usedList, int key) {
        List<Integer> usedCoins = new ArrayList<Integer>();
        while (key > 0) {
            int thisCoin = usedList[key];
            usedCoins.add(thisCoin);
            key -= thisCoin;
        }
        int size = usedCoins.size();
//        Integer[] res1 = usedCoins.toArray(new Integer[size]);
//        int[] res = new int[size];
//        for (int i = 0; i < size; i++) {
//            res[i] = res1[i];
//        }
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = usedCoins.get(i);

        }

        return res;
    }

    @Test
    public void List2Array() {
        List<String> a = new ArrayList<String>();
        a.add("123");
        a.add("456");
        a.add("789");
        int size = a.size();
//        String[] array = new String[size];
//        array = a.toArray(array);
        String[] array = a.toArray(new String[size]);
        System.out.println(Arrays.toString(array));
        List<String> list = Arrays.asList(array);
        System.out.println(list);

    }

    @Test
    public void List2Array2() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        int size = list.size();
        ArrayList<Integer> a = new ArrayList<Integer>();
//        int[] array1=(int[])a.toArray(new int[size]);//会报错

        Integer[] array = list.toArray(new Integer[list.size()]);//能正确运行
        for (int element : array) {
            System.out.println(element);
        }

        int[] d = new int[size];
        for (int i = 0; i < list.size(); i++) {
            d[i] = list.get(i);
        }
        System.out.println(Arrays.toString(d));
    }

    @Test
    public void test1() {
        int res = 11 & 1;
        System.out.println(res);

    }


}
