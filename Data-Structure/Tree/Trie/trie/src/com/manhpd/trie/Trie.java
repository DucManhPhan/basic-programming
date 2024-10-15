package com.manhpd.trie;

class Trie {

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word == "") {
            return;
        }

        String lowerCase = word.toLowerCase();
        TrieNode node = this.root;
        int len = lowerCase.length();
        for (int i = 0; i < len; ++i) {
            char c = lowerCase.charAt(i);
            if (node.childrenNodes[c - 'a'] == null) {
                node.childrenNodes[c - 'a'] = new TrieNode();
            }

            node = node.childrenNodes[c - 'a'];
        }

        node.isEndOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word == "") {
            return false;
        }

        String lowerCase = word.toLowerCase();
        TrieNode node = this.root;
        int len = lowerCase.length();
        for (int i = 0; i < len; ++i) {
            char c = lowerCase.charAt(i);
            TrieNode tmp = node.childrenNodes[c - 'a'];
            if (tmp == null) {
                return false;
            }

            node = tmp;
        }

        return node != null && node.isEndOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix == "") {
            return false;
        }

        String lowerCase = prefix.toLowerCase();
        TrieNode node = this.root;
        int len = lowerCase.length();
        for (int i = 0; i < len; ++i) {
            char c = lowerCase.charAt(i);
            TrieNode tmp = node.childrenNodes[c - 'a'];
            if (tmp == null) {
                return false;
            }

            node = tmp;
        }

        return true;
    }

}

class TrieNode {

    public TrieNode[] childrenNodes;

    public boolean isEndOfWord;

    public TrieNode() {
        this.childrenNodes = new TrieNode[26];
        this.isEndOfWord = false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */