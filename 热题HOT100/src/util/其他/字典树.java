package util.其他;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-06-23 18:04
 */
public class 字典树 {
    @Test
    public void test1() {
        Trie trie = new Trie();
        trie.insert("apple");

        trie.search("apple");
        System.out.println("trie.search(\"app\") = " + trie.search("app"));
        System.out.println("trie.startsWith(\"app\") = " + trie.startsWith("app"));

        trie.insert("app");
        System.out.println("trie.search(\"app\") = " + trie.search("app"));
    }

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
}
