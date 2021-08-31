/**
 * 题目Id：127
 * 题目：单词接龙
 * 日期：2021-04-28 16:41:02
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
// 👍 747 👎 0


package leetcode.editor.cn;

//单词接龙

import java.util.*;

public class P127_WordLadder_old {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P127_WordLadder_old().new Solution();
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int res = solution.ladderLength("hit", "cog", Arrays.asList(words));
        System.out.println("res = " + res);
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        Map<String, Integer> wordId = new HashMap<>();
        List<List<Integer>> edge = new ArrayList<>();
        int nodeNum = 0;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            for (String word : wordList) {
                addEdge(word);
            }
            addEdge(beginWord);
            if (!wordId.containsKey(endWord)) {
                return 0;
            }
            System.out.println("wordId = " + wordId);
            System.out.println("edge = " + edge);

            int[] dis = new int[nodeNum];
            Arrays.fill(dis, 10000);
            int beginId = wordId.get(beginWord), endId = wordId.get(endWord);

            System.out.println("beginId = " + beginId);
            System.out.println("endId = " + endId);

            dis[beginId] = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(beginId);
            while (!queue.isEmpty()) {
                int x = queue.poll();
                if (x == endId) {
                    return dis[endId] / 2 + 1;
                }
                for (Integer it : edge.get(x)) {
                    if (dis[it] == 10000) {
                        dis[it] = dis[x] + 1;
                        queue.add(it);
                    }
                }
            }

            return 0;
        }

        private void addEdge(String word) {
            addWord(word);
            int id1 = wordId.get(word);
            char[] array = word.toCharArray();
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char temp = array[i];
                array[i] = '*';
                String newWord = new String(array);
                addWord(newWord);
                int id2 = wordId.get(newWord);
                edge.get(id1).add(id2);
                edge.get(id2).add(id1);
                array[i] = temp;
            }

        }

        private void addWord(String word) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, nodeNum);
                nodeNum++;
                edge.add(new ArrayList<Integer>());
            }
        }
    }

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            visited.add(beginWord);
            int count = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                count++;
                for (int i = 0; i < size; ++i) {
                    String start = queue.poll();
                    System.out.println("出队:" + start);

                    for (String s : wordList) {
                        // 已经遍历的不再重复遍历
                        if (visited.contains(s)) {
                            continue;
                        }
                        // 不能转换的直接跳过
                        if (!canConvert(start, s)) {
                            continue;
                        }
                        // 用于调试
                         System.out.println("  "+count + ": " + start + "->" + s);
                        // 可以转换，并且能转换成 endWord，则返回 count
                        if (s.equals(endWord)) {
                            return count + 1;
                        }
                        // 保存访问过的单词，同时把单词放进队列，用于下一层的访问
                        visited.add(s);
                        queue.offer(s);
                        System.out.println("  入队:" + s);
                    }
                    System.out.println();
                }
            }
            return 0;
        }

        public boolean canConvert(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            int count = 0;
            for (int i = 0; i < s1.length(); ++i) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    ++count;
                    if (count > 1) {
                        return false;
                    }
                }
            }
            return count == 1;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
