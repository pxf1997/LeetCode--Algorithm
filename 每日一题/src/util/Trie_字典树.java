package util;

import org.junit.Test;

/**
 * @author pxf
 * @create 2021-05-24 11:35
 */
public class Trie_字典树 {

    @Test
    public void test1() {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println("test1.search(\"apple\") = " + trie.search("apple"));
        System.out.println("test1.search(\"app\") = " + trie.search("app"));
        System.out.println("test1.startsWith(\"app\") = " + trie.startsWith("app"));

        trie.insert("app");
        System.out.println("test1.search(\"app\") = " + trie.search("app"));
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
                if (cur.next[index] == null) {
                    return false;
                }
                cur = cur.next[index];
            }
            return true;
        }

        class TrieNode {
            boolean isEnd;
            String word; // isEnd的改进--记录以此节点结尾的单词是啥
            TrieNode[] next = new TrieNode[26];
        }
    }
}
