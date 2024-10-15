package com.manhpd.otherSolutions;

import java.util.ArrayList;
import java.util.List;

public class ListBasedSolution {

    public static void main(String[] args) {
        String[] strings = {
                "apple", "ant", "bin", "back", "ball", "big", "brick", "bribe", "broke", "bride", "brag", "bridge", "bright", "brisket", "yellow", "wood",
        };
        String prefix = "bri";

        List<String> res = listBasedSolution(strings, prefix);
        System.out.println(res.toString());
    }

    private static List<String> listBasedSolution(String[] strings, String prefix) {
        List<String> res = new ArrayList<>();

        for (String str : strings) {
            if (str.startsWith(prefix)) {
                res.add(str);
            }
        }

        return res;
    }
}
