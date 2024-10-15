package com.manhpd.prefixSearch;

import java.util.Arrays;
import java.util.List;

public class PrefixCount {

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "pay", "paid", "pat", "app", "apple", "apply", "ape"
        );

        String pref = "pa";

        int count = countWordsContainingPrefix(words, pref);
        System.out.println("Result: " + count);
    }

    private static int countWordsContainingPrefix(List<String> words, String prefix) {

        return 0;
    }
}
