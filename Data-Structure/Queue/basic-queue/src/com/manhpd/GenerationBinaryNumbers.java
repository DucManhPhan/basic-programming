package com.manhpd;

import java.util.*;

/**
 * Given a number n, write a function that generates and prints all binary numbers with decimal values from 1 to n.
 *
 * For example:
 *
 * Input: n = 2
 * Output: 1, 10
 *
 * Input: n = 5
 * Output: 1, 10, 11, 100, 101
 *
 */
public class GenerationBinaryNumbers {

    public static void main(String[] args) {
        GenerationBinaryNumbers solution = new GenerationBinaryNumbers();

        int n = 5;
        long startMs = System.currentTimeMillis();
//        List<String> res = solution.genBinaryNumbersII(n);
        List<String> res = solution.genBinaryNumbers(n);
        System.out.println("Time: " + (System.currentTimeMillis() - startMs));

        System.out.println(res);
    }

    /**
     * use brute force algorithm
     *
     * @param n
     * @return
     */
    private List<String> genBinaryNumbers(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            res.add(toBinaryNumber(i));
        }

        return res;
    }

    private String toBinaryNumber(int n) {
        Stack<Integer> stk = new Stack<>();

        while (n >= 2) {
            stk.push(n % 2);
            n = n/2;
        }

        stk.push(n);
        StringBuilder sb = new StringBuilder();

        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        return sb.toString();
    }

    private String toBinaryNumberII(int n) {
        return Integer.toBinaryString(n);
    }

    private String toBinaryNumberIII(int n) {
        StringBuilder sb = new StringBuilder(32);

        while (n > 0) {
            sb.append(n % 2);
            n >>= 1;
        }

        return sb.reverse().toString();
    }

    /**
     * Using Queue data structure
     *
     * @param n
     * @return
     */
    private List<String> genBinaryNumbersII(int n) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("1");

        while (n-- > 0) {
            String s1 = queue.peek();
            queue.remove();
            res.add(s1);

            queue.add(s1 + "0");
            queue.add(s1 + "1");
        }

        return res;
    }

}
