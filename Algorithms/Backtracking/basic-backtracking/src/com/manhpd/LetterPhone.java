package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;

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
        List<String> res = letterCombinations2(digits);

        res.stream().forEach(item -> System.out.print(item + ", "));
    }

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
        for(int i = 0; i < digits.length();i++){
            int index = Character.getNumericValue(digits.charAt(i));
            while(out_arr.peek().length() == i){
                String permutation = out_arr.remove();
                for(char c : char_map[index].toCharArray()){
                    out_arr.add(permutation + c);
                }
            }
        }
        return out_arr;
    }

}
