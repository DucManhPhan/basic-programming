package com.manhpd.prefixSearch;

import com.manhpd.trie.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two lists of words (strings), for each word in the second list, identify if the word appears as either a complete word or a prefix of any word in the first list.
 * If it's present as a complete word, print "word".
 * If it's present as a prefix, print "prefix".
 * If it isn't present as either a complete word or a prefix, print "not-found".
 *
 * Example 1
 * Sample Input:
 * list1: ["app", "apple", "apply", "ape"]
 * list2: ["app", "appl", "apex"]
 *
 * Sample Output:
 * ["word", "prefix", "not-found"]
 *
 * Example 2
 * Sample Input:
 * list1: ["pay", "paid", "pad", "pat"]
 * list2: ["pa", "pai", "pat"]
 *
 * Sample Output:
 * ["prefix", "prefix", "word"]
 *
 */
public class KeywordSearch {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("app", "apple", "apply", "ape");
        List<String> list2 = Arrays.asList("app", "appl", "apex");

        Trie trie = buildTrie(list1);
        List<String> res = prefixOrWord(trie, list2);

        System.out.println("Results: " + res);
    }

    private static Trie buildTrie(List<String> list1) {
        Trie trie = new Trie();

        list1.stream().forEach(it -> {
            trie.insert(it);
        });

        return trie;
    }

    private static List<String> prefixOrWord(Trie trie, List<String> list2) {
        List<String> res = new ArrayList<>();

        list2.stream().forEach(str -> {
            if (trie.search(str)) {
                res.add("word");
            } else if (trie.startsWith(str)) {
                res.add("prefix");
            } else {
                res.add("not-found");
            }
        });

        return res;
    }
}
