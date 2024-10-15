package com.manhpd.otherSolutions;

import java.util.Arrays;

public class SortedListBasedSolution {

    public static void main(String[] args) {
        String[] strings = {
                 "bright", "brisket", "yellow", "wood", "apple", "ant", "bin", "back", "ball", "big", "brick", "bribe", "broke", "bride", "brag", "bridge",
        };

        String prefix = "bri";
        String[] results = sortedListBasedSolution(strings, prefix);
        System.out.println("Results: " + Arrays.toString(results));
    }

    /**
     * Sort the input that has array list of strings first.
     * Using binary search to find the correct incremental prefix
     *
     * @param strings
     * @return
     */
    private static String[] sortedListBasedSolution(String[] strings, String prefix) {
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));

        int lengthPrefix = prefix.length();
        for (int i = 1; i < lengthPrefix + 1; ++i) {
            String prefixToSearch = prefix.substring(0, i);
            System.out.println("prefixToSearch: " + prefixToSearch);

            int startIdx = lowerBound(strings, prefixToSearch);
            int endIdx = upperBound(strings, prefixToSearch);
            System.out.println("startIdx = " + startIdx + ", endIdx = " + endIdx);

            strings = Arrays.copyOfRange(strings, startIdx, endIdx + 1, String[].class);
            System.out.println("current strings: " + Arrays.toString(strings));
        }

        return strings;
    }

    private static int lowerBound(String[] strings, String prefix) {
        if (strings == null || strings.length == 0) {
            return -1;
        }

        int left = 0;
        int right = strings.length;

        while (left + 1 < right) {
            int mid = (right + left) / 2;

            if (strings[mid].startsWith(prefix)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (strings[right].startsWith(prefix)) {
            return right;
        }

        return -1;
    }

    private static int upperBound(String[] strings, String prefix) {
        if (strings == null || strings.length == 0) {
            return -1;
        }

        int left = 0;
        int right = strings.length;

        while (left + 1 < right) {
            int mid = (right + left) / 2;

            if (strings[mid].startsWith(prefix)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (strings[left].startsWith(prefix)) {
            return left;
        }

        return -1;
    }
}
