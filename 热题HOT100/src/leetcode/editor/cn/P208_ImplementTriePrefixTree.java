/**
 * 题目Id：208
 * 题目：实现 Trie (前缀树)
 * 日期：2021-06-23 17:54:09
 */
//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。
//
// 请你实现 Trie 类：
//
//
// Trie() 初始化前缀树对象。
// void insert(String word) 向前缀树中插入字符串 word 。
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。
//
//
//
//
// 示例：
//
//
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
//
//
//
//
// 提示：
//
//
// 1 <= word.length, prefix.length <= 2000
// word 和 prefix 仅由小写英文字母组成
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
//
// Related Topics 设计 字典树
// 👍 798 👎 0


package leetcode.editor.cn;

//实现 Trie (前缀树)

public class P208_ImplementTriePrefixTree {
    public static void main(String[] args) {
        //测试代码
        Trie test1 = new P208_ImplementTriePrefixTree().new Trie();
        test1.insert("apple");

        test1.search("apple");
        System.out.println("test1.search(\"app\") = " + test1.search("app"));
        System.out.println("test1.startsWith(\"app\") = " + test1.startsWith("app"));

        test1.insert("app");
        System.out.println("test1.search(\"app\") = " + test1.search("app"));
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {
        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                // 没有就造,有就不管
                if (cur.next[index] == null) {
                    cur.next[index] = new TrieNode();
                }
                cur = cur.next[index];
            }
            cur.isEnd = true;
            cur.word = word;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                // 没有就造,有就不管
                if (cur.next[index] == null) {
                    return false;
                }
                cur = cur.next[index];
            }
            return cur.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                // 没有就造,有就不管
                if (cur.next[index] == null) {
                    return false;
                }
                cur = cur.next[index];
            }
            return true;
        }

        class TrieNode {
            TrieNode[] next = new TrieNode[26];
            // 是否为单词结尾
            boolean isEnd;
            // 如果是单词结尾,对应的单词
            String word;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
