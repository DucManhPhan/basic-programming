package com.manhpd.otherSolutions;

import java.util.*;

public class PrefixHashMapSolution {

    public static void main(String[] args) {
        String[] strings = {
                "bright", "brisket", "yellow", "wood", "apple", "ant", "bin", "back", "ball", "big", "brick", "bribe", "broke", "bride", "brag", "bridge",
        };

//        String prefix = "bri";
        // Results: [bride, bribe, bright, brisket, brick, bridge]
        String prefix = "a";

        String[] results = prefixHashMapSolution(strings, prefix);
        System.out.println("Results: " + Arrays.toString(results));
    }

    private static String[] prefixHashMapSolution(String[] strings, String prefix) {
        Map<String, Set<String>> prefixHashMap = buildPrefixHashMap(strings);

        return prefixHashMap.get(prefix).toArray(new String[0]);
    }

    private static Map<String, Set<String>> buildPrefixHashMap(String[] strings) {
        Map<String, Set<String>> prefixHashMap = new HashMap<>();

        for (String str : strings) {
            for (int i = 0; i < str.length(); ++i) {
                String prefix = str.substring(0, i + 1);

                if (prefixHashMap.containsKey(prefix)) {
                    Set<String> values = prefixHashMap.get(prefix);
                    values.add(str);
                } else {
                    Set<String> newValues = new HashSet<>();
                    newValues.add(str);

                    prefixHashMap.put(prefix, newValues);
                }
            }
        }

        return prefixHashMap;
    }
}
