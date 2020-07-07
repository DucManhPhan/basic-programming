package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LetterPhone {

    public static Map<Character, String> numToCharacters = new HashMap<>(){{
        put('0', "0");
        put('1', "1");
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public static void main(String[] args) {
        String digits = "23";
//        List<String> res = letterCombinations(digits);
//        List<String> res = letterCombinations2(digits);
//        List<String> res = letterCombinationsII(digits);
        List<String> res = letterCombinationIterativeVersion(digits);

        res.stream().forEach(item -> System.out.print(item + ", "));
    }

    /**
     * Using backtracking algorithm
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        letterCombinations(digits, res, 0, digits.length(), new ArrayList<>());

        return res;
    }

    public static void letterCombinations(String digits, List<String> res, int start, int size, List<Character> values) {
        if (start > size) {
            return;
        }

        if (start == size) {
            res.add(values.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }

        String correspondingChars = numToCharacters.get(digits.charAt(start));
        for (char c : correspondingChars.toCharArray()) {
            values.add(c);
            letterCombinations(digits, res, start + 1, size, values);
            values.remove(values.size() - 1);
        }
    }

    /**
     * Using iterative version - combines with Queue
     *
      * @param digits
     * @return
     */
    public static List<String> letterCombinations2(String digits) {
        LinkedList<String> out_arr = new LinkedList();
        if(digits.length() == 0) return out_arr;
        out_arr.add("");
        String[] char_map = new String[]{
                "0",
                "1",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        for(int i = 0; i < digits.length(); i++) {
            int index = Character.getNumericValue(digits.charAt(i));
            while(out_arr.peek().length() == i) {
                String permutation = out_arr.remove();
                for(char c : char_map[index].toCharArray()){
                    out_arr.add(permutation + c);
                }
            }
        }

        return out_arr;
    }

    /**
     * Using recursive version
     *
     */
    public static List<String> letterCombinationsII(String digits) {
        return letterCombinationsII(digits, 0);
    }

    public static List<String> letterCombinationsII(String digits, int index) {
        List<String> list = new ArrayList<>();
        String chars = numToCharacters.get(digits.charAt(index));

        // base case - get all characters at the last index
        if (index == digits.length() - 1) {
            for (int i = 0; i < chars.length(); ++i) {
                list.add("" + chars.charAt(i));
            }

            return list;
        }

        List<String> subList = letterCombinationsII(digits, index + 1);
        for (int i = 0; i < chars.length(); ++i) {
            char c = chars.charAt(i);
            for (String tmp : subList) {
                list.add(c + tmp);
            }
        }

        return list;
    }

    public static List<String> letterCombinationIterativeVersion(String digits) {
        Queue<String> queue = new LinkedList<>();
        boolean isFirstDigit = true;

        for (char c : digits.toCharArray()) {
            String charsOfDigit = numToCharacters.get(c);

            if (isFirstDigit) {
                charsOfDigit.chars()
                        .mapToObj(ch -> (char)ch)
                        .forEach(ch -> queue.add(String.valueOf(ch)));
                isFirstDigit = false;
                queue.add(null);
                continue;
            }

            while (!queue.isEmpty() && !isFirstDigit) {
                String str = queue.remove();
                if (str == null) {
                    break;
                }

                for (char tmp : charsOfDigit.toCharArray()) {
                    queue.add(str + tmp);
                }
            }

            // mark the end of characters when iterate each digit
            queue.add(null);
        }

        return queue.stream()
                    .filter(str -> str != null)
                    .collect(Collectors.toList());
    }

}
