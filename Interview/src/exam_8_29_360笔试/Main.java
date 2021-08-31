package exam_8_29_360笔试;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        //测试代码
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        while (sc.hasNext()) {
            String str = sc.nextLine();
            list.add(str);
        }
        sc.close();
        String line0 = list.get(0);
        String line1 = list.get(1);

        String[] str1 = line0.split(" ");
        int n = Integer.parseInt(str1[0]);
        int p = Integer.parseInt(str1[1]);
        int q = Integer.parseInt(str1[2]);

        String[] str2 = line1.split(" ");
        int[] exam = new int[str2.length];
        for (int i = 0; i < str2.length; i++) {
            exam[i] = Integer.parseInt(str2[i]);
        }
        Solution solution = new Solution();
        int count = solution.func(n, p, q, exam);
        System.out.println(count);
    }

    static class Solution {
        int cnt = 0;

        public int func(int n, int p, int q, int[] exam) {
            // 考试分从高到低排序,尽可能给最高的平时分
            // 注意--期末考试分数更高的学生平时成绩也一定要更高。
            Arrays.sort(exam);
            int[] daily = new int[n];
            daily_helper(exam, daily);

            System.out.println("exam = " + Arrays.toString(exam));
            System.out.println("daily = " + Arrays.toString(daily));
            check(exam, daily, p, q);
            return cnt;
        }

        private void check(int[] exam, int[] daily, int p, int q) {
            for (int i = 0; i < exam.length; i++) {
                int point = (exam[i] * q + daily[i] * p) / 100;
                System.out.println("point = " + point);
                cnt += (point >= 60 ? 1 : 0);
            }
        }

        private void daily_helper(int[] exam, int[] daily) {
            int cur = 100;
            // 从高到低,尽可能多的给平时分
            for (int i = exam.length - 1; i > 0; i--) {
                daily[i] = cur;
                if (exam[i] > exam[i - 1]) {
                    cur--;
                }
            }
            daily[0] = cur;
        }
    }


}

// 又到了一学期一次的大学生期末考试。但很多人期末考试的卷面成绩是不能及格的，需要靠较高的平时成绩来拖上去。
// 平时成绩与期末考试的占比已经确定，假设平时成绩占比为p，期末考试占比为q，平时分为a，期末考试分数为b，则总成绩为(p*a+q*b)/100。
// （平时分与期末成绩都是整数，但总成绩可以是小数。）
//
// 饶老师心肠特别好，他希望自己的学生及格率尽可能的高。但他也坚持期末考试分数更高的学生平时成绩也一定要更高。饶老师想知道在这种情况下，他们班的最大及格人数是多少（及格是指总成绩不低于60分）。


