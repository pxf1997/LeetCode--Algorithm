/**
 * 题目Id：127
 * 题目：单词接龙
 * 日期：2021-04-28 22:43:40
 */
//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列： 
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 
// 👍 748 👎 0


package leetcode.editor.cn;

//单词接龙

import java.util.*;

public class P127_WordLadder {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P127_WordLadder().new Solution();
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int res = solution.ladderLength("hit", "cog", Arrays.asList(words));
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int ladderLength_1(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            HashSet<String> visited = new HashSet<String>();
            Queue<String> queue = new LinkedList<String>();
            int path = 0;
            queue.add(beginWord);
            visited.add(beginWord);

            while (!queue.isEmpty()) {
                int size = queue.size();
                path++;
                //一层出队--分别逻辑处理，会入队下一层
                while (size-- > 0) {
                    String cur = queue.poll();
//                    System.out.println("出队" + cur);
                    for (String word : wordList) {
//                        跳过----已经遍历、不能转换
                        if (visited.contains(word)) {
                            continue;
                        }
                        if (!canConVert(cur, word)) {
                            continue;
                        }
//                        保存访问过的单词，同时把单词放进队列，用于下一层的访问
//                        System.out.println("  " + path + ": " + cur + "->" + word);
                        visited.add(word);
                        queue.add(word);

                        if (word.equals(endWord)) {
                            return path + 1;
                        }
                    }
                }

            }
            return 0;
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            // visited 修改为 boolean 数组
            boolean[] visited = new boolean[wordList.size()];
            int idx = wordList.indexOf(beginWord);
            if (idx != -1) {
                visited[idx] = true;
            }
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            int path = 0;

            while (!queue.isEmpty()) {
                path++;
                int size = queue.size();
                while (size-- > 0) {
                    String cur = queue.poll();
//                    System.out.println("出队:" + cur);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (visited[i]) {
                            continue;
                        }
                        String word = wordList.get(i);
                        if (!canConVert(cur, word)) {
                            continue;
                        }
                        visited[i] = true;
                        queue.add(word);
                        // 用于调试
//                        System.out.println("  " + path + ": " + cur + "->" + word);
                        if (word.equals(endWord)) {
                            return path + 1;
                        }
                    }
                }
            }
            return 0;

        }

        private boolean canConVert(String word1, String word2) {
            if (word1.length() != word2.length()) {
                return false;
            }
            int count = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    count++;
                    if (count >= 2) {
                        return false;
                    }
                }
            }

            return count == 1;
        }
    }

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            int end = wordList.indexOf(endWord);
            if (end == -1) {
                return 0;
            }
            wordList.add(beginWord);
            int start = wordList.size() - 1;
//             用于 BFS 遍历的队列
            Queue<Integer> queue1 = new LinkedList<>();
            Queue<Integer> queue2 = new LinkedList<>();
//              用于保存已访问的单词
            Set<Integer> visited1 = new HashSet<>();
            Set<Integer> visited2 = new HashSet<>();

            queue1.offer(start);
            queue2.offer(end);
            visited1.add(start);
            visited2.add(end);
            int count1 = 0;
            int count2 = 0;
//            双向BFS--也有套路，对比基础BFS
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                count1++;
                int size1 = queue1.size();
                while (size1-- > 0) {
                    String s = wordList.get(queue1.poll());
                    for (int i = 0; i < wordList.size(); ++i) {
                        if (visited1.contains(i)) {
                            continue;
                        }
                        if (!canConvert(s, wordList.get(i))) {
                            continue;
                        }
                        if (visited2.contains(i)) {
                            return count1 + count2 + 1;
                        }
                        visited1.add(i);
                        queue1.offer(i);
                    }
                }

                count2++;
                int size2 = queue2.size();
                while (size2-- > 0) {
                    String s = wordList.get(queue2.poll());
                    for (int i = 0; i < wordList.size(); ++i) {
                        if (visited2.contains(i)) {
                            continue;
                        }
                        if (!canConvert(s, wordList.get(i))) {
                            continue;
                        }
                        if (visited1.contains(i)) {
                            return count1 + count2 + 1;
                        }
                        visited2.add(i);
                        queue2.offer(i);
                    }
                }
            }
            return 0;
        }

        public boolean canConvert(String a, String b) {
            int count = 0;
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) != b.charAt(i)) {
                    if (++count > 1) return false;
                }
            }
            return count == 1;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
