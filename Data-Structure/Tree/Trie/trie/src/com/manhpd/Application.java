package com.manhpd;

public class Application {

    public static void main(String[] args) {
        testTrie();
    }

    public static void testTrie() {
        String[] nums = {"Trie","insert","search","search","startsWith","insert","search" ,"apple","apple","app","app","app","app"};
        Trie trie = new Trie();

        for (String s : nums) {
            trie.insert(s);
        }

        boolean isExist = trie.search("insert");
        System.out.println(isExist);
    }

}
