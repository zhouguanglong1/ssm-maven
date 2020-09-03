package com.zhougl.search;

/**
 * Trie树简单实现
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/15 18:02
 */
public class TrieTree {
    private TrieNode root = new TrieNode('/'); // 存储无意义字符

    // 往 Trie 树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    // 在 Trie 树中查找一个字符串
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 不存在 pattern
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false) return false; // 不能完全匹配，只是前缀
        else return true; // 找到 pattern
    }

    public class TrieNode {
        public char data;
        TrieNode[] children = new TrieNode[26];
        boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }
}
